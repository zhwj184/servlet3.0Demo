package com.filter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter{
	
	private AtomicInteger count = new AtomicInteger(0);
	
	private int cnt = 0;

	@Override
	public void destroy() {
		System.out.println("testfilter destroy");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//logs 记录请求处理
		//权限验证
	    //事务处理
		//编码转换
		//流量限制
		//统计
		int cnt = this.count.addAndGet(1);
		if(cnt > 10){
			return ;
		}
		
//		synchronized (cnt) {
//			cnt++;
//		}
		//cnt++;  cnt = cnt + 1;  A: 2, 3, 3 B: 2 3 3 
		
		System.out.println("myFilter");
		arg2.doFilter(arg0, arg1);
		//
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
//		arg0.getInitParameter("");
		System.out.println("testfilter init");
		
	}

}
