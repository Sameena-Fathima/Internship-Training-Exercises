package day22;

public class Main {
	public static void main(String[] args) {
		Main obj=new Main();
		System.out.println("------------Invoice details--------");
		obj.invoice();
		System.out.println("------------Customer details--------");
		obj.customer();
		System.out.println("------------Item details--------");
		obj.item();
		System.out.println("------------Item transaction details--------");
		obj.itemtransaction();
	}
	
	public void invoice(){
		InvoiceMasterDTO invoiceobj=new InvoiceMasterDTO();
		InvoiceMasterDAOImpl invoiceimplobj=new InvoiceMasterDAOImpl();
		invoiceobj.setInvno(1);
		invoiceobj.setInvdate("2021-03-12");
		invoiceobj.setCustomerno(100);
		invoiceimplobj.insertInvoice(invoiceobj);
		
		invoiceobj.setInvno(2);
		invoiceobj.setInvdate("2021-03-16");
		invoiceobj.setCustomerno(102);
		invoiceimplobj.insertInvoice(invoiceobj);
		
		System.out.println(invoiceimplobj.getInvoiceMasterAll());
		System.out.println(invoiceimplobj.getInvoiceMaster(1));
		
		InvoiceMasterDTO invoiceupdateobj=new InvoiceMasterDTO();
		invoiceupdateobj.setInvno(1);
		invoiceupdateobj.setInvdate("2021-03-17");
		invoiceupdateobj.setCustomerno(101);
		invoiceimplobj.updateInvoice(invoiceupdateobj);
		invoiceimplobj.deleteInvoice(2);
		System.out.println(invoiceimplobj.getInvoiceMasterAll());
		
	}
	
	public void customer(){
		CustomerMasterDTO custobj = new CustomerMasterDTO();
		CustomerMasterDAOImpl custimplobj = new CustomerMasterDAOImpl();
		custobj.setCustno(101);
		custobj.setCustname("Riya");
		custobj.setCustemail("riya@gmail.com");
		custobj.setCustaddress("Chennai");
		custobj.setCustphoneno("9234567890");
		custimplobj.insertCustomer(custobj);
		
		custobj.setCustno(102);
		custobj.setCustname("Reema");
		custobj.setCustemail("reema@gmail.com");
		custobj.setCustaddress("Chennai");
		custobj.setCustphoneno("9456789001");
		custimplobj.insertCustomer(custobj);
		
		System.out.println(custimplobj.getCustomerMasterAll());
		
		CustomerMasterDTO custupdateobj=new CustomerMasterDTO();
		custupdateobj.setCustno(101);
		custupdateobj.setCustname("Rita");
		custupdateobj.setCustemail("rita@gmail.com");
		custupdateobj.setCustaddress("Chennai");
		custupdateobj.setCustphoneno("9111111111");
		custimplobj.updateCustomer(custupdateobj);
		
		System.out.println(custimplobj.getCustomerMaster(101));
	}
	
	public void item(){
		ItemMasterDTO itemobj=new ItemMasterDTO();
		ItemMasterDAOImpl itemimplobj=new ItemMasterDAOImpl();
		itemobj.setItemno(1);
		itemobj.setItemname("Maaza");
		itemobj.setPrice(80);
		itemobj.setUnit("1.5 liters");
		itemimplobj.insertItem(itemobj);
		
		itemobj.setItemno(2);
		itemobj.setItemname("Bovonto");
		itemobj.setPrice(65);
		itemobj.setUnit("1.5 liters");
		itemimplobj.insertItem(itemobj);
		
		System.out.println(itemimplobj.getItemMaster(1));
		System.out.println(itemimplobj.getItemMasterAll());
	}
	
	public void itemtransaction() {
		ItemTransactionDTO itemtransobj=new ItemTransactionDTO();
		ItemTransactionDAOImpl itemtransimplobj=new ItemTransactionDAOImpl();
		itemtransobj.setInvno(1);
		itemtransobj.setItemno(1);
		itemtransobj.setItemqty(2);
		itemtransimplobj.insertItem(itemtransobj);
		
		System.out.println(itemtransimplobj.getItemTransactionByInvoiceNo(1));
	}
}

