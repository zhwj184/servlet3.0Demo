package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.listener.MySessionListener;

public class MySerlvet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1056987768220312316L;
	
	private int inita = 0;
	
	MySessionListener sb = new MySessionListener();//����һ������������    
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int a = 123;
		//cookie
		if(request.getCookies() != null){
			for(Cookie cookie: request.getCookies()){
				response.getWriter().write(cookie.getName() + cookie.getValue());
			}	
		}
		System.out.println(request.getSession().getId());
		System.out.println(request.isRequestedSessionIdFromCookie());
		System.out.println(request.isRequestedSessionIdFromURL());
		
		//dispatcher
//		response.getWriter().write(request.getDispatcherType().name());
//		request.getRequestDispatcher("/myRedirectServlet").forward(request, response);
		
		//request.getServletContext().getRequestDispatcher(arg0);
//		request.getServletContext().getNamedDispatcher(arg0);
		
		
		
		
		response.getWriter().write("hello, world " + inita);
		HttpSession session = request.getSession(true);//���Ȼ����Ҫ�����������session����req��HttpRequest����    

		session.putValue("BinderObject",sb);//�������������session�У��Ӵ�ʱ��ʼִ��sb��valueBound����  
		session.removeValue("BinderObject");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.inita = Integer.valueOf(config.getInitParameter("inita"));
		System.out.println("myservlet init");
		long cona = Long.parseLong(config.getServletContext().getInitParameter("cona"));
		System.out.println("context init values" + cona);
	}
	
	@Override
	public void destroy() {
		System.out.println("myservlet destroy");
		super.destroy();
	}

}
