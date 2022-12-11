package day22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
public class InvoiceMasterDAOImpl implements InvoiceMasterDAO {
	Connection con;
	PreparedStatement ps;
	@Override
	public int insertInvoice(InvoiceMasterDTO invMasterDTO){
		String query="insert into invoicemaster values(?,?,?)";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, invMasterDTO.getInvno());
			ps.setDate(2,  java.sql.Date.valueOf(invMasterDTO.getInvdate()));
			ps.setInt(3, invMasterDTO.getCustomerno());
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	@Override
	public int deleteInvoice(int invno) {
		String query="delete from invoicemaster where invoiceNo=?";
		try {
			con=DBUtility.getConnection();
			ps= con.prepareStatement(query);
			ps.setInt(1,invno);
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updateInvoice(InvoiceMasterDTO invMasterDTO) {
		String query="update InvoiceMaster set invoiceDate = ?, customerNo = ? where invoiceNo = ?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setDate(1,  java.sql.Date.valueOf(invMasterDTO.getInvdate()));
			ps.setInt(2, invMasterDTO.getCustomerno());
			ps.setInt(3, invMasterDTO.getInvno());
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public InvoiceMasterDTO getInvoiceMaster(int invno){
		String query="select * from invoicemaster where invoiceNo=?";
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				invoiceobj.setInvno(rs.getInt(1));
				invoiceobj.setInvdate(rs.getString(2));
				invoiceobj.setCustomerno(rs.getInt(3));
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invoiceobj;
	}
	@Override
	public Set<InvoiceMasterDTO> getInvoiceMasterAll(){
		Set<InvoiceMasterDTO> invoicedetails=new HashSet<InvoiceMasterDTO>();
		InvoiceMasterDTO invoiceobj;
		Statement st;
		try {
			con=DBUtility.getConnection();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invoicedetails;
	}

}