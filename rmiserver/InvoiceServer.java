package rmiserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Properties;

import assignment.XmlToExcelConverter;
import day22.CustomerMasterDAOImpl;
import day22.CustomerMasterDTO;
import day22.InvoiceMasterDAOImpl;
import day22.InvoiceMasterDTO;
import day22.ItemMasterDAOImpl;
import day22.ItemMasterDTO;
import day22.ItemTransactionDAOImpl;
import day22.ItemTransactionDTO;

public class InvoiceServer extends UnicastRemoteObject implements Invoice{

	public InvoiceServer() throws RemoteException {
	}
	
	@Override
	public void createInvoice(int invno, int custno,Map<Integer,Integer> itemmap) throws RemoteException {
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		InvoiceMasterDAOImpl invoiceimplobj=new InvoiceMasterDAOImpl();
		ItemTransactionDTO itemobj=new ItemTransactionDTO();
		ItemTransactionDAOImpl itemimplobj=new ItemTransactionDAOImpl();
		invoiceobj.setInvno(invno);
		invoiceobj.setInvdate(String.valueOf(java.time.LocalDate.now()));
		invoiceobj.setCustomerno(custno);
		invoiceimplobj.insertInvoice(invoiceobj);
		for(Integer key: itemmap.keySet()) {
			itemobj.setInvno(invno);
			itemobj.setItemno(key);
			itemobj.setItemqty(itemmap.get(key));
			itemimplobj.insertItem(itemobj);
		}			
	}
		

	@Override
	public void addItem(ItemMasterDTO item) throws RemoteException {
		System.out.println("Adding items...");
		ItemMasterDAOImpl itemimplobj=new ItemMasterDAOImpl();
		itemimplobj.insertItem(item);
		System.out.println("Items added successfully...");
	}

	@Override
	public void createPDF(int invno) throws RemoteException {
		System.out.println("Creating PDF...");
		try {
			CreateXML.generateXML(invno);
			XmlToPDFConverter obj=new XmlToPDFConverter();		
			obj.WriteToPDF("src/rmiserver/invoice.xml");
			System.out.println("PDF created successfully...");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void createExcel(int invno) throws RemoteException {
		System.out.println("Creating Excel...");
		try {
			CreateXML.generateXML(invno);
			XmlToExcelConverter obj=new XmlToExcelConverter();		
			obj.WriteToExcel("src/rmiserver/invoice.xml");
			System.out.println("Excel created successfully...");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void calculateDate(int invno) throws RemoteException {
		InvoiceMasterDAOImpl invimplobj=new InvoiceMasterDAOImpl();
		InvoiceMasterDTO invobj=invimplobj.getInvoiceMaster(invno);
		CustomerMasterDAOImpl  custimplobj=new CustomerMasterDAOImpl();
		CustomerMasterDTO custobj=custimplobj.getCustomerMaster(invobj.getCustomerno());
		int speed=40;
		String invdate=invobj.getInvdate();
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream("src/rmiserver/location.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String location=custobj.getCustaddress();
		int distance=Integer.parseInt(prop.getProperty(location));
		Consignment cons=new Consignment();
		String date=cons.getDeliveryInfo(distance,speed,invdate);
		System.out.println("Delivery date: "+date);
	}

}