package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ActionServlet extends HttpServlet {
	
	RequestProcessor rp;Properties prop;
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext ctx=config.getServletContext();
			String filename=config.getInitParameter("config");
			String path=ctx.getRealPath(filename);
			ctx.setAttribute("path", path);
			String dbfilename=config.getInitParameter("dbconfig");
			String dbpath=ctx.getRealPath(dbfilename);
			//ctx.setAttribute("dbpath", path);
		
			prop=new Properties();
			prop.load(new FileInputStream(dbpath));
			Class.forName(prop.getProperty("driver"));
			ctx.setAttribute("properties", prop);
			this.rp=new RequestProcessor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rp.process(request,response);
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req,resp);
}

}
