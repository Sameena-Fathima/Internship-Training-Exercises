package model;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ShoppingService;
import service.ShoppingServiceImpl;

public class GeneratePDFAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Creating PDF");
		Properties prop=(Properties)request.getServletContext().getAttribute("properties");
		HttpSession session=request.getSession();
		ShoppingService shoppingService=ShoppingServiceImpl.getShoppingServiceImpl(prop);
		TotalDetails totaldetails=(TotalDetails) session.getAttribute("totaldetails");
		shoppingService.createXML((int)session.getAttribute("invno"),totaldetails);
		shoppingService.createPDF((int)session.getAttribute("invno"),totaldetails.getInvdate());
		return "shop3";
	}

}
