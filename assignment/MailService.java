package assignment;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



interface XmlToMail{
	public void writeToMail(String xmlfilepath) throws Exception;
}

public class MailService implements XmlToMail{	
	public void writeToMail(String xmlfilepath)  throws Exception{
		final String host = "smtp.gmail.com";
		final int port = 587;
		final String email_from = "YOUR_MAIL_ID";
		final String password = "PASSWORD";
		final String email_to="RECIPIENT_MAIL_ID";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email_from, password);
            }
        });
		
        	
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email_from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_to));
        message.setSubject("Invoice Details");
        
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
	    dbf.setValidating(true);
	      
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    org.w3c.dom.Document doc=db.parse(xmlfilepath);
		  
		org.w3c.dom.Element rootElement=doc.getDocumentElement();

            
        StringBuilder email = new StringBuilder();
        email.append("<html><body>"+"<table style='border:2px'>");
        email.append("<tr>");
        email.append("<th colspan='3'>Name:Sierra</th> ");
        email.append("<th>Invoice No: 1 </th>");
        email.append("<th>Invoice Date: 12/03/2021 </th>");
        email.append("</tr>");
        
        email.append("<tr>");
        email.append("<th>S.No</th> ");
        email.append("<th>Item Name</th> ");
        email.append("<th>Price</th>");
        email.append("<th>Quantity</th>");
        email.append("<th>Amount</th>");
        email.append("</tr>");
        
        
        
        for(int i=0;i<rootElement.getChildNodes().getLength();i++) {
        	  email.append("<tr>");
			  for(int j=0;j<rootElement.getChildNodes().item(i).getChildNodes().getLength();j++) {
				  email.append("<td>"+rootElement.getChildNodes().item(i).getChildNodes().item(j).getFirstChild().getNodeValue()+"</td>");
			  }
			  email.append("</tr>");
        }
        email.append("</table></body></html>");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(email.toString(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        
        System.out.println("Mail sent...");
        
	}
}