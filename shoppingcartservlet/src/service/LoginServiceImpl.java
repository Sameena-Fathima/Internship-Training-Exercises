package service;

import java.util.Properties;

import model.UserDAO;
import model.UserDAOImpl;
import model.UserDTO;

public class LoginServiceImpl implements LoginService,Cloneable{
	private static LoginServiceImpl loginServiceImpl;
	Properties prop;
	public LoginServiceImpl(Properties prop) {
		this.prop=prop;
	}
	
	public static LoginServiceImpl getLoginServiceImpl(Properties prop) {
		if(loginServiceImpl==null) {
			loginServiceImpl=new LoginServiceImpl(prop);
			return loginServiceImpl;
		}
		else {
			return loginServiceImpl.createClone();
		}
	}
	public LoginServiceImpl createClone() {
		try {
			return (LoginServiceImpl)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean checkUser(String username, String userpass) {
		UserDAOImpl userdaoimpl=UserDAOImpl.getUserDAOImpl(prop);
		UserDTO userdto=userdaoimpl.getUserByName(username);
		if(userdto!=null)
		{
			if(userdto.getUserpass().equalsIgnoreCase(userpass))
			{
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean checkFlag(String username) {
		UserDAO userdao=UserDAOImpl.getUserDAOImpl(prop);
		UserDTO userdto=userdao.getUserByName(username);
		if(userdto!=null)
		{
			if(userdto.getFlag()==0)
			{
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public int updateFlag(String username, int flag) {
		UserDAO userdao=UserDAOImpl.getUserDAOImpl(prop);
		UserDTO userdto=userdao.getUserByName(username);
		if(userdto!=null)
		{
			userdto.setFlag(flag);
			return userdao.updateUser(userdto);	
		}
		return 0;
	}

	@Override
	public int registerUser(UserDTO userdto) {
		UserDAO userdao=UserDAOImpl.getUserDAOImpl(prop);
		return userdao.insertUser(userdto);
	}
	@Override
	public int getcustno(String uname) {
		UserDAO userDao=UserDAOImpl.getUserDAOImpl(prop);
		return userDao.getcustNo(uname);	}

}
