package day22;

import java.io.Serializable;

public class InvoiceMasterDTO implements Serializable{
	private int invno;
	private String invdate;
	private int customerno;
	public final int getInvno() {
		return invno;
	}
	public final void setInvno(int invno) {
		this.invno = invno;
	}
	public final String getInvdate() {
		return invdate;
	}
	public final void setInvdate(String invdate) {
		this.invdate = invdate;
	}
	public final int getCustomerno() {
		return customerno;
	}
	public final void setCustomerno(int customerno) {
		this.customerno = customerno;
	}
	
	@Override
	public String toString() {
		return "InvoiceMasterDTO [invno=" + invno + ", invdate=" + invdate + ", customerno=" + customerno + "]";
	}
	
	
}