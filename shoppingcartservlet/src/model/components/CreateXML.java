package model.components;

import java.io.File;
import java.io.FileWriter;

import model.ItemDetails;
import model.TotalDetails;


public class CreateXML {
	public static void generateXML(int invno,TotalDetails totaldetails) {

		System.out.println("generating xml");
		StringBuilder sb=new StringBuilder();
		sb.append("<!DOCTYPE invoice SYSTEM \"invoice.dtd\">");
		sb.append("<invoice>");
		sb.append("<invoiceno>"+invno+"</invoiceno>");
		sb.append("<invoicedate>"+totaldetails.getInvdate()+"</invoicedate>");
		sb.append("<customer>");
		sb.append("<customerno>"+totaldetails.getCustomermasterdto().getCustno()+"</customerno>"+"<customername>"+totaldetails.getCustomermasterdto().getCustname()+"</customername>"+
				"<customeraddress>"+totaldetails.getCustomermasterdto().getCustaddress()+"</customeraddress>"+"<customeremail>"+totaldetails.getCustomermasterdto().getCustemail()+"</customeremail>"+
				"<customerphno>"+totaldetails.getCustomermasterdto().getCustphoneno()+"</customerphno>");
		sb.append("</customer>");
		
		sb.append("<items>");

		for(ItemDetails item:totaldetails.getItemdetails())
		{
			sb.append("<item>");
			sb.append("<itemno>"+item.getItemno()+"</itemno>"+"<itemname>"+item.getItemname()+"</itemname>"+
					"<unit>"+item.getItemunit()+"</unit>"+"<price>"+item.getItemprice()+"</price>"+"<quantity>"+item.getItemqty()+"</quantity>"+
					"<total>"+item.getItemprice()*item.getItemqty()+"</total>");
			sb.append("</item>");
		}
		sb.append("</items>");
		sb.append("</invoice>");
		try {
			File file=new File("C:\\Users\\samee\\eclipse-workspace\\ShoppingCart\\invoice.xml");
			FileWriter writer=new FileWriter(file);
			writer.write(sb.toString());
			writer.close();
			System.out.println("xml created...");
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
