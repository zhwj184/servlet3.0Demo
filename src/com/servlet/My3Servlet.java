package com.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class annotation
 */
@WebServlet(urlPatterns={"/annotation"},initParams={@WebInitParam(name="f", value="valuef"),@WebInitParam(name="g", value="valueg")})
@ServletSecurity(httpMethodConstraints = {@HttpMethodConstraint(value = "GET", rolesAllowed = "R1",transportGuarantee=TransportGuarantee.CONFIDENTIAL),
		@HttpMethodConstraint(value = "POST", rolesAllowed = "R1",transportGuarantee = TransportGuarantee.CONFIDENTIAL)})
public class My3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public My3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("hello, world");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("hello, world");
	}

}
