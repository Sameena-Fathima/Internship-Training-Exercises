package day22;

import java.util.Set;

public interface ItemTransactionDAO {
	public int insertItem(ItemTransactionDTO itemTransactionDTO);
	public int deleteItem(int invno,int itemno);
	public int updateItem(ItemTransactionDTO itemTransactionDTO);
	public  ItemTransactionDTO getItemTransaction(int invno);
	public Set<ItemTransactionDTO> getItemTransactionAll();
	public Set<ItemTransactionDTO> getItemTransactionByInvoiceNo(int invno);

}
