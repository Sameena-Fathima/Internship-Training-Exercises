package model;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;
import service.LoginServiceImpl;

public class RegisterAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("uname");
		String password=request.getParameter("upass");
		Properties prop=(Properties) request.getServletContext().getAttribute("properties");
		UserDTO userdto=new UserDTO();
		userdto.setUsername(username);
		userdto.setUserpass(password);
		userdto.setFlag(0);
		LoginService loginservice=new LoginServiceImpl(prop);
		loginservice.registerUser(userdto);
		return "register.success";
	}
	
}
