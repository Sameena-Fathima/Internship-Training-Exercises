package service;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import model.components.*;
import model.TotalDetails;
import model.CustomerMasterDAO;
import model.CustomerMasterDAOImpl;
import model.CustomerMasterDTO;
import model.InvoiceMasterDAO;
import model.InvoiceMasterDAOImpl;
import model.InvoiceMasterDTO;
import model.ItemDetails;
import model.ItemMasterDAO;
import model.ItemMasterDAOImpl;
import model.ItemMasterDTO;
import model.ItemTransactionDAO;
import model.ItemTransactionDAOImpl;
import model.ItemTransactionDTO;

public class ShoppingServiceImpl implements ShoppingService,Cloneable{
	Properties prop;
	public ShoppingServiceImpl(Properties prop) {
		this.prop=prop;
	}
	private static ShoppingServiceImpl shoppingServiceImpl;
	
	public static ShoppingServiceImpl getShoppingServiceImpl(Properties prop) {
		if(shoppingServiceImpl==null) {
			shoppingServiceImpl=new ShoppingServiceImpl(prop);
			return shoppingServiceImpl;
		}
		else {
			return shoppingServiceImpl.createClone();
		}
	}
	public ShoppingServiceImpl createClone() {
		try {
			return (ShoppingServiceImpl)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Set<ItemMasterDTO> getAllItems(String category) {
		Set<ItemMasterDTO> itemdetails=new HashSet<ItemMasterDTO>();
		ItemMasterDAO itemDao=ItemMasterDAOImpl.getItemMasterDAOImpl(prop);
		itemdetails=itemDao.getItemMasterAll(category);
		return itemdetails;
	}
	@Override
	public Set<ItemMasterDTO> buyItems(Set<ItemTransactionDTO> ItemTransactionDTOset) {
		ItemTransactionDAO itemtransactiondao=ItemTransactionDAOImpl.getItemTransactionDAOImpl(prop);
		Set<ItemMasterDTO> items=new HashSet<ItemMasterDTO>();
		for(ItemTransactionDTO ItemTransactionDTO:ItemTransactionDTOset) {
		itemtransactiondao.insertItem(ItemTransactionDTO);
		ItemMasterDTO itemmasterdto=new ItemMasterDTO();
		ItemMasterDAO itemmasterdao=ItemMasterDAOImpl.getItemMasterDAOImpl(prop);
		itemmasterdto=itemmasterdao.getItemMaster(ItemTransactionDTO.getItemno());
		items.add(itemmasterdto);
		}
		return items;
	}
	@Override
	public int getInvNo(int custno,String dateinstring) {
		InvoiceMasterDAO invoicemaster=InvoiceMasterDAOImpl.getInvoiceMasterDAOImpl(prop);
		InvoiceMasterDTO invmasterdto=new InvoiceMasterDTO();
		invmasterdto.setCustomerno(custno);
		invmasterdto.setInvdate(dateinstring);
		invoicemaster.insertInvoice(invmasterdto);
		int invno=invoicemaster.getInvno(custno);
		return invno;
	}
	@Override
	public CustomerMasterDTO getCustomerDetails(int customerno) {
		CustomerMasterDAO customerdao=CustomerMasterDAOImpl.getCustomerMasterDAOImpl(prop);
		CustomerMasterDTO customerdto=new CustomerMasterDTO();
		customerdto=customerdao.getCustomerMaster(customerno);
		return customerdto;
	}
	@Override
	public Set<ItemDetails> getItemDetails(int invno) {
		ItemTransactionDAO itemtransactionmasterdao=ItemTransactionDAOImpl.getItemTransactionDAOImpl(prop);
		Set<ItemDetails> itemdetails=new HashSet<ItemDetails>(); 
		itemdetails=itemtransactionmasterdao.getItemDetails(invno);
		return itemdetails;
	}
	
	@Override
	public String getInvdate(int invno) {
		InvoiceMasterDAO invoicemasterdao=InvoiceMasterDAOImpl.getInvoiceMasterDAOImpl(prop);
		String date=invoicemasterdao.getDate(invno);
		return date;
	}
	@Override
	public void createPDF(int invno,String invdate) {
		String xmlfilepath="C:\\Users\\samee\\eclipse-workspace\\ShoppingCart\\invoice.xml";
		XmlToPDFConverter xc=new XmlToPDFConverter();
		try {
		xc.WriteToPDF(xmlfilepath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void createExcel(int invno,String invdate) {
		try {
			String xmlfilepath="C:\\Users\\samee\\eclipse-workspace\\ShoppingCart\\invoice.xml";
			XmlToExcelConverter xmlobj=new XmlToExcelConverter();
			xmlobj.WriteToExcel(xmlfilepath);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void createXML(int invno, TotalDetails totaldetails) {
		CreateXML.generateXML(invno,totaldetails);
	}
	@Override
	public void sendMail(int invno, TotalDetails totaldetails) {
		MailService ms=new MailService();
		try {
			ms.writeToMail(invno, totaldetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void sendSMS(TotalDetails totaldetails) {
		String phoneno="+91"+totaldetails.getCustomermasterdto().getCustphoneno();
		Smsservice.sendSMS(phoneno,totaldetails);
		
	}


}