package com.J621.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.J621.vo.J621Image;

public class PicDownload implements Runnable {

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

	private List<J621Image> list;

	public PicDownload() {
		super();
	}

	public PicDownload(List<J621Image> list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		String s1 = format.format(new Date());
		System.out.println(s1);

		System.out.println(list.size());

		try {
			for (J621Image imageVO : list) {

				FileOutputStream out = null;
				InputStream in = null;
				HttpURLConnection conn = null;

				conn = getHttp(imageVO.getUrl());
	
				File file = new File(imageVO.getFilePath(), imageVO.getImageCount()+imageVO.getImageType());

				out = new FileOutputStream(file);

				in = conn.getInputStream();
				byte[] b = new byte[8096];
				int len2 = 0;
				while ((len2 = in.read(b)) != -1) {
					out.write(b, 0, len2);
				}
				System.out.println("下载完成");
				String s2 = format.format(new Date());
				System.out.println(s2);
				System.out.println(format.parse(s2).getTime() - format.parse(s1).getTime());
				in.close();
				out.close();
				System.gc();
			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			// try {
			// in.close();
			// out.close();
			// System.gc();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			System.err.println("线程关闭");
		}

	}

	public HttpURLConnection getHttp(String urlLocation) throws IOException {
		URL url = null;
		if (urlLocation != null) {
			url = new URL(urlLocation);
		}
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(30 * 60 * 1000);
		conn.setRequestMethod("GET");

		return conn;
	}

}
