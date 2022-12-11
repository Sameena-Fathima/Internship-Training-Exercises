package soapdemo;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class EmployeeClient {
	public static void main(String[] args) throws Exception{
		URL url=new URL("http://localhost:2030/soapdemo/employee?wsdl");
		QName qName=new QName("http://soapdemo/","ComplexServiceImplService");
		Service service=Service.create(url,qName);
		ComplexService os=service.getPort(ComplexService.class);
		
		os.setEmployee();
		os.setAllEmployees();
		System.out.println(os.getEmployee(1).toString());
		System.out.println(os.getAllEmployees().toString());
	}
}
