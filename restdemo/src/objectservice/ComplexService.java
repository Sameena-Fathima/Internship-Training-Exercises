package objectservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@Path("empservice")
public class ComplexService {
	
	Employee emp;
	List<Employee> emplist=new ArrayList<>();
	
	public ComplexService() {
		emp=new Employee(1,"Sierra",2000);
		emplist.add(emp);
		emp=new Employee(2,"Sara",3000);
		emplist.add(emp);
	}
	
	
	@POST
	@Path("setemp")
	@Consumes("application/xml")
	public void setEmployee(Employee emp) {
		this.emp=emp;
		emplist.add(emp);
	}
	
//	@POST
//	@Path("setallemp")
//	@Consumes("application/json")
//	public void setAllEmployees(List<Employee> emplist)
//	{
//		this.emplist=emplist;
////		for(Employee emp:list) {
////			this.emp=emp;
////			emplist.add(emp);
////		}
//		System.out.println("new employees are added"+emplist);
//	}
//	
	
	@GET
	@Path("getemp/{eid}")
	//@Produces("application/xml")
	@Produces("application/json")
	public Employee getEmployee(@PathParam("eid") String eid) {
		Iterator<Employee> iter=emplist.iterator();
		int eidnum=Integer.parseInt(eid);
		while(iter.hasNext()) {
			Employee e=iter.next();
			if(e.getEid()==eidnum) {
				return e;
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	@GET
	@Path("getall")
	//@Produces("application/xml")
	@Produces("application/json")
	public List<Employee> getAllEmployees(){
		return this.emplist;
	}

}


@XmlRootElement(name="employee")
class Employee{
	private int eid;
	private String ename;
	private int esal;
	
	public Employee() {
		
	}
	
//	@Override
//	public String toString() {
//		return "Employee [eid=" + eid + ", ename=" + ename + ", esal=" + esal + "]";
//	}

	public Employee(int eid, String ename, int esal) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.esal = esal;
	}	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getEsal() {
		return esal;
	}
	public void setEsal(int esal) {
		this.esal = esal;
	}
	
}