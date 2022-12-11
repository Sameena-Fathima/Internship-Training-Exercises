package day22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class CustomerMasterDAOImpl implements CustomerMasterDAO{
	Connection con;
	PreparedStatement ps;
	@Override
	public int insertCustomer(CustomerMasterDTO customerMasterDTO){
		String query="insert into customermaster  values(?,?,?,?,?)";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, customerMasterDTO.getCustno());
	        ps.setString(2, customerMasterDTO.getCustname());
	        ps.setString(3, customerMasterDTO.getCustaddress());
	        ps.setString(4, customerMasterDTO.getCustemail());
	        ps.setString(5, customerMasterDTO.getCustphoneno());
	        ps.executeUpdate();
	        DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteCustomer(int custno) {
		String query="delete from customermaster where customerNo=?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,custno);
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateCustomer(CustomerMasterDTO customerMasterDTO){
		String query="update customermaster set customerName=?,customerAddress=?,customerEmail=?,customerPhoneNo=? where customerNo=?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,customerMasterDTO.getCustname());
			ps.setString(2, customerMasterDTO.getCustaddress());
			ps.setString(3, customerMasterDTO.getCustemail());
			ps.setString(4, customerMasterDTO.getCustphoneno());
			ps.setInt(5,customerMasterDTO.getCustno());
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public CustomerMasterDTO getCustomerMaster(int custno){
		String query="select * from customermaster where customerNo=?";
		CustomerMasterDTO custobj=new CustomerMasterDTO();
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,custno);
			ResultSet rs=ps.executeQuery();
	
			while(rs.next()) {
				custobj.setCustno(rs.getInt(1));
				custobj.setCustname(rs.getString(2));
				custobj.setCustaddress(rs.getString(3));
				custobj.setCustemail(rs.getString(4));
				custobj.setCustphoneno(rs.getString(5));
				}
			DBUtility.closeConnection(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return custobj;
	}

	@Override
	public Set<CustomerMasterDTO> getCustomerMasterAll(){
		Set<CustomerMasterDTO> customerdetails=new HashSet<CustomerMasterDTO>();
		Statement st;
		CustomerMasterDTO customerobj;
		try {
			con=DBUtility.getConnection();
			st = con.createStatement();
			String query="select * from customermaster";
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				customerobj=new CustomerMasterDTO();
				customerobj.setCustno(rs.getInt("customerNo"));
				customerobj.setCustname(rs.getString("customerName"));
				customerobj.setCustemail(rs.getString("customerEmail"));
				customerobj.setCustaddress(rs.getString("customerAddress"));
				customerobj.setCustphoneno(rs.getString("customerPhoneNo"));
				customerdetails.add(customerobj);				
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return customerdetails;
	}
}
