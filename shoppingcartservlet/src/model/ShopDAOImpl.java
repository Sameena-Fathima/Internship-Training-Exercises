package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Set;

public class ShopDAOImpl implements ShopDAO{
	private static ShopDAOImpl shopdaoimpl;
	private Properties prop;
	private ShopDAOImpl(Properties prop) {
		this.prop=prop;
	}
	
	synchronized public static ShopDAOImpl getShopDAOImpl(Properties prop){
		if(shopdaoimpl==null)
		{
			shopdaoimpl=new ShopDAOImpl(prop);
			return shopdaoimpl;
		}
		else
			return shopdaoimpl.createClone();
	}
	
	public ShopDAOImpl createClone() {
		try {
			return (ShopDAOImpl)super.clone();
		}catch(Exception e) {
			return null;
		}
	}
	@Override
	public int insertShopCategory(ShopDTO shopdto) {
		
		return 0;
	}

	@Override
	public int updateShopCategory(ShopDTO shopdto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteShopCategory(int shopid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ShopDTO getShopDTO(int shopid) {
		try {
			Connection con=DBUtility.getConnection(prop);
			ShopDTO shopdto=new ShopDTO();
			PreparedStatement ps=con.prepareStatement("select * from shopcategories where shopid=?");
			ps.setInt(1, shopid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				shopdto.setShopid(rs.getInt(1));
				shopdto.setShopname(rs.getString(2));
			}
			DBUtility.closeConnection(null);
			return shopdto;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Set<ShopDTO> getAllShopDTO() {
		// TODO Auto-generated method stub
		return null;
	}

}
