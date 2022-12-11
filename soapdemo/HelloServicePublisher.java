package soapdemo;

import javax.xml.ws.Endpoint;

public class HelloServicePublisher {
	public static void main(String[] args) {
		
		Endpoint.publish("http://localhost:2020/soapdemo/hello",new HelloServiceImpl());
		System.out.println("Service published...");
	}
}
