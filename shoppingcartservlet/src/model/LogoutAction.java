package model;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.LoginServiceImpl;

public class LogoutAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		String uname=(String)session.getAttribute("name");
		Properties prop=(Properties)request.getServletContext().getAttribute("properties");
		LoginService loginservice=LoginServiceImpl.getLoginServiceImpl(prop);
		loginservice.updateFlag(uname, 0);
		
		return "logout.success";
	}

}
