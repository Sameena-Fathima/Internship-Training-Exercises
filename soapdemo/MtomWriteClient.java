package soapdemo;

import java.awt.Image;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class MtomWriteClient {
	public static void main(String[] args) throws Exception {
		URL url=new URL("http://localhost:3000/soapdemo/mtom?wsdl");
		QName qName=new QName("http://soapdemo/","MtomServiceImplService");
		Service service=Service.create(url,qName);
		MtomService ms=service.getPort(MtomService.class);
		BindingProvider bp = (BindingProvider) ms;
		SOAPBinding binding = (SOAPBinding) bp.getBinding();
		binding.setMTOMEnabled(true);
		Image img=ImageIO.read(new File("cat2.jpg"));
		ms.setImage(img,"cat.jpg");
	}
}