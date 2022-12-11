package model;

public class ItemDetails {
	int itemno;
	int itemqty;
	int itemprice;
	String itemunit;
	String itemname;
	@Override
	public String toString() {
		return "ItemDetails [itemno=" + itemno + ", itemqty=" + itemqty + ", itemprice=" + itemprice + ", itemunit="
				+ itemunit + ", itemname=" + itemname + "]";
	}
	public int getItemno() {
		return itemno;
	}
	public void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public int getItemqty() {
		return itemqty;
	}
	public void setItemqty(int itemqty) {
		this.itemqty = itemqty;
	}
	public int getItemprice() {
		return itemprice;
	}
	public void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	public String getItemunit() {
		return itemunit;
	}
	public void setItemunit(String itemunit) {
		this.itemunit = itemunit;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
}
