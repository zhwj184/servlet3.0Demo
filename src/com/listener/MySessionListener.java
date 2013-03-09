package com.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

// 

public class MySessionListener implements HttpSessionBindingListener, HttpSessionAttributeListener, HttpSessionActivationListener{

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("Session valueBound " + arg0.getName() + arg0.getSession().getId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("Session valueUnbound " + arg0.getName() + arg0.getSession().getId());
		
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("Session sessionDidActivate " + arg0.getSource() + arg0.getSession().getId());
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("Session sessionWillPassivate " + arg0.getSource() + arg0.getSession().getId());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("Session attributeAdded " + arg0.getSource() + arg0.getSession().getId());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("Session attributeRemoved " + arg0.getSource() + arg0.getSession().getId());
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("Session attributeReplaced " + arg0.getSource() + arg0.getSession().getId());
	}

}
