package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported=true,name="asyncServlet", urlPatterns="/async")
public class AsyncServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3903580630389463919L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().write("hello, async test");
		resp.getWriter().println("start£º"+new Date()+".<br/>"); 
		resp.getWriter().flush();
		final AsyncContext async = req.startAsync(req,resp);
		async.setTimeout(20000);
		async.addListener(new AsyncListener() {
			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				System.out.println("onTimeout");
				System.out.println(arg0.getThrowable());
			}
			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				System.out.println("onStartAsync");
				
			}
			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				System.out.println("onError");
				System.out.println(arg0.getThrowable());
			}
			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				System.out.println("onComplete");
				System.out.println(arg0.getThrowable());
			}
		});
		async.start(new Runnable() {
			@Override
			public void run() {
				ServletRequest request = async.getRequest();
				int i = 0;
				while(i++<19){
					try {
						Thread.sleep(1000);
						async.getResponse().getWriter().write("aync thread processing" + i);
						async.getResponse().getWriter().flush();
						async.getResponse().getWriter().println("async end£º"+new Date()+".<br/>"); 
						async.getResponse().getWriter().flush();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				async.complete();
			}
		});
		resp.getWriter().println("end£º"+new Date()+".<br/>"); 
		resp.getWriter().flush();

	}

}
