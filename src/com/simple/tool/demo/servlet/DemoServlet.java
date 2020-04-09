package com.simple.tool.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simple.tool.demo.thread.TimingThread;
import com.simple.tool.demo.util.SignUtil;


@WebServlet("/demo.do")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String codeKey = "com.simple.tool.sign.factory.EncryptFactory";
	private static final String methodName = "checkSign";
       
   
    public DemoServlet() {
        super();
    }
    
	@Override
	public void init() throws ServletException {
		new Thread(new TimingThread()).start();//启动定时任务
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = "111";
		String b = "222";
		String sign = "Gvnr%2BIHNSIW87WYVK0ojoQkluvNBA5zSCssy0TPk6m2inywuACgeyrIwMlEj3ZEmpXDRsNCG%2BAQP1KlQ%2F9LSiTF%2FOLzoR4tyvGQSG1OyOwXskeW57mEPStLTR1RD1C1zTOVzit4cvAPIxlJQCR9T0BdyMpLfddETRWOrBczmcNE%3D";

		boolean flag = false;
		try {
	        flag = SignUtil.checkSign(codeKey, a,b,sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
						
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append("{\"code\": \"0\",\"message\": \"处理成功\",\"data\": \""+flag+"\"}");
	
	}

}
