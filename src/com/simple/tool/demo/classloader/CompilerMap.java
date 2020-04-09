package com.simple.tool.demo.classloader;

import java.util.HashMap;
import java.util.Map;


public class CompilerMap {

	private static Map<String,CustomStringJavaCompiler> map = new HashMap();//JDK版本可能较低，所以未使用CurrentHashMap
	
	private static Map<String,Class> classMap = new HashMap();
	
	
	public static Class getClass(String key) {
		return classMap.get(key);
	}

	public static void setClass(String key,Class classz) {
		classMap.put(key, classz);
	}

	public static void putMap(String key,CustomStringJavaCompiler compiler){
		synchronized (CompilerMap.class) {
			map.put(key, compiler);
		}
		
	}
	
	public static CustomStringJavaCompiler getMap(String key){
		return map.get(key);
	}
}
