package springtransaction;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Client {
	
	public static void main(String[] args) throws Exception{
		ConfigurableApplicationContext ctx=new ClassPathXmlApplicationContext("config.xml");
		
		BusinessBean bb=ctx.getBean("businessadvisedbean",BusinessBean.class);
		
		bb.doTransaction(2,1,100);
	
	}
}
