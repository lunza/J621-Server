package com.J621.utils;

import java.io.File;
import java.math.BigDecimal;

public class FinalStrings {

	public final static String E621_STATIC = "https://static1.e621.net/";
	public static final BigDecimal SALT = new BigDecimal("3.4");
	public static final String SEPARATOR = File.separator;

	public static final String os = System.getProperty("os.name");
	
	public static final String WINADDR = "D:\\pics";
	public static final String LINADDR = "/root/pics";

	public static String getOS() {
		if (os.toLowerCase().startsWith("win")) {

			return "windows";
		}
		return "linux";
	}
}
