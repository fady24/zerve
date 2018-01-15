package server;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;

import handlers.webhandler;

public class serversetup {

	private static Server server = null;
	private static ServerConnector connector = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startserver();
	}

	public static void startserver(){
		server = new Server();
		connector = new ServerConnector(server);
		connector.setPort(24);
		
		ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(webhandler.class, "/web");
		
		HandlerList handlers =  new HandlerList();
		handlers.setHandlers(new Handler[] { handler,
				new DefaultHandler() });
		
		server.setHandler(handlers);
		server.addConnector(connector);
		
		
		try{
			
			server.start();
			server.join();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
