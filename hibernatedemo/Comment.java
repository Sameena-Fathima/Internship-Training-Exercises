package hibernatedemo;

import java.io.Serializable;

public class Comment implements Serializable{
	private String msg;
	
	public Comment(){
		
	}
	
	public Comment(String msg) {
		this.msg = msg;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
