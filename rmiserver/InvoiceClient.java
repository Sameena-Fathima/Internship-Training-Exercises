package rmiserver;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InvoiceClient {
	public static void main(String[] args) throws Exception{
		Invoice inv=(Invoice)Naming.lookup("rmi://localhost:1099/myinvoiceapp");
		Scanner sc=new Scanner(System.in);
		System.out.println("Please select your choice...");
		System.out.println("1-Create new Invoice");
		System.out.println("2-Calculate Delivery date and time");
		System.out.println("3-Create Excel");
		System.out.println("4-Create PDF");
		System.out.println("5-Enter new items");
		int invno=0;
		System.out.println("Enter choice");
		int choice=sc.nextInt();
		if(choice!=5)
		{
			System.out.println("Enter invoice number");
			invno=sc.nextInt();
		}
		switch(choice) {
		case 1:{
			ClientHandler.createInvoice(inv,invno);
			break;
		}
		case 2:{
			inv.calculateDate(invno);	
			break;
		}
		case 3:{
			inv.createExcel(invno);
			break;
		}
		case 4:{
			inv.createPDF(invno);
			break;
		}
		case 5:{
			ClientHandler.addItem(inv);
			break;
		}
		
		default:{
			System.out.println("Invalid choice...");
			break;
		}
		}
	}
}