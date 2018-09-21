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
	public static String objectToJson(Map<String,Object> result) {

		String str = JSON.toJSONString(result);
		str = str.replace("\\", "");
		str = str.replace("\"[", "[");
		str = str.replace("]\"", "]");
		str = str.replace("\"{", "{");
		str = str.replace("}\"", "}");

		return str;

	}
	
	
	

}
