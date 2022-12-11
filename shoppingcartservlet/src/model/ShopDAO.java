package model;

import java.util.Set;

public interface ShopDAO {
	public int insertShopCategory(ShopDTO shopdto);
	public int updateShopCategory(ShopDTO shopdto);
	public int deleteShopCategory(int shopid);
	public ShopDTO getShopDTO(int shopid);
	public Set<ShopDTO>  getAllShopDTO();

}
