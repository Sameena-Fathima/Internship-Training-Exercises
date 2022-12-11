package soapdemo;

import java.awt.Image;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class MtomReadClient {
	public static void main(String[] args) throws Exception {
		URL url=new URL("http://localhost:3000/soapdemo/mtom?wsdl");
		QName qName=new QName("http://soapdemo/","MtomServiceImplService");
		Service service=Service.create(url,qName);
		MtomService ms=service.getPort(MtomService.class);
		
		Image image=ms.getImage("cat2.jpg");
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		frame.setVisible(true);
	}
}
