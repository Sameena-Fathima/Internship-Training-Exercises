package model;

import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import service.LoginService;
import service.LoginServiceImpl;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
public class MySessionListener implements HttpSessionListener {

   
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("session created....");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("session destroyed...");
        HttpSession session=se.getSession();
        Properties prop=(Properties)session.getServletContext().getAttribute("properties");
        System.out.println(prop.toString());
        LoginService loginservice=LoginServiceImpl.getLoginServiceImpl(prop);
        Object name=session.getAttribute("name");
        if(name!=null) {
        	String uname=(String)name;
    		loginservice.updateFlag(uname, 0);
        }
    }
	
}
