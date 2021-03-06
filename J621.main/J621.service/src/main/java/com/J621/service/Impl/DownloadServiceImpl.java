package com.J621.service.Impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.J621.dao.J621ImageMapper;
import com.J621.dao.J621UserMapper;
import com.J621.service.DownloadService;
import com.J621.utils.FinalStrings;
import com.J621.utils.IDUtil;
import com.J621.utils.JsonUtils;
import com.J621.utils.MD5Util;
import com.J621.vo.J621Image;
import com.J621.vo.J621User;

@Service
public class DownloadServiceImpl implements DownloadService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private J621ImageMapper imageMapper;
	
	@Autowired
	private J621UserMapper userMapper;

	@Override
	public List<String> getIndexUrlList(int startIndex, int endIndex, String kEY) {
		String url = null;
		List<String> indexUrlList = new ArrayList<String>();
		for (int a = startIndex; a <= endIndex; a++) {
			url = "https://e621.net/post/index/" + a + "/" + kEY;
			// System.out.println("正在扫描第" + a + "页,地址为" + url);
			indexUrlList.add(url);
		}

		return indexUrlList;

	}

	@Override
	public List<String> getSimpleImgUrlList(List<String> indexUrlList, int minScore) throws IOException {
		Document doc = null;
		int scoreInt = 0;
		String linkUrl = null;
		List<String> SimpleImgUrlList = new ArrayList<String>();
		int count = 1;
		for (String indexUrl : indexUrlList) {
			doc = Jsoup.connect(indexUrl).get();
			Elements spans = doc.getElementsByClass("thumb");
			if(spans==null||spans.size()==0) {
				continue;
			}
			for (Element span : spans) {
				Elements scores = span.getElementsByClass("post-score-faves");
				for (Element score : scores) {
					scoreInt = Integer.parseInt(score.text().substring(1, score.text().length()));

					if (scoreInt < minScore) {
						System.out.println("图片分数过低跳过下载,分数为" + scoreInt);
						break;
					} else {
						Elements links = span.getElementsByTag("a");
						for (Element link : links) {
							linkUrl = "https://e621.net" + link.attr("href") + "~" + scoreInt;
							System.out.println("正在分析第" + count + "张图片,地址为:" + linkUrl);
							SimpleImgUrlList.add(linkUrl);
							count++;
						}
					}
				}
			}
		}

		return SimpleImgUrlList;
	}

	@Override
	public List<String> getHDImgUrlList(List<String> simpleImgUrlList, String kEY) throws IOException {
		Document doc = null;
		String hdImgUrl = null;
		List<String> HDImgUrlList = new ArrayList<String>();
		int count = 1;
		for (String imgUrl : simpleImgUrlList) {
			String[] temp = imgUrl.split("~");
			doc = Jsoup.connect(temp[0]).get();
			Element img = doc.getElementById("image");
			try {
				hdImgUrl = img.attr("src") + "~" + temp[1];
			} catch (NullPointerException e) {
				System.err.println("跳过flash文件");
				continue;
			}

			System.out.println("正在分析第" + count + "张图片静态地址,地址为" + hdImgUrl);
			HDImgUrlList.add(hdImgUrl);
			count++;

		}
		return HDImgUrlList;
	}

	@Override
	public List<J621Image> downloadPic(List<String> hDImgUrlList, String lOCAL_ADDR, String kEY, J621User user) {

		String mKey = MD5Util.encrypt(kEY);
		System.out.println(mKey);
		Map<String, J621Image> imageMap = imageMapper.getAllImagesByKey(mKey, user.getId());

		int MaxFileName = imageMapper.getMxFileName(mKey, user.getId());

		List<J621Image> li = new ArrayList<J621Image>();
		int count = 1;
		if (MaxFileName != 0) {
			count = MaxFileName + 1;
		}
		int total = user.getPicCount();
		String filePath = null;
		String savePath = lOCAL_ADDR + FinalStrings.SEPARATOR + user.getId() + FinalStrings.SEPARATOR + kEY
				+ FinalStrings.SEPARATOR;
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		for (String urlLocation : hDImgUrlList) {
			String[] temp = urlLocation.split("~");
			String url = temp[0].substring(FinalStrings.E621_STATIC.length(), temp[0].length());
			if (imageMap.containsKey(url)) {
				continue;
			}

			J621Image image = new J621Image();

			filePath = savePath + count + "." + temp[0].substring(temp[0].length() - 3, temp[0].length());
			image.setId(IDUtil.getID());
			image.setFilePath(savePath);
			image.setImageType("." + temp[0].substring(temp[0].length() - 3, temp[0].length()));
			image.setImageCount(count);
			image.setUrl(temp[0]);
			image.setScore(temp[1]);
			image.setKeyses(mKey);
			image.setCreateDate(new Date());
			image.setCreateDay(sdf.format(new Date()));
			image.setSalt(FinalStrings.SALT.toString());

			System.out.println("正在扫描第" + count + "张图片,地址为" + filePath);
			li.add(image);
			count++;
			total++;
			if((!user.getStatus().equals("0"))&&total>=user.getMaxCount()) {
				break;
			}
		}
		// ThreadPool.getFileWithThreadPool(urlLocation, filePath, 4, savePath);
		user.setPicCount(total);
		userMapper.updateByPrimaryKey(user);
		System.err.println("所有图片扫描完毕!本次共下载" + count + "张图片");
		System.err.println("正在渲染请稍等");
		return li;
	}

	@Override
	public void saveImg(List<J621Image> li, String localAddr, String userId) {
		for (J621Image j621Image : li) {
			j621Image.setUrl(
					j621Image.getUrl().substring(FinalStrings.E621_STATIC.length(), j621Image.getUrl().length()));
			j621Image.setFilePath(localAddr);
			j621Image.setUserId(userId);
			imageMapper.insert(j621Image);
		}
		System.out.println("保存完毕");
	}

	@Override
	public String testUrl(String testUrl) {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			Document doc = Jsoup.connect(testUrl).get();
			Elements spans = doc.getElementsByClass("thumb");
			if(spans==null||spans.size()==0) {
				
				m.put("result", "未找到该关键字");
				return JsonUtils.objectToJson(m);
			}
		} catch (IOException e) {
			m.put("result", "服务器维护中，稍后再试");
			return JsonUtils.objectToJson(m);
		}
		return null;
	}

}
