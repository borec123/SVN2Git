package com.example.producingwebservice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@WebServlet(
		  name = "ThePayMessageDispatcherServlet",
		  description = "Servlet consumich ThePay notifications",
		  urlPatterns = {"/ws/*"}
		)
public class ThePayMessageDispatcherServlet extends MessageDispatcherServlet {


	private ApplicationContext applicationContext;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//setApplicationContext(applicationContext);
		setTransformWsdlLocations(true);
		setContextClass(org.springframework.web.context.support.AnnotationConfigWebApplicationContext.class);
	    //String aa = null;
	    setContextConfigLocation("com.example.producingwebservice.WebServiceConfig");
	}

	private static final long serialVersionUID = 709383872820601205L;

	public ThePayMessageDispatcherServlet(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}


}
