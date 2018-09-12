package service;

import java.util.List;

import bean.Authority;
import dao.AuthorityDao;

public class AuthorityService {
	public List<Authority> getAll(){
		AuthorityDao authorityDao=new AuthorityDao();
		try {
			return authorityDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
