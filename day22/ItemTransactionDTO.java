package day22;

public class ItemTransactionDTO {
	private int invno;
	private int itemno;
	private int quantity;
	
	public final int getInvno() {
		return invno;
	}
	public final void setInvno(int invno) {
		this.invno = invno;
	}
	public final int getItemno() {
		return itemno;
	}
	public final void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public final int getItemqty() {
		return quantity;
	}
	public final void setItemqty(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "InvoiceMasterDTO [invno=" + invno + ", itemno=" + itemno + ", quantity=" + quantity + "]";
	}
	
	
}

