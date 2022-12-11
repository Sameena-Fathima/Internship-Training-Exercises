package model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ItemMasterDAOImpl implements ItemMasterDAO,Cloneable {
	private static ItemMasterDAOImpl itemmasterdaoimpl;
	private Properties prop;
	PreparedStatement ps;
	private ItemMasterDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ItemMasterDAOImpl getItemMasterDAOImpl(Properties prop){
		if(itemmasterdaoimpl==null)
		{
			itemmasterdaoimpl=new ItemMasterDAOImpl(prop);
			return itemmasterdaoimpl;
		}
		else
			return itemmasterdaoimpl.createClone();
	}
	
	public ItemMasterDAOImpl createClone() {
		try {
			return (ItemMasterDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	@Override
	public int insertItem(ItemMasterDTO itemMasterDTO){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="insert into itemmaster values(?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, itemMasterDTO.getItemno());
	        ps.setString(2, itemMasterDTO.getItemname());
	        ps.setString(3, itemMasterDTO.getCategory());
	        ps.setInt(4, itemMasterDTO.getPrice());
	        ps.setString(5, itemMasterDTO.getUnit());
	        ps.setString(6, itemMasterDTO.getImage());
	        int count=ps.executeUpdate();
	        DBUtility.closeConnection(null);
	        return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public int deleteItem(int itemno){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="delete from itemmaster where itemNo=?";
			ps = con.prepareStatement(query);
			ps.setInt(1,itemno);
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
		}
		return 0;
	}

	@Override
	public int updateItem(ItemMasterDTO itemMasterDTO){
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="update itemmaster set itemName=?,category=?,price=?,unit=?,imageUrl=? where itemNo=?";
			ps = con.prepareStatement(query);
			ps.setString(1, itemMasterDTO.getItemname());
			ps.setString(2, itemMasterDTO.getCategory());
			ps.setInt(3, itemMasterDTO.getPrice());
			ps.setString(4,itemMasterDTO.getUnit());
			ps.setString(5, itemMasterDTO.getImage());
			ps.setInt(6,itemMasterDTO.getItemno());
			int count=ps.executeUpdate();
			DBUtility.closeConnection(null);
			return count;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return 0;
		}
	}

	@Override
	public ItemMasterDTO getItemMaster(int itemno) {
		try {
			Connection con=DBUtility.getConnection(prop);
			String query="select * from itemmaster where itemNo=?";
			ItemMasterDTO itemobj=new ItemMasterDTO();
			ps = con.prepareStatement(query);
			ps.setInt(1,itemno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemobj.setItemno(rs.getInt(1));
				itemobj.setItemname(rs.getString(2));
				itemobj.setCategory(rs.getString(3));
				itemobj.setPrice(rs.getInt(4));
				itemobj.setUnit(rs.getString(5));
				itemobj.setImage(rs.getString(6));
			}
			DBUtility.closeConnection(null);
			return itemobj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	@Override
	public Set<ItemMasterDTO> getItemMasterAll(String category){
		Set<ItemMasterDTO> itemdetails=new HashSet<ItemMasterDTO>();
		Statement st;
		ItemMasterDTO itemobj;
		try {
			Connection con=DBUtility.getConnection(prop);
			st = con.createStatement();
			String query="select * from itemmaster where category=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, category);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemobj=new ItemMasterDTO();
				itemobj.setItemno(rs.getInt("itemNo"));
				itemobj.setItemname(rs.getString("itemName"));
				itemobj.setCategory(rs.getString("category"));
				itemobj.setPrice(rs.getInt("price"));
				itemobj.setUnit(rs.getString("unit"));
				itemobj.setImage(rs.getString("imageUrl"));
				itemdetails.add(itemobj);
			}
			DBUtility.closeConnection(null);
			return itemdetails;
		} catch (Exception e) {
			DBUtility.closeConnection(e);
			return null;
		}
		
	}
	
}