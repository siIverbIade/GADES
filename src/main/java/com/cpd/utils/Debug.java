package com.cpd.utils;

public final class Debug {
	public static String Print(Object object) {
		Json json = new Json();
		System.out.println(json.Decode(object, false));
		return json.Decode(object, false);
	}
}