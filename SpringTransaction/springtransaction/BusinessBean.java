package springtransaction;

public interface BusinessBean {
	public void doTransaction(int creditaccountid,int debitaccountid, int amt)throws Exception;
}
