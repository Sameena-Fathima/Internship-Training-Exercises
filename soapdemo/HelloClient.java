package soapdemo;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloClient {
	public static void main(String[] args) throws Exception{
		URL url=new URL("http://localhost:2020/soapdemo/hello?wsdl");
		QName qName=new QName("http://soapdemo/","HelloServiceImplService");
		Service service=Service.create(url,qName);
		HelloService hs=service.getPort(HelloService.class);
		
		String result=hs.sayHello("Sierra");
		System.out.println(result);
}
}
