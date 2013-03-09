package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingleThreadServlet extends HttpServlet implements SingleThreadModel{

	static{
		System.out.println("erery request will print this");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -500396323384183163L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().write("hello, world ");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("SingleThreadServlet init");
		super.init();
	}

}
