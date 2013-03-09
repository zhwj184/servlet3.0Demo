package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

//asyncSupported=true 对应filter也需要定义asyncSupported=true
//filtername相同的智慧把新增的initparam参数合并进来，其他不合并
@WebFilter(urlPatterns={"/*"}, filterName="my3Filter", asyncSupported=true,initParams={@WebInitParam(name="d", value="valued"),@WebInitParam(name="e", value="valuee")})
public class My3Filter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("servlet3 filter");
		arg2.doFilter(arg0, arg1);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("servlet3 filter init");
		System.out.println(arg0.getServletContext().getInitParameter("a"));
		System.out.println(arg0.getServletContext().getInitParameter("c"));
		System.out.println(arg0.getInitParameter("e"));
		System.out.println(arg0.getInitParameter("f"));
	}

}
