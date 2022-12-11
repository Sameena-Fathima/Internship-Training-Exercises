package rmiserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import day22.ItemMasterDTO;

public class ClientHandler {
	public static void createInvoice(Invoice inv,int invno) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> itemmap=new HashMap<>();
		int custno,itemno,itemqty;
		System.out.println("Enter customer number");
		custno=Integer.parseInt(br.readLine());
		System.out.println("Enter item number and quantity");
		while(true)
		{
			System.out.println("Enter item number(enter -1 to exit)");
			itemno=Integer.parseInt(br.readLine());
			if(itemno==-1)
				break;
			System.out.println("Enter quantity");
			itemqty=Integer.parseInt(br.readLine());
			itemmap.put(itemno,itemqty);
		}
		inv.createInvoice(invno,custno,itemmap);
	}

	public static void addItem(Invoice inv) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int itemno,price;
		String itemname,unit;
		ItemMasterDTO itemobj=new ItemMasterDTO();
		while(true) {
			System.out.println("Enter item number(enter -1 to exit)");
			itemno=Integer.parseInt(br.readLine());
			if(itemno==-1)
				break;
			itemobj.setItemno(itemno);
			System.out.println("Enter item name");
			itemname=br.readLine();
			itemobj.setItemname(itemname);
			System.out.println("Enter unit");
			unit=br.readLine();
			itemobj.setUnit(unit);
			System.out.println("Enter item price");
			price=Integer.parseInt(br.readLine());
			itemobj.setPrice(price);
			inv.addItem(itemobj);
		}
	} 
}


