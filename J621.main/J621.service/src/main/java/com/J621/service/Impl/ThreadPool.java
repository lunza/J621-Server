package com.J621.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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
						data = getDataList(tdData, end / threadPoolSize * startPoint, end / threadPoolSize * endPoint);
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
					System.out.println("下载完成，本次共" + tdData.size() + "张图片");
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

	public static List<String> getHDURLWithThreadPool(List<String> tdData, int threadPoolSize) {

		ExecutorService taskExecutor = Executors.newCachedThreadPool();
		int end = tdData.size();
		List<String> hdUrl = new ArrayList<String>();
		List<FutureTask> futureTasks = new ArrayList<FutureTask>();
		List<String> data = new ArrayList<String>();
		List<List<String>> li = new ArrayList<List<String>>();
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
						data = getURLDataList(tdData, end / threadPoolSize * startPoint,
								end / threadPoolSize * endPoint);
						li.add(data);
						// 处理余数
					} else {
						data = getURLDataList(tdData, end / threadPoolSize * startPoint, end);
						li.add(data);
					}
				}
				// 生成新线程
				for (int i = 0; i <= threadPoolSize - 1; i++) {

					FutureTask futureTask = new FutureTask(new URLAnalyzer(li.get(i)));
					futureTasks.add(futureTask);

					taskExecutor.submit(futureTask);
					// 需要加延迟5到10毫秒，否则易发生数据库死锁
					Thread.sleep(10);
				}
			} else {
				FutureTask futureTask = new FutureTask(new URLAnalyzer(tdData));
				futureTasks.add(futureTask);
				taskExecutor.submit(futureTask);
			}
			for (FutureTask futureTask : futureTasks) {
				// 该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
				List<String> url = (List<String>)futureTask.get();
				for (String string : url) {
					hdUrl.add(string);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			taskExecutor.shutdown();
			while (true) {
				if (taskExecutor.isTerminated()) {
					System.out.println("地址解析完成，本次共" + tdData.size() + "张图片");
					System.gc();
					break;
				}
				try {
					Thread.sleep(1000);
					return hdUrl;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(hdUrl.size());
		return hdUrl;

	}

	private static List<String> getURLDataList(List<String> tdData, int start, int end) {
		List<String> data = new ArrayList<String>();
		for (int i = start; i < end; i++) {
			data.add(tdData.get(i));
		}
		return data;
	}
	
	
	

}
