package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import day22.ItemMasterDTO;

public interface Invoice extends Remote{
	public void createInvoice(int invno,int custno,Map<Integer,Integer> map)throws RemoteException;
	public void addItem(ItemMasterDTO item)throws RemoteException;
	public void calculateDate(int invno)throws RemoteException;
	public void createPDF(int invno)throws RemoteException;
	public void createExcel(int invno)throws RemoteException;
}
