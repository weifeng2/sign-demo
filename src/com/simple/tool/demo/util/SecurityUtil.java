package com.simple.tool.demo.util;

public class SecurityUtil {
	
	private static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQuYr4uMM0E/fxHkCvO/OmcjUs+rKlLxN47D0n90qADrHjgzZEwFgwk2GYYtPVZRhkgVs9boagUsZ4ROBC5KhFrnoYMdHSUKdq2Lunh9Nk53Q84hBJN5gWJ6Ud91yllBhiDEFDkAkYI5vnX1l127mDsN0xgGPMPeTT58/7eYXxXQIDAQAB";
	private static final String PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJC5ivi4wzQT9/EeQK8786ZyNSz6sqUvE3jsPSf3SoAOseODNkTAWDCTYZhi09VlGGSBWz1uhqBSxnhE4ELkqEWuehgx0dJQp2rYu6eH02TndDziEEk3mBYnpR33XKWUGGIMQUOQCRgjm+dfWXXbuYOw3TGAY8w95NPnz/t5hfFdAgMBAAECgYBbCNsp6M6hC46C1QzLLWLt6Bka2fdVgG83OSKe6SartpKfEgIo7PSMWaiiOkgViyn0zomd8tAsOOUu8eQ/L6XIeyx300HOhlNA0QN6wK1DmMu9osBpruSXdSRY06iT4+JKk2Rn48bevLzfn8Z5FFEQn4yBxEDG8wd0f9HmOyhTAQJBANlmOOwR8oklPkx/FPYtTix7J7oXQVhfzl/sSW9NyEaJVIEuhoAeRqtK1hI/O+DT8X3M1YxxVRGl8pMBNVM9xiUCQQCqa++RsyUhqpGrW/JgHjAg+D9xaWEAd2JMZKKz3e7DNjwoRDMAOeSTBaBmWgKR3EATL/KHFTizogtD+eh/HkzZAkBW6fhN4Or/C4W5nYDq3rgHLoF2c3+rbjoavWOqfQVZteOz9b7OD8yfBL9K8j7lJrbAXZjumgyxjo/JV81S5zqFAkEAqE9jUl1APcishzWO4bjOxbEHT2XIxGIjtdjqC4QTK76jmQ0J5Z5HjaBP8uXQheelbI29FAY0csyGHcNyCCGPuQJBAJdXbtYUKau3nInF0rip/r4Bs3tyKBfGnZx2H0Ab6vl2exzzMvTivbYzQZ70fo8P82A8ntBmawLcrZt4UWpVtr4=";

	//加密
	public static String encode(String str) throws Exception{
		byte[] ss = RSAUtils.encryptByPublicKey(str.getBytes(), PUBLICKEY);
		return Base64Util.encode(ss);
	}
	
	//解密
	public static String decode (String str)throws Exception{
		byte[] ss = RSAUtils.decryptByPrivateKey(Base64Util.decode(str), PRIVATEKEY);
		return new String(ss);
	}
	
/*	public static void main(String[] args){
		try {
			String aa = SecurityUtil.encode("abc");
			System.out.println(aa);
			String bb = SecurityUtil.decode(SecurityUtil.encode("abc"));
			System.out.println(bb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
