package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ItemTransactionDAOImpl implements ItemTransactionDAO,Cloneable{
	private static ItemTransactionDAOImpl itemtransactionmasterdaoimpl;
	private Properties prop;
	private ItemTransactionDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ItemTransactionDAOImpl getItemTransactionDAOImpl(Properties prop) {
		
		if(itemtransactionmasterdaoimpl==null) {
			itemtransactionmasterdaoimpl=new ItemTransactionDAOImpl(prop);
			return itemtransactionmasterdaoimpl;
		}
		else {
			return itemtransactionmasterdaoimpl.createClone();
		}
	}
	public ItemTransactionDAOImpl createClone() {
		try {
			return (ItemTransactionDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	@Override
	public int insertItem(ItemTransactionDTO itemTransactionDTO){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="insert into itemtransaction values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
	     	ps.setInt(1,itemTransactionDTO.getInvno());
			ps.setInt(2, itemTransactionDTO.getItemno());
			ps.setInt(3,itemTransactionDTO.getItemqty());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public int deleteItem(int invno,int itemno){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="delete from itemtransaction where invoiceNo=? and itemNo=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,invno);
			ps.setInt(2,itemno);
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public int updateItem(ItemTransactionDTO itemTransactionDTO) {
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="update itemtransaction set quantity=? where invoiceNo=? and itemNo=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,itemTransactionDTO.getItemqty());
			ps.setInt(2, itemTransactionDTO.getInvno());
			ps.setInt(3, itemTransactionDTO.getItemno());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public ItemTransactionDTO getItemTransaction(int invno){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="select * from itemtransaction where invoiceNo=?";
			ItemTransactionDTO itemobj=new ItemTransactionDTO();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemobj.setInvno(rs.getInt(1));
				itemobj.setItemno(rs.getInt(2));
				itemobj.setItemqty(rs.getInt(3));
				
			}
			DBUtility.closeConnection(null);
			return itemobj;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
		
		
	}

	@Override
	public Set<ItemTransactionDTO> getItemTransactionAll(){
		try {
			Connection con=DBUtility.getConnection(prop);
			Set<ItemTransactionDTO> itemtransactiondetails=new HashSet<ItemTransactionDTO>();
			Statement st;
			ItemTransactionDTO itemtransactionobj;
			String query="select * from itemtransaction";
			st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				itemtransactionobj=new ItemTransactionDTO();
				itemtransactionobj.setInvno(rs.getInt("invoiceNo"));
				itemtransactionobj.setItemno(rs.getInt("itemNo"));
				itemtransactionobj.setItemqty(rs.getInt("quantity"));
				itemtransactiondetails.add(itemtransactionobj);
			}
			DBUtility.closeConnection(null);

			return itemtransactiondetails;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}

	}

	@Override
	public Set<ItemTransactionDTO> getItemTransactionByInvoiceNo(int invno) {
		try {
			Connection con=DBUtility.getConnection(prop);
			Set<ItemTransactionDTO> itemtransactiondetails=new HashSet<ItemTransactionDTO>();
			String query="select * from itemtransaction where invoiceNo=?";
			ItemTransactionDTO itemtransactionobj;
			PreparedStatement ps=con.prepareStatement(query);
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
			return itemtransactiondetails;
			} catch (Exception e) {
				DBUtility.closeConnection(e);
				return null;
			}
	}
	@Override
	public Set<ItemDetails> getItemDetails(int invno) {
		try {
			Set<ItemDetails> itemdetailsset=new HashSet<ItemDetails>();
			String query="select itemtransaction.itemNo,quantity,itemName,price,unit from itemtransaction,itemmaster where itemmaster.itemNo in(SELECT itemNo from itemmaster where itemtransaction.itemNo=itemmaster.itemNo) and invoiceNo=?";
			Connection con=DBUtility.getConnection(prop);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, invno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ItemDetails itemdetails=new ItemDetails();
				itemdetails.setItemno(rs.getInt("itemNo"));
				itemdetails.setItemqty(rs.getInt("quantity"));
				itemdetails.setItemname(rs.getString("itemName"));
				itemdetails.setItemprice(rs.getInt("price"));
				itemdetails.setItemunit(rs.getString("unit"));
				itemdetailsset.add(itemdetails);
			}
			DBUtility.closeConnection(null);
			return itemdetailsset;
		}catch(Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
	}
}
