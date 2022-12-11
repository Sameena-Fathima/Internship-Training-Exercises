package model;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.LoginServiceImpl;

public class LoginAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("uname");
		String password=request.getParameter("upass");
		HttpSession session=request.getSession();
		Properties prop=(Properties) request.getServletContext().getAttribute("properties");
		LoginService loginservice=LoginServiceImpl.getLoginServiceImpl(prop);
		if(loginservice.checkUser(username, password))
		{
			if(loginservice.checkFlag(username))
			{
				int custno=loginservice.getcustno(username);
				session.setAttribute("name", username);
				session.setAttribute("custno", custno);
				loginservice.updateFlag(username,1);
				return "login.success";
			}
			else
			{
				return "login.already";
			}
		}	
		else
			return "login.failure";
	}

}
