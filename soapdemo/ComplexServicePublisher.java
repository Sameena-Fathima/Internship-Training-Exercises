package soapdemo;

import javax.xml.ws.Endpoint;

import soapdemo.MtomServiceImpl;

public class ComplexServicePublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:2030/soapdemo/employee", new ComplexServiceImpl());
		System.out.println("Service published");
	}

}
