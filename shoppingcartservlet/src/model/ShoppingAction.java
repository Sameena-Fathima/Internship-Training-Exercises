package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ShoppingService;
import service.ShoppingServiceImpl;

public class ShoppingAction extends Action{
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	String nextshopcategory=request.getParameter("nextid");
	String shopcategory=request.getParameter("category");
	HttpSession session=request.getSession();
	Properties prop=(Properties)request.getServletContext().getAttribute("properties");
	ShoppingService shoppingservice=ShoppingServiceImpl.getShoppingServiceImpl(prop);
	HashMap<Integer,Integer> map;
	if(session.getAttribute("selecteditems")==null) {
		map=new HashMap<Integer,Integer>();
	}
	else {
		 map=(HashMap<Integer, Integer>) session.getAttribute("selecteditems");
	}
	if(shopcategory!=null) {
		String[] selectedItems = request.getParameterValues("choice");
		if(selectedItems!=null) {
				for(int i=0;i<selectedItems.length;i++) {
						String qty=request.getParameter(selectedItems[i]);
						map.put(Integer.parseInt(selectedItems[i]),Integer.parseInt(qty));
				}
				session.setAttribute("selecteditems", map);
		}
		
	}
	Set<ItemMasterDTO> items=new HashSet<ItemMasterDTO>();
	items=shoppingservice.getAllItems(nextshopcategory);
	session.setAttribute("items", items);
	//session.setAttribute(nextshopcategory, items);
	return nextshopcategory;
}
}
