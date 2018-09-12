package dao;

import java.util.ArrayList;
import java.util.List;

import bean.User;
import tools.PageInformation;
import tools.Tool;
import tools.WebProperties;

public class UserDao {
	//判断是否存在用户
	public Integer hasUser(User user,DatabaseDao databaseDao) throws Exception{
		String sql="SELECT * FROM user where name='"+user.getName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return 1;
		}
		return 0;
	}
	//将新注册的用户信息插入数据库user表中
	public Integer register(User user,DatabaseDao databaseDao) throws Exception{
		user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")+WebProperties.propertiesMap.get("headIconFileDefault"));
		String sql="INSERT INTO user(type,name,password,enable,headIconUrl) "
				+ "VALUES('"+user.getType()+"','"+user.getName()
				+"','"+user.getPassword()+"','"+user.getEnable()+
				"','"+user.getHeadIconUrl().replace("\\", "\\\\")+"')";
		return databaseDao.update(sql);
	}
	//判断用户是否可以登陆
	public Integer login(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="SELECT * FROM user WHERE name='"+user.getName()
		+"' and password='"+user.getPassword()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			String enable = databaseDao.getString("enable");
			if ("use".equals(enable)) {
				user.setType(databaseDao.getString("type"));
				user.setUserId(databaseDao.getInteger("userId"));
				user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
				user.setHeadIconUrl(databaseDao.getString("headIconUrl"));
				return 1;//可以登陆
			}
			return 0;//用户存在，但被停用，不可登陆
		}
		return -1;//用户不存在或者密码错误
	}
	
	public List<User> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao)throws Exception{
		List<User> users=new ArrayList<User>();
		String sqlCount=Tool.getSql(pageInformation, "count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//获取总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);
		
		String sqlSelect=Tool.getSql(pageInformation, "select");
		databaseDao.query(sqlSelect);
		while(databaseDao.next()){
			User user=new User();
			user.setEnable(databaseDao.getString("enable"));
			user.setName(databaseDao.getString("name"));
			user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setType(databaseDao.getString("type"));
			user.setUserId(databaseDao.getInteger("userId"));
			users.add(user);
		}
		return users;
	}
	//修改用户的可用性
	public Integer changeEnable(String id,DatabaseDao databaseDao)throws Exception{
		String sql="SELECT * FROM user WHERE userId IN ("+id+")";
		databaseDao.query(sql);
		while(databaseDao.next()){
			String enable=databaseDao.getString("enable");
			if("use".equals(enable)){
				enable="stop";
			}else{
				enable="use";
			}
			sql="UPDATE user SET enable='"+enable+"' WHERE userId IN ("+id+")";
			databaseDao.update(sql);
			return 1;
		}
		return 0;
	}
	//修改密码
	public Integer changePassword(String id,String newPassword) throws Exception{
		String sql="SELECT * FROM user WHERE userId IN ("+id+")";
		DatabaseDao databaseDao=new DatabaseDao();
		databaseDao.query(sql);
		while(databaseDao.next()){
			sql="UPDATE user SET password="+newPassword+" WHERE userId='"+id+"' ";
			databaseDao.update(sql);
			return 1;
		}
		return 0;
	}
	//删除多个用户
	public Integer deletes(String ids,DatabaseDao databaseDao) throws Exception{
		if(ids!=null&&ids.length()>0){
			String sql="DELETE FROM user WHERE userId In ("+ids+")";
			return databaseDao.update(sql);
		}else{
			return -1;
		}
	}
	//更新头像路径
	public Integer updateHeadIcon(User user,DatabaseDao databaseDao)throws Exception{		
		String sql="update user set headIconUrl='"+user.getHeadIconUrl().replace("\\", "\\\\")+"' where userId='"+user.getUserId().toString()+"' ";
		return databaseDao.update(sql);
	}
}
