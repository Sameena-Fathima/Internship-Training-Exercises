package assignment;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

class SmsService{
	 public static void main(String[] args) 
	 {
		final String ACCOUNT_SID = "YOUR_ACCOUNT_SID";
		final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";
     	final String recipient="RECIPIENT_NUMBER";
     	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
         String message = "Invoice has been generated";
         com.twilio.rest.api.v2010.account.Message messageobj = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(recipient),
     	        new PhoneNumber("+12244707635"), 
     	        message).create();
         System.out.println(messageobj.getSid());
     }
}