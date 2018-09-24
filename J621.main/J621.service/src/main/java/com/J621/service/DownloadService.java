package com.J621.service;

import java.io.IOException;
import java.util.List;

import com.J621.vo.J621Image;

public interface DownloadService {

	public List<String> getIndexUrlList(int startIndex, int endIndex, String kEY);

	public List<String> getSimpleImgUrlList(List<String> indexUrlList, int minScore) throws IOException;

	public List<String> getHDImgUrlList(List<String> simpleImgUrlList, String kEY) throws IOException;
	
	public List<J621Image> downloadPic(List<String> hDImgUrlList, String lOCAL_ADDR, String kEY,String userId, String userName);

	public void saveImg(List<J621Image> li,String localAddr, String userId);


}
