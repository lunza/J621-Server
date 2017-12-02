package com.J621.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.J621.vo.J621Image;


public class ThreadPool {

	public static String getFileWithThreadPool(List<J621Image> tdData, int threadPoolSize) {
		ExecutorService taskExecutor = Executors.newCachedThreadPool();
		int end = tdData.size();
		List<J621Image> data = new ArrayList<J621Image>();
		List<List<J621Image>> li = new ArrayList<List<J621Image>>();
		try {
			// 数据集拆分
			if (tdData.size() > threadPoolSize) {
				int startPoint;
				int endPoint;
				
				// 生成和线程数相等数量的数据集
				for (int i = 1; i <= threadPoolSize; i++) {
					startPoint = i - 1;
					endPoint = i;
					if (endPoint != threadPoolSize) {
						data = getDataList(tdData, end / threadPoolSize * startPoint,
								end / threadPoolSize * endPoint);
						li.add(data);
						// 处理余数
					} else {
						data = getDataList(tdData, end / threadPoolSize * startPoint, end);
						li.add(data);
					}
				}
				// 生成新线程
				for (int i = 0; i <= threadPoolSize - 1; i++) {
					taskExecutor.execute(new PicDownload(li.get(i)));
					// 需要加延迟5到10毫秒，否则易发生数据库死锁
					Thread.sleep(10);
				}
			} else {
				taskExecutor.execute(new PicDownload(tdData));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			taskExecutor.shutdown();
			while (true) {
				if (taskExecutor.isTerminated()) {
					System.out.println("下载完成，本次共"+tdData.size()+"张图片");
					System.gc();
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "success";

	}

	private static List<J621Image> getDataList(List<J621Image> tdData, int start, int end) {
		List<J621Image> data = new ArrayList<J621Image>();
		for (int i = start; i < end; i++) {
			data.add(tdData.get(i));
		}
		return data;
	}


}
