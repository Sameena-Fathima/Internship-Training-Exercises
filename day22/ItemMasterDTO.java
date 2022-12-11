package day22;

import java.io.Serializable;

public class ItemMasterDTO implements Serializable{
	private int itemno;
	private String itemname;
	private int price;
	private String unit;
	
	public int getItemno() {
		return itemno;
	}
	public void setItemno(int itemno) {
		this.itemno = itemno;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString() {
		return "ItemMasterDTO [itemno=" + itemno + ", itemname=" + itemname + ", price=" + price + ", unit=" + unit
				+ "]";
	}

}
