package com.J621.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.J621.service.DownloadService;
import com.J621.service.UserService;
import com.J621.service.Impl.ThreadPool;
import com.J621.utils.FinalStrings;
import com.J621.utils.JsonUtils;
import com.J621.utils.SysUtils;
import com.J621.utils.ZipUtils;
import com.J621.vo.J621Image;

@Controller
@RequestMapping("/J621/service")
public class J621Controller {

	private static String os = FinalStrings.getOS();

	@Autowired
	private DownloadService dlService;

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public String downloadImages(@RequestParam(value = "endIndex", required = false) String endIndex,
			@RequestParam(value = "startIndex", required = false) String startIndex,
			@RequestParam(value = "keyses", required = false) String keyses,
			@RequestParam(value = "minScore", required = false) String minScore,
			@RequestParam(value = "threadPoolSize", required = false) String threadPoolSize,
			@RequestParam(value = "userId", required = false) String userId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		SysUtils.deletePath(userId);
		String localAddr = "";
		if (os.equals("windows")) {
			localAddr = FinalStrings.WINADDR;
		} else {
			localAddr = FinalStrings.LINADDR;
		}
		int start_Index = Integer.parseInt(startIndex);
		int thread_Pool_Size = Integer.parseInt(threadPoolSize);
		int end_Index = Integer.parseInt(endIndex);
		// String key = pro.getProperty("KEY");
		int min_Score = Integer.parseInt(minScore);
		
		// String localAddr = pro.getProperty("LOCAL_ADDR");
		// int start_File_Name = Integer.parseInt(startFileName);
		System.err.println("开始分析页码信息");
		List<String> indexUrlList = dlService.getIndexUrlList(start_Index, end_Index, keyses);
		System.err.println("页码信息分析完毕,开始分析图片详细地址");
		List<String> SimpleImgUrlList = dlService.getSimpleImgUrlList(indexUrlList, min_Score);
		System.err.println("详细地址分析完毕,开始分析图片静态地址");
		// List<String> HDImgUrlList = dlService.getHDImgUrlList(SimpleImgUrlList, key);
		List<String> HDImgUrlList = ThreadPool.getHDURLWithThreadPool(SimpleImgUrlList, thread_Pool_Size);
		System.err.println("静态地址分析完毕,开始下载图片");
		System.out.println(keyses);
		List<J621Image> li = dlService.downloadPic(HDImgUrlList, localAddr, keyses, userId);
		ThreadPool.getFileWithThreadPool(li, thread_Pool_Size);

		String srcFile = localAddr + FinalStrings.SEPARATOR + userId + FinalStrings.SEPARATOR + keyses;
		System.out.println(srcFile);
		ZipUtils.doCompress(srcFile, srcFile + ".zip");

		dlService.saveImg(li, localAddr, userId);

		Map<String, Object> result = new HashedMap<String, Object>();
		result.put("result", "下载成功");
		String rep = "";
		String sep = "";
		if (os.equals("windows")) {
			rep = "\\\\";
			sep = "~~";
		} else {
			rep = FinalStrings.SEPARATOR;
			sep = "~";
		}
		result.put("srcFile", (srcFile + ".zip").replaceAll(rep, sep));
		// request.getSession().setAttribute("srcFile", srcFile + ".zip");
		request.getSession().setAttribute("isOver", "OK");
		return JsonUtils.objectToJson(result);

	}

	@RequestMapping(value = "/downloadZipFile", method = RequestMethod.POST)
	@ResponseBody
	public void downloadZipFile(HttpServletResponse response,
			@RequestParam(value = "filePath", required = true) String filePath
			) {
		System.out.println(filePath);
		String sep = "";
		if (os.equals("windows")) {

			sep = "~~";
		} else {

			sep = "~";
		}
		String filePath2 = filePath.replace(sep, FinalStrings.SEPARATOR);
		System.out.println(filePath2);
		File file1 = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			file1 = new File(filePath2);

			// Spring工具获取项目resources里的文件
			// File file2 = ResourceUtils.getFile("classpath:shell/init.sh");
			if (!file1.exists()) {
				return;
			}
			response.reset();
			if (os.equals("windows")) {
				response.setHeader("Content-Disposition", "attachment;filename=" + filePath.split(sep)[2]);
			} else {
				response.setHeader("Content-Disposition", "attachment;filename=" + filePath.split(sep)[3]);
			}

			response.setContentType("application/octet-stream; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(file1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

}
