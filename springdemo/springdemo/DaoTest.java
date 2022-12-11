package springdemo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		DBDao dao = ctx.getBean("dao", DBDao.class);
		
		dao.getNoOfRows();
		System.out.println(dao.getUserById(101));
		System.out.println(dao.getAllUsers());
	}
}
