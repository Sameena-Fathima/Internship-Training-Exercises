package springdemo;

import java.io.Serializable;

public class UserDTO implements Serializable{
	private int id;
	private String name;
	private String pass;
	private int flag;
	
	public UserDTO() {
		
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", pass=" + pass + ", flag=" + flag + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}