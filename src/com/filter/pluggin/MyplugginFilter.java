package com.filter.pluggin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyplugginFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("MyplugginFilter destroy");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("MyplugginFilter filter");
		arg2.doFilter(arg0, arg1);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
//		arg0.getInitParameter("");
		System.out.println("MyplugginFilter init");
		
	}

}
