package com.J621.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class URLAnalyzer implements Callable {

	private List<String> list;
	public volatile static int count;

	public URLAnalyzer() {
		super();
	}

	public URLAnalyzer(List<String> list) {
		super();
		this.list = list;
	}


	@Override
	public List<String> call() throws Exception {
		Document doc = null;
		String hdImgUrl = null;
		List<String> HDImgUrlList = new ArrayList<String>();
		count = 1;
		for (String imgUrl : list) {
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

}
