package controller;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Action;

public class RequestProcessor {
	
	public void process(HttpServletRequest req,HttpServletResponse resp)
	{
		try {
				ServletContext ctxt=req.getServletContext();
				String path=(String)ctxt.getAttribute("path");
				Properties prop=new Properties();
				prop.load(new FileInputStream(path));
				String id=req.getParameter("formid");
				String actionclass=prop.getProperty(id);
				model.Action action=(Action)Class.forName(actionclass).getConstructor().newInstance();
				String result=action.execute(req, resp);
				String nextPage=prop.getProperty(result);
				RequestDispatcher rd=req.getRequestDispatcher(nextPage);
				rd.forward(req, resp);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
