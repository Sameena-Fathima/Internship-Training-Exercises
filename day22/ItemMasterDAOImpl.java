package day22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ItemMasterDAOImpl implements ItemMasterDAO {
	Connection con;
	PreparedStatement ps;
	@Override
	public int insertItem(ItemMasterDTO itemMasterDTO){
		String query="insert into itemmaster values(?,?,?,?)";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, itemMasterDTO.getItemno());
	        ps.setString(2, itemMasterDTO.getItemname());
	        ps.setInt(3, itemMasterDTO.getPrice());
	        ps.setString(4, itemMasterDTO.getUnit());
	        ps.executeUpdate();
	        DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteItem(int itemno){
		String query="delete from itemmaster where itemNo=?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,itemno);
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateItem(ItemMasterDTO itemMasterDTO){
		String query="update itemmaster set itemName=?,price=?,unit=? where itemNo=?";
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, itemMasterDTO.getItemname());
			ps.setInt(2, itemMasterDTO.getPrice());
			ps.setString(3,itemMasterDTO.getUnit());
			ps.setInt(4,itemMasterDTO.getItemno());
			ps.executeUpdate();
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ItemMasterDTO getItemMaster(int itemno) {
		String query="select * from itemmaster where itemNo=?";
		ItemMasterDTO itemobj=new ItemMasterDTO();
		try {
			con=DBUtility.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,itemno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				itemobj.setItemno(rs.getInt(1));
				itemobj.setItemname(rs.getString(2));
				itemobj.setPrice(rs.getInt(3));
				itemobj.setUnit(rs.getString(4));
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemobj;
	}
	@Override
	public Set<ItemMasterDTO> getItemMasterAll(){
		Set<ItemMasterDTO> itemdetails=new HashSet<ItemMasterDTO>();
		Statement st;
		ItemMasterDTO itemobj;
		try {
			con=DBUtility.getConnection();
			st = con.createStatement();
			String query="select * from itemmaster";
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				itemobj=new ItemMasterDTO();
				itemobj.setItemno(rs.getInt("itemNo"));
				itemobj.setItemname(rs.getString("itemName"));
				itemobj.setPrice(rs.getInt("price"));
				itemobj.setUnit(rs.getString("unit"));
				itemdetails.add(itemobj);
			}
			DBUtility.closeConnection(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemdetails;
	}
}