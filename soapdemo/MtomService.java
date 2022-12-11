package soapdemo;

import java.awt.Image;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MtomService {
	@WebMethod
	public Image getImage(String name) throws Exception;
	
	@WebMethod
	public Image setImage(Image img,String name) throws Exception;
}