package soapdemo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "soapdemo.MtomService")
@HandlerChain(file="handler-chain.xml")
public class MtomServiceImpl implements MtomService{

	@Override
	public Image getImage(String name) throws Exception {
		File fin=new File(name);
		return ImageIO.read(fin);
	}

	@Override
	public Image setImage(Image img, String name) throws Exception {
		File fos=new File(name);
		BufferedImage bi=(BufferedImage) img;
		ImageIO.write(bi, "jpg", fos);
		return null;
	}

}
