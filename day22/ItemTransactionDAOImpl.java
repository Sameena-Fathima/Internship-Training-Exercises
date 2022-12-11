package day22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ItemTransactionDAOImpl implements ItemTransactionDAO{
	Connection con;
	PreparedStatement ps;
	@Override
	public int insertItem(ItemTransactionDTO itemTransactionDTO){
		String query="insert into itemtransaction values(?,?,?)";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,itemTransactionDTO.getInvno());
			ps.setInt(2, itemTransactionDTO.getItemno());
			ps.setInt(3,itemTransactionDTO.getItemqty());
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteItem(int invno,int itemno){
		String query="delete from itemtransaction where invoieNo=? and itemNo=?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,invno);
			ps.setInt(2,itemno);
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateItem(ItemTransactionDTO itemTransactionDTO) {
		String query="update itemtransaction set quantity=? where invoiceNo=? and itemNo=?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,itemTransactionDTO.getItemqty());
			ps.setInt(2, itemTransactionDTO.getInvno());
			ps.setInt(3, itemTransactionDTO.getItemno());
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ItemTransactionDTO getItemTransaction(int invno){
		String query="select * from itemtransaction where invoiceNo=?";
		ItemTransactionDTO itemobj=new ItemTransactionDTO();
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemobj.setInvno(rs.getInt(1));
				itemobj.setItemno(rs.getInt(2));
				itemobj.setItemqty(rs.getInt(3));
				
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemobj;
	}

	@Override
	public Set<ItemTransactionDTO> getItemTransactionAll(){
		Set<ItemTransactionDTO> itemtransactiondetails=new HashSet<ItemTransactionDTO>();
		Statement st;
		ItemTransactionDTO itemtransactionobj;
		try {
			con=DBUtility.getConnection();
			st = con.createStatement();
			String query="select * from itemtransaction";
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				itemtransactionobj=new ItemTransactionDTO();
				itemtransactionobj.setInvno(rs.getInt("invoiceNo"));
				itemtransactionobj.setItemno(rs.getInt("itemNo"));
				itemtransactionobj.setItemqty(rs.getInt("quantity"));
				itemtransactiondetails.add(itemtransactionobj);
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemtransactiondetails;
	}

	@Override
	public Set<ItemTransactionDTO> getItemTransactionByInvoiceNo(int invno) {
		Set<ItemTransactionDTO> itemtransactiondetails=new HashSet<ItemTransactionDTO>();
		String query="select * from itemtransaction where invoiceNo=?";
		ItemTransactionDTO itemtransactionobj;
		try {
			con=DBUtility.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemtransactionobj=new ItemTransactionDTO();
				itemtransactionobj.setInvno(rs.getInt("invoiceNo"));
				itemtransactionobj.setItemno(rs.getInt("itemNo"));
				itemtransactionobj.setItemqty(rs.getInt("quantity"));
				itemtransactiondetails.add(itemtransactionobj);
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
				e.printStackTrace();
		}

		return itemtransactiondetails;
	}
}
