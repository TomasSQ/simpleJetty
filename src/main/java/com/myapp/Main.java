package com.myapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;

public class Main {

	public static void main(String[] args) throws Exception	{
		Server server = new Server(8123);

		WebAppContext context = new WebAppContext();

		context.setDescriptor("./src/main/webapp/WEB-INF/web.xml");
		context.setResourceBase("./src/main/webapp");
		context.setContextPath("/");

		context.setParentLoaderPriority(true);

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(false);
		resource_handler.setWelcomeFiles(new String[]{ "index.html" });
		resource_handler.setResourceBase("./src/main/webapp/");


		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, context });
		server.setHandler(handlers);

		server.start();
		server.join();
	}

}
