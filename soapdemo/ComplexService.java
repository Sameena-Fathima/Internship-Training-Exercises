package soapdemo;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ComplexService {
	
	@WebMethod
	public void setEmployee();
	
	@WebMethod
	public void setAllEmployees();
	
	@WebMethod
	public Employee getEmployee(int eid);
	
	@WebMethod
	public List<Employee> getAllEmployees();
}
