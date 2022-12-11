package soapdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService(endpointInterface = "soapdemo.ComplexService")
@HandlerChain(file = "handler-chain.xml")
public class ComplexServiceImpl implements ComplexService{
	
	Employee emp;
	List<Employee> emplist=new ArrayList<>();
	int eid,esal;
	String ename;
	Scanner sc=new Scanner(System.in);
	
	@WebMethod
	@Override
	public void setEmployee() {
		System.out.println("Enter employee id:");
		eid=sc.nextInt();
		System.out.println("Enter employee name:");
		ename=sc.next();
		System.out.println("Enter employee salary:");
		esal=sc.nextInt();
		Employee obj=new Employee();
		obj.setEid(eid);
		obj.setEname(ename);
		obj.setEsal(esal);
		emplist.add(obj);
		
	}
	
	@WebMethod
	@Override
	public void setAllEmployees()
	{
		System.out.println("Enter employee id(Enter -1 to exit):");
		eid=sc.nextInt();
		while(eid!=-1)
		{
			System.out.println("Enter employee name:");
			ename=sc.next();
			System.out.println("Enter employee salary:");
			esal=sc.nextInt();
			Employee obj=new Employee();
			obj.setEid(eid);
			obj.setEname(ename);
			obj.setEsal(esal);
			emplist.add(obj);
			System.out.println("Enter employee id(Enter -1 to exit):");
			eid=sc.nextInt();
		}
		
		System.out.println("new employees are added"+emplist);
	}
	
	
	@WebMethod
	public Employee getEmployee(int eid) {
		Iterator<Employee> iter=emplist.iterator();
		while(iter.hasNext()) {
			Employee e=iter.next();
			if(e.getEid()==eid) {
				return e;
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	@WebMethod
	public List<Employee> getAllEmployees(){
		return emplist;
		
	}	

}


