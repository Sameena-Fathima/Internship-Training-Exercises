package rmiserver;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;


//import rmiserver.db.InvoiceMasterDAOImpl;
//import rmiserver.db.InvoiceMasterDTO;

import day22.CustomerMasterDAOImpl;
import day22.CustomerMasterDTO;
import day22.InvoiceMasterDAOImpl;
import day22.InvoiceMasterDTO;
import day22.InvoiceMasterDTO;
import day22.ItemMasterDAOImpl;
import day22.ItemMasterDTO;
import day22.ItemTransactionDAOImpl;
import day22.ItemTransactionDTO;


public class CreateXML {
	public static void generateXML(int invno) {
		File file;
		FileWriter writer;
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		InvoiceMasterDAOImpl invoiceimplobj=new InvoiceMasterDAOImpl();
		invoiceobj=invoiceimplobj.getInvoiceMaster(invno);
		
		ItemTransactionDAOImpl itemimplobj=new ItemTransactionDAOImpl();
		Set<ItemTransactionDTO> itemdetails=new HashSet<ItemTransactionDTO>();
		itemdetails=itemimplobj.getItemTransactionByInvoiceNo(invno);

		ItemMasterDTO itemmasterobj=new ItemMasterDTO();
		ItemMasterDAOImpl itemmasterimplobj=new ItemMasterDAOImpl();
		
		
		CustomerMasterDTO customerobj=new CustomerMasterDTO();
		CustomerMasterDAOImpl custimplobj=new CustomerMasterDAOImpl();
		customerobj=custimplobj.getCustomerMaster(invoiceobj.getCustomerno());
		
		StringBuilder sb=new StringBuilder();
		sb.append("<!DOCTYPE invoice SYSTEM \"invoice.dtd\">");
		sb.append("<invoice>");
		sb.append("<invoiceno>"+invno+"</invoiceno>");
		sb.append("<invoicedate>"+invoiceobj.getInvdate()+"</invoicedate>");
		sb.append("<customer>");
		sb.append("<customerno>"+customerobj.getCustno()+"</customerno>"+"<customername>"+customerobj.getCustname()+"</customername>"+
				"<customeraddress>"+customerobj.getCustaddress()+"</customeraddress>"+"<customeremail>"+customerobj.getCustemail()+"</customeremail>"+
				"<customerphno>"+customerobj.getCustphoneno()+"</customerphno>");
		sb.append("</customer>");
		
		sb.append("<items>");

		for(ItemTransactionDTO item:itemdetails)
		{
			sb.append("<item>");
			itemmasterobj=itemmasterimplobj.getItemMaster(item.getItemno());
			sb.append("<itemno>"+item.getItemno()+"</itemno>"+"<itemname>"+itemmasterobj.getItemname()+"</itemname>"+
					"<unit>"+itemmasterobj.getUnit()+"</unit>"+"<price>"+itemmasterobj.getPrice()+"</price>"+"<quantity>"+item.getItemqty()+"</quantity>"+
					"<total>"+itemmasterobj.getPrice()*item.getItemqty()+"</total>");
			sb.append("</item>");
		}
		sb.append("</items>");
		sb.append("</invoice>");
		try {
			file=new File("src/rmiserver/invoice.xml");
			writer=new FileWriter(file);
			writer.write(sb.toString());
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
