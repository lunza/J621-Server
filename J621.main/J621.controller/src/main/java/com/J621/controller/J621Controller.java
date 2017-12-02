package com.J621.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.J621.service.DownloadService;
import com.J621.service.Impl.ThreadPool;
import com.J621.vo.J621Image;



@Controller
@RequestMapping("/J621")
public class J621Controller {
	
	@Autowired
	private DownloadService dlService;

	@RequestMapping(value = "/download",method = RequestMethod.GET)
	public String downloadImages(
			@RequestParam(value = "endIndex" ,required=true) String endIndex,
			@RequestParam(value = "startIndex",required=true) String startIndex,
			@RequestParam(value = "key",required=true) String key,
			@RequestParam(value = "minScore",required=true) String minScore,
			@RequestParam(value = "localAddr",required=true) String localAddr,
			@RequestParam(value = "startFileName",required=true) String startFileName,
			@RequestParam(value = "threadPoolSize",required=true) String threadPoolSize) throws IOException {
		int start_Index = Integer.parseInt(startIndex);
		int thread_Pool_Size = Integer.parseInt(threadPoolSize);
		int end_Index = Integer.parseInt(endIndex);
		//String key = pro.getProperty("KEY");
		int min_Score = Integer.parseInt(minScore);
		//String localAddr = pro.getProperty("LOCAL_ADDR");
		int start_File_Name = Integer.parseInt(startFileName);
		System.err.println("开始分析页码信息");
		List<String> indexUrlList = dlService.getIndexUrlList(start_Index, end_Index, key);
		System.err.println("页码信息分析完毕,开始分析图片详细地址");
		List<String> SimpleImgUrlList = dlService.getSimpleImgUrlList(indexUrlList, min_Score);
		System.err.println("详细地址分析完毕,开始分析图片静态地址");
		List<String> HDImgUrlList = dlService.getHDImgUrlList(SimpleImgUrlList, key);
		System.err.println("静态地址分析完毕,开始下载图片");
		List<J621Image> li = dlService.downloadPic(HDImgUrlList, localAddr, key, start_File_Name);
		ThreadPool.getFileWithThreadPool(li,thread_Pool_Size);
		dlService.saveImg(li);
		return null;
	}

}
