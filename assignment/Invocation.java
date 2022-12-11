package assignment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Invocation {
	public static void main(String[] args) throws Exception{
		Object xmlobj=new Invocation();
		String xmlfilepath="src/assignment/invoice.xml";
		xmlobj=Proxy.newProxyInstance(Invocation.class.getClassLoader(), new Class[] {XmlToExcel.class,XmlToPDF.class,XmlToMail.class}, new MyInvocationHandler(new Object[] {new XmlToExcelConverter(),new XmlToPDFConverter(),new MailService()}));
		XmlToExcel conv_to_excel=(XmlToExcel)xmlobj;
		conv_to_excel.WriteToExcel(xmlfilepath);
		
		XmlToPDF conv_to_pdf=(XmlToPDF)xmlobj;
		conv_to_pdf.WriteToPDF(xmlfilepath);
		
		XmlToMail send_mail=(XmlToMail)xmlobj;
		send_mail.writeToMail(xmlfilepath);	
	
	}

		
}
class MyInvocationHandler implements InvocationHandler
{
	Object obj[];
	public MyInvocationHandler(Object obj[]) {
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object object = null;
		
		for(Object o: obj) {
			Method methods[] = o.getClass().getDeclaredMethods();
			for(Method m: methods) {
				if(m.getName().equals(method.getName())) {
					m.setAccessible(true);
					object = method.invoke(o, args);
				}
			}
		}
		return object;
	}
}