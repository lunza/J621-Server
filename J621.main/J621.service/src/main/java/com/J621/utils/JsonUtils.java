package com.J621.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

	/**
	 * 
	 * @Title: objectToJson
	 * @Type JsonServiceImpl
	 * @Description: 封装json
	 * @author 孙菁
	 * @version 最后修改时间：2017年11月1日 上午9:02:38
	 * @param result
	 * @return
	 */
	public static String objectToJson(Map<String, Object> result) {

		String str = JSON.toJSONString(result);
		str = str.replace("\\", "");
		str = str.replace("\"[", "[");
		str = str.replace("]\"", "]");
		str = str.replace("\"{", "{");
		str = str.replace("}\"", "}");

		return str;

	}

	/**
	 * 只包含数字 或字母大小写
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateNumAndLetter(String str) {
		String regex = "^[a-z0-9A-Z]+$";

		return str.matches(regex);

	}
	
	/**
	 * 只包含数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateNum(String str) {
		String regex = "^[0-9]+$";

		return str.matches(regex);

	}
	/**
	 * 只包含英文，空格或下划线
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateLetter(String str) {
		String regex = "^[a-zA-Z\\d_\\s]*$";
		return str.matches(regex);
	}
	
	public static void main(String[] args) {
		String regex1 = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		String regex2 = "^[0-9]+$";
		String str = "1";
		System.out.println(str.matches(regex2));
	}


}
