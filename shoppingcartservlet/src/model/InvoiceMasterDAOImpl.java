package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
public class InvoiceMasterDAOImpl implements InvoiceMasterDAO,Cloneable{
	private static InvoiceMasterDAOImpl invoicemasterdaoimpl;
	private Properties prop;
	private InvoiceMasterDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static InvoiceMasterDAOImpl getInvoiceMasterDAOImpl(Properties prop) {
		
		if(invoicemasterdaoimpl==null) {
			 invoicemasterdaoimpl=new InvoiceMasterDAOImpl(prop);
			return  invoicemasterdaoimpl;
		}
		else {
			return invoicemasterdaoimpl.createClone();
		}
	}
	public InvoiceMasterDAOImpl createClone() {
		try {
			return (InvoiceMasterDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	@Override
	public int insertInvoice(InvoiceMasterDTO invMasterDTO){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="insert into invoicemaster(invoiceDate,customerNo) values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1,  java.sql.Date.valueOf(invMasterDTO.getInvdate()));
			ps.setInt(2, invMasterDTO.getCustomerno());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
		
	}
	@Override
	public int deleteInvoice(int invno) {
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="delete from invoicemaster where invoiceNo=?";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setInt(1,invno);
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}
	@Override
	public int updateInvoice(InvoiceMasterDTO invMasterDTO) {
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="update invoicemaster set invoiceDate = ?, customerNo = ? where invoiceNo = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1,  java.sql.Date.valueOf(invMasterDTO.getInvdate()));
			ps.setInt(2, invMasterDTO.getCustomerno());
			ps.setInt(3, invMasterDTO.getInvno());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}
	@Override
	public InvoiceMasterDTO getInvoiceMaster(int invno){
		String query="select * from invoicemaster where invoiceNo=?";
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		try {
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				invoiceobj.setInvno(rs.getInt(1));
				invoiceobj.setInvdate(rs.getString(2));
				invoiceobj.setCustomerno(rs.getInt(3));
			}
			DBUtility.closeConnection(null);
			return invoiceobj;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
		
		
	}
	@Override
	public Set<InvoiceMasterDTO> getInvoiceMasterAll(){
		try {
			Connection con=DBUtility.getConnection(prop);
			Set<InvoiceMasterDTO> invoicedetails=new HashSet<InvoiceMasterDTO>();
			InvoiceMasterDTO invoiceobj;
			Statement st;
			st = con.createStatement();
			String query="select * from invoicemaster";
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				invoiceobj=new InvoiceMasterDTO();
				invoiceobj.setInvno(rs.getInt("invoiceNo"));
				invoiceobj.setInvdate(String.valueOf(rs.getDate("invoiceDate")));
				invoiceobj.setCustomerno(rs.getInt("customerNo"));
				invoicedetails.add(invoiceobj);
			}
			DBUtility.closeConnection(null);
			return invoicedetails;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
		
	}
	@Override
	public String getDate(int invno) {
		try {
			String date="";
			Connection con=DBUtility.getConnection(prop);
			String query="select invoiceDate from invoicemaster where invoiceNo=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, invno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				date=rs.getString("invoiceDate");
			}
			return date;
			
		}catch(Exception e) {}
		return null;
	}

	@Override
	public int getInvno(int custno) {
		String query="select * from invoicemaster where customerNo=?";
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		try {
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,custno);
			int invno=0;
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				invno=rs.getInt("invoiceNo");
			}
			return invno;
		}
		catch(Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}
	

}