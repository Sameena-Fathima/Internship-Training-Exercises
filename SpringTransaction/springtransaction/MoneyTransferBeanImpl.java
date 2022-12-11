package springtransaction;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
/*
 * create table accounts (accid int(5),uname varchar(40),amount int(5));
 * insert into accounts values (1,'ramu',5000);
 * insert into accounts values (2,'somu',10000);
 */
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class MoneyTransferBeanImpl implements MoneyTransferBean{
	private DataSource datasource;
	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public void credit(int accid, int amt) throws Exception {
		JdbcTemplate jdbc=new JdbcTemplate(datasource);
		Integer oldamount=jdbc.queryForObject("select amount from accounts where accountid=?",
				new Object[] {accid},Integer.class);
		int newamt=oldamount+amt;
		
		NamedParameterJdbcTemplate njdbc=new NamedParameterJdbcTemplate(datasource);
		
		MapSqlParameterSource mps=new MapSqlParameterSource();
		mps.addValue("amt", newamt);
		mps.addValue("id", accid);
		njdbc.update("update accounts set amount=:amt where accountid=:id", mps);
	}

	@Override
	public void debit(int accid, int amt) throws Exception {
		JdbcTemplate jdbc=new JdbcTemplate(datasource);
		Integer oldamount=jdbc.queryForObject("select amount from accounts where accountid=?",
				new Object[] {accid},Integer.class);
		if(oldamount<amt) {
			throw new InsufficientBalanceException("The user does not have enough balance...");
		}
		int newamount=oldamount-amt;
		NamedParameterJdbcTemplate njdbc=new NamedParameterJdbcTemplate(datasource);
		
		MapSqlParameterSource mps=new MapSqlParameterSource();
		mps.addValue("amt", newamount);
		mps.addValue("id", accid);
		njdbc.update("update accounts set amount=:amt where accountid=:id", mps);
		
	}

}
