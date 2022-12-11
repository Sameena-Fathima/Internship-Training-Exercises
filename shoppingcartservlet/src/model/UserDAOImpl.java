package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Set;

public class UserDAOImpl implements UserDAO,Cloneable{
	private static UserDAOImpl userdaoimpl;
	private Properties prop;
	//private Connection con;
	private UserDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static UserDAOImpl getUserDAOImpl(Properties prop){
		if(userdaoimpl==null)
		{
			userdaoimpl=new UserDAOImpl(prop);
			return userdaoimpl;
		}
		else
			return userdaoimpl.createClone();
	}
	
	public UserDAOImpl createClone() {
		try {
			return (UserDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	@Override
	public int insertUser(UserDTO userdto) {
		try {
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?,?)");
			ps.setInt(1, userdto.getUserid());
			ps.setString(2, userdto.getUsername());
			ps.setString(3, userdto.getUserpass());
			ps.setInt(4, userdto.getFlag());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		}
		catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public int updateUser(UserDTO userdto) {
		try {
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps=con.prepareStatement("update users set username=?,password=?,flag=? where userid=?");
			ps.setString(1, userdto.getUsername());
			ps.setString(2, userdto.getUserpass());
			ps.setInt(3, userdto.getFlag());
			ps.setInt(4, userdto.getUserid());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		}
		catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public int deleteUser(int userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDTO getUserDTO(int userid) {
		try {
			Connection con=DBUtility.getConnection(prop);
			UserDTO userdto=new UserDTO();
			PreparedStatement ps=con.prepareStatement("select * from users where userid=?");
			ps.setInt(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				userdto.setUserid(rs.getInt(1));
				userdto.setUsername(rs.getString(2));
				userdto.setUserpass(rs.getString(3));
				userdto.setFlag(rs.getInt(4));
			}
			DBUtility.closeConnection(null);
			return userdto;
		}catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
	}

	@Override
	public Set<UserDTO> getAllUserDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserByName(String username) {
		try {
			Connection con=DBUtility.getConnection(prop);
			UserDTO userdto=new UserDTO();
			PreparedStatement ps=con.prepareStatement("select * from users where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				userdto.setUserid(rs.getInt(1));
				userdto.setUsername(rs.getString(2));
				userdto.setUserpass(rs.getString(3));
				userdto.setFlag(rs.getInt(4));
			}
			DBUtility.closeConnection(null);
			return userdto;
		}catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
		
	}
	@Override
	public int getcustNo(String uname) {
		try {
			int custno=0;
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps=con.prepareStatement("select userid from users where username=?");
			ps.setString(1, uname);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				custno=rs.getInt(1);
			}
			return custno;
		}catch(Exception e) {
			e.printStackTrace();
			DBUtility.closeConnection(e);
			return 0;
		}
	}
	
	
}
