package model.components;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import model.ItemDetails;
import model.TotalDetails;

public class Smsservice {
	public static void sendSMS(String to_number,TotalDetails totaldetails) {
	final String ACCOUNT_SID = "YOUR_ACCOUNT-SID";
	final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";
	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	String message="";
	message="Hi "+totaldetails.getCustomermasterdto().getCustname()+"\n";
	StringBuilder sb=new StringBuilder();
	sb.append("Here is your invoice details"+"\n");
	sb.append("Items:"+"\n");
	for (ItemDetails id : totaldetails.getItemdetails()) {
       sb.append("Item No:"+id.getItemno()+"\n");       
       sb.append("Item Name: "+id.getItemname()+"\n"
       		+ "Item Price: "+id.getItemprice()+"\n"+"Item Quantity: "+id.getItemqty()+"\n"+"Item Unit: "+
    		   id.getItemunit()+"\n"+"Item Total: "+id.getItemqty()*id.getItemprice());
       sb.append("\n");
  
	}
	message=message+sb.toString();

	com.twilio.rest.api.v2010.account.Message messageobj = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(to_number),
	        new PhoneNumber("+12244707635"), 
	        message).create();
		    System.out.println(messageobj.getSid());
	}
}
