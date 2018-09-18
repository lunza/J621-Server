package com.J621.utils;

import java.util.UUID;

public class IDUtil {
	
	public static String getID() {

		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}


}
