package com.context;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;

@WebListener(value="my3Context listener")
public class My3Context implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		  System.out.println("my3Context contextDestroyed on " +  new Date() + ".");    
		  System.out.println(arg0.getServletContext().getInitParameter("key3"));
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 System.out.println("my3Context contextInitialized on " +   new Date() + ".");    
		 System.out.println(arg0.getServletContext().getInitParameter("key3"));
	}

}
