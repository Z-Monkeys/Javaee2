package dao;

import java.net.URLDecoder;

import bean.UserInformation;

public class UserInformationDao {
	public UserInformation getInformation(Integer userId,DatabaseDao databaseDao){
		UserInformation userInformation=new UserInformation();
		userInformation.setUserId(userId);
		String sql="select * from userinformation where userId='"+userId+"' ";
		try {
			databaseDao.query(sql);
			while(databaseDao.next()){
				userInformation=new UserInformation();
				userInformation.setUserInformationId(databaseDao.getInteger("userInformationId"));
				userInformation.setSex(databaseDao.getString("sex"));
				userInformation.setHobby(databaseDao.getString("hobby"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInformation;
	}
	
	public Integer update(UserInformation userInformation,DatabaseDao databaseDao)throws Exception{
		String sex = URLDecoder.decode(userInformation.getSex(), "UTF-8");
		String hobby = URLDecoder.decode(userInformation.getHobby(), "UTF-8");
		String sql="update userinformation set sex='"+sex+"',hobby='"+
					hobby+"' where userId='"+userInformation.getUserId()+"' ";
		return databaseDao.update(sql);
	}
	
	public Integer insert(UserInformation userInformation,DatabaseDao databaseDao)throws Exception{
		String sex = URLDecoder.decode(userInformation.getSex(), "UTF-8");
		String hobby = URLDecoder.decode(userInformation.getHobby(), "UTF-8");
		String sql="insert into userinformation(userId,sex,hobby) values("+
					userInformation.getUserId()+",'"+sex+"','"+
					hobby+"') ";
		return databaseDao.update(sql);
	}
	
	public boolean hasUserId(Integer userId,DatabaseDao databaseDao){
		String sql="select * from userinformation where userId='"+userId.toString()+"' ";
		try {
			databaseDao.query(sql);
			while(databaseDao.next()){
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
