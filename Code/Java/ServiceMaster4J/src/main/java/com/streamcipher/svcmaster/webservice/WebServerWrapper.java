package com.streamcipher.svcmaster.webservice;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.streamcipher.svcmaster.config.DependencyWebService;

public class WebServerWrapper implements IWebServerWrapper {

    private static final Logger log = Logger.getLogger(WebServerWrapper.class);
    private Server webServer;
        
    @Override
    public void start(int port) {
	webServer = new Server(port);
	final ServletHolder servletHolder = new ServletHolder(new CXFServlet());
	final ServletContextHandler context = new ServletContextHandler();
	context.setContextPath("/");
	context.addServlet(servletHolder, "/rest/*");
	context.addEventListener(new ContextLoaderListener());
	context.addEventListener(new RequestContextListener());
	context.setInitParameter("contextClass",
		AnnotationConfigWebApplicationContext.class.getName());
	context.setInitParameter("contextConfigLocation",
		DependencyWebService.class.getName());

	webServer.setHandler(context);
	try {
	    webServer.start();
	    // webServer.join();
	} catch (Exception e) {
	    log.error("Could not start up web server on port: " + port, e);
	}

    }

    @Override
    public void shutDown() {
	try {
	    // webServer.join();
	    webServer.stop();
	} catch (Exception e) {
	    log.error("Could not stop web server", e);
	}

    }

}
