package soapdemo;

import javax.xml.ws.Endpoint;

import soapdemo.MtomServiceImpl;

public class MtomServicePublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:3000/soapdemo/mtom", new MtomServiceImpl());
		System.out.println("Service published");
	}

}
