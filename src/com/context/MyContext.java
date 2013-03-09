package com.context;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContext implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		  System.out.println("Context contextDestroyed on " +  new Date() + ".");    
		  System.out.println(arg0.getServletContext().getInitParameter("cona"));
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 System.out.println("Context contextInitialized on " +   new Date() + ".");    
		 System.out.println(arg0.getServletContext().getInitParameter("cona"));
		 //��֤���������Ƿ���ȷ
	}

}
