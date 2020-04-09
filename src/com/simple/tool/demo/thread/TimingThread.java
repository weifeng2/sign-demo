package com.simple.tool.demo.thread;

import com.simple.tool.demo.classloader.CompilerMap;
import com.simple.tool.demo.classloader.CustomStringJavaCompiler;
import com.simple.tool.demo.redis.RedisUtil;
import com.simple.tool.demo.util.SecurityUtil;

public class TimingThread implements Runnable{
	
	public static final String SERIMODEL = "package com.simple.tool.demo.model;public class SeriModel {private String a;private String b;private String sign;public boolean getSign(String a,String b,String sign){return true;}}";
	public static final String ENCRIPTFACTORY = "package com.simple.tool.sign.factory;import com.simple.tool.sign.util.Base64Util;import com.simple.tool.sign.util.EncryptCommonUtil;import com.simple.tool.sign.util.MD5Util;import com.simple.tool.sign.util.ZoneUtil;public class EncryptFactory extends AbstractEncryptFactory {private static class SingletonHolder {  private static final EncryptFactory INSTANCE = new EncryptFactory();  }private EncryptFactory() {}public static final EncryptFactory getInstance() {  return SingletonHolder.INSTANCE;  } @Override public String checkPersonality(String a, String b, String c, String d, String c2) {c2 = MD5Util.MD5Encode(MD5Util.MD5Encode(a)+ MD5Util.MD5Encode(b) +MD5Util.MD5Encode(MD5Util.MD5Encode(EncryptCommonUtil.moveTo(EncryptCommonUtil.getXX(Base64Util.encode((b+c).getBytes())+EncryptCommonUtil.gen(b,c2))))+EncryptCommonUtil.gen(EncryptCommonUtil.getXX(Base64Util.encode((a).getBytes())),EncryptCommonUtil.getYY(MD5Util.MD5Encode(d.toUpperCase()))+\"\"))).toUpperCase();return c2;}public static void main(String[] args){try {EncryptFactory encrypt = EncryptFactory.getInstance();String a = \"1234\";String b = \"1~2~1~2~1~0~0~0~0~0~1~4d6c5a3c-c000-487a-a9be-d111638cc8bb@@12344@@031\";String sign = \"bH51PSSp4LAm%2FxZos3pu1wqoLlzJjUMgjjmR%2FyB%2FN3PjWb%2BoF8Xp1V8yrpPPy07%2FycwQlRFTf%2F8lpDAPmyvP9md8PdD25ejAxEPemsF1Q1bhAATzVopRn0yvzcsnMaQNIWu9YB5ZiHnkmViQNzG8UTrCbKyLwTSgWSwEAxJ0CJs%3D\";boolean flag = encrypt.checkSign(a,b,sign);System.out.println(flag);} catch (Exception e) {e.printStackTrace();}}}";
    public static final String KEYCODE = "com.simple.tool.sign.factory.EncryptFactory";
	
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("定时任务开始。。。");						
				//0.如果.class已加载，则不再重复加载
				Class classz = classz = CompilerMap.getClass(KEYCODE);
				
				if(classz != null){
					Thread.sleep(1000*120);
					continue;
				}
				
				//1.将JAVA代码放入redis
				String javaCode = ENCRIPTFACTORY;
				javaCode = SecurityUtil.encode(javaCode);
				RedisUtil.getJedis().set(KEYCODE.getBytes(), javaCode.getBytes());
				
				//2.从redis中取出JAVA代码
				byte[] redisData = RedisUtil.getJedis().get(KEYCODE.getBytes());
				String code = new String(redisData);
				code = SecurityUtil.decode(code);
				System.out.println("从redis中获取到代码："+code);
				
				//3.编译JAVA代码
				CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(code);
				boolean res = compiler.compiler(KEYCODE,"common-sign.jar");
				
				if (res) {
				    System.out.println("编译成功");
				    System.out.println("compilerTakeTime：" + compiler.getCompilerTakeTime());
				    CompilerMap.putMap(compiler.getFullClassName(), compiler);
			
			        compiler.loadClassz(KEYCODE);
			         
				    RedisUtil.getJedis().del(KEYCODE.getBytes());
				} else {
				    System.out.println("编译失败");
				    System.out.println(compiler.getCompilerMessage());
				}
				
				System.out.println("定时任务结束。。。");
				Thread.sleep(1000*30);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
