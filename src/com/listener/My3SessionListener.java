package com.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

// 

@WebListener(value="my3SessionListener")
public class My3SessionListener implements HttpSessionBindingListener, HttpSessionAttributeListener, HttpSessionActivationListener{

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("my3SessionListener valueBound " + arg0.getName() + arg0.getSession().getId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("my3SessionListener valueUnbound " + arg0.getName() + arg0.getSession().getId());
		
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("my3SessionListener sessionDidActivate " + arg0.getSource() + arg0.getSession().getId());
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("my3SessionListener sessionWillPassivate " + arg0.getSource() + arg0.getSession().getId());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("my3SessionListener attributeAdded " + arg0.getSource() + arg0.getSession().getId());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("my3SessionListener attributeRemoved " + arg0.getSource() + arg0.getSession().getId());
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("my3SessionListener attributeReplaced " + arg0.getSource() + arg0.getSession().getId());
	}

}
