package com.J621.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public class MD5Util {
	
	

	public static String encrypt(String key) {

		char[] arr = key.toCharArray();
		String temp = "";
		for (int i = arr.length-1; i >= 0; i--) {
			
			temp = new BigDecimal((int)arr[i]).multiply(FinalStrings.SALT)+","+temp;
		}
		
		temp = temp.substring(0,temp.length()-1);
		return temp;

	}
	
	public static String decrypt(String mkey,String salt) {
		String result = "";
		String[] temp = mkey.split(",");
		char[] ch = new char[temp.length];
		for (int a = 0;a<temp.length;a++) {
			BigDecimal b1 =new BigDecimal( temp[a]).divide(new BigDecimal(salt));
			
			int b = b1.intValue();
			ch[a] = (char)b;
		}
		result = result.valueOf(ch);
		return result;
		
	}

	public static void main(String[] args) {
		String temp = encrypt(" ");
			System.out.println(temp);
			String temp2 = decrypt(temp,FinalStrings.SALT.toString());
			System.out.println(temp2);
		
	}

}