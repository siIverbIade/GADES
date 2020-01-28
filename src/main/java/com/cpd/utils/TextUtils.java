package com.cpd.utils;

public class TextUtils {
	public static String firstLetterUp(String arg) {
		return arg.substring(0, 1).toUpperCase() + arg.substring(1);
	}
	
	public static String firstLetterDn(String arg) {
		return arg.substring(0, 1).toLowerCase() + arg.substring(1);
	}
}