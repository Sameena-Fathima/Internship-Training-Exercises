package rest;

import java.net.URL;
import java.net.URLConnection;

public class Client {
	public static void main(String[] args) throws Exception{
		URL url=new URL("http://localhost:2021/restdemo/rest/test");
		URLConnection urlcon=url.openConnection();
		urlcon.getInputStream();	
	}
}
