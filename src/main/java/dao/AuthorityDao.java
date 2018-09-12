package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Authority;

public class AuthorityDao {
	public List<Authority> getAll()throws Exception{
		List<Authority> authoritys=new ArrayList<Authority>();
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from authority";
		databaseDao.query(sql);
		while(databaseDao.next()){
			Authority authority=new Authority();
			authority.setAuthorityId(databaseDao.getInteger("authorityId"));
			authority.setUserType(databaseDao.getString("userType"));
			authority.setUrl(databaseDao.getString("url"));
			authority.setParam(databaseDao.getString("param"));
			authority.setRedirectUrl(databaseDao.getString("redirectUrl"));
			authoritys.add(authority);
		}
		return authoritys;
	}
}
