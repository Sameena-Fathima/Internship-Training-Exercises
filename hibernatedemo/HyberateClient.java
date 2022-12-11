package hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HyberateClient {
	public static void main(String[] args) {
		BookDTO bookdto=new BookDTO(1,"Harry Potter","J.K Rowling",500);
		
		Comment comment=new Comment();
		comment.setMsg("Good");
		
		bookdto.setComment(comment);
		
		//Step1 - Load Configuration
		Configuration cfg=new Configuration();

		//Step 2 - Build SessionFactory
		SessionFactory factory=cfg.configure().buildSessionFactory();
		
		//Step 3 - Create a session (Local Cache - Client specific)
		Session session=factory.openSession();
		
		//To make the DTO hibernate aware, ensure that you call save method of session with the DTO object.
		session.save(bookdto);
		
		session.beginTransaction().commit();
	}
	
	
}
