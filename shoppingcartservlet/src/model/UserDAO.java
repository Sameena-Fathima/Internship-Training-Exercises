package model;

import java.util.Set;

public interface UserDAO {
	public int insertUser(UserDTO userdto);
	public int updateUser(UserDTO userdto);
	public int deleteUser(int userid);
	public UserDTO getUserDTO(int userid);
	public Set<UserDTO>  getAllUserDTO();
	public UserDTO getUserByName(String username);
	public int getcustNo(String uname);
}
