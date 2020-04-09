package com.simple.tool.demo.util;

import java.lang.reflect.Method;

import com.simple.tool.demo.classloader.CompilerMap;

public class SignUtil {

	public static boolean checkSign(String fullName,String a,String b,String sign) throws Exception{
		boolean flag = false;
		Class aClass = CompilerMap.getClass(fullName);
		
		if(aClass == null){
			return false;
		}
		
    	Method[] mms = aClass.getMethods();
        Method getInstance = aClass.getMethod("getInstance");
        Method checkSign = aClass.getMethod("checkSign", String.class,String.class,String.class);
        
        Object obj = getInstance.invoke(null);
        flag = (Boolean)checkSign.invoke(obj, a,b,sign);
        return flag;
	}
}
