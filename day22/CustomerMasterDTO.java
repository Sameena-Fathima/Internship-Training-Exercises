package day22;

import java.io.Serializable;

public class CustomerMasterDTO implements Serializable{
	private int custno;
	private String custname;
	private  String custaddress;
	private String custemail;
	private String custphoneno;
	public int getCustno() {
		return custno;
	}
	public void setCustno(int custno) {
		this.custno = custno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustaddress() {
		return custaddress;
	}
	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}
	public String getCustemail() {
		return custemail;
	}
	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}
	public String getCustphoneno() {
		return custphoneno;
	}
	public void setCustphoneno(String custphoneno) {
		this.custphoneno = custphoneno;
	}
	
	@Override
	public String toString() {
		return "CustomerMasterDTO [custno=" + custno + ", custname=" + custname + ", custaddress=" + custaddress
				+ ", custemail=" + custemail + ", custphoneno=" + custphoneno + "]";
	}
}
