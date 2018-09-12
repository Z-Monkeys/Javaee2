package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import tools.FileTool;
import tools.PageInformation;
import tools.WebProperties;

public class UserDaoTest {
	
	static protected UserDao userDao;
	static protected DatabaseDao databaseDao;
	static protected User user;
	
	@BeforeClass
	static public void beforeClass()throws Exception{
		DatabaseDao.databasePassword="";
		DatabaseDao.databaseUser="root";
		DatabaseDao.databaseType="com.mysql.jdbc.Driver";
		DatabaseDao.databaseUrl="jdbc:mysql://localhost:3306/news?useUnicode=true&amp;characterEncoding=utf8";
		userDao=new UserDao();
		databaseDao=new DatabaseDao();
		user=new User();
	}
	
	@AfterClass
	static public void afterClass()throws Exception{
		databaseDao.close();
	}
	
	@Test
	public void testHasUser() throws Exception {
		User user=new User();
		DatabaseDao databaseDao=new DatabaseDao();
		UserDao userDao=new UserDao();
		user.setName("admin");
		int result=userDao.hasUser(user, databaseDao);
		assertEquals(1,result);
	}
	
	@Test
	public void testLogin() throws Exception{
		user.setName("admin");
		user.setPassword("123abc");
		int result=userDao.login(user);
		assertEquals(1,result);
	}
	
	@Test
	public void testRegister()throws Exception{
		WebProperties.propertiesMap.put("projectRoot", "D:\\Tomcat8\\apache-tomcat-8.5.31\\webapps\\news");
		WebProperties.propertiesMap.put("projectName", "news");
		WebProperties.propertiesMap.put("tempDir", "\\upload\\temp");
		WebProperties.propertiesMap.put("headIconFileDefault", "\\upload\\images\\headIcon\\0.jpg");
		WebProperties.propertiesMap.put("headIconDir", "\\upload\\images\\headIcon");
		WebProperties.propertiesMap.put("headIconDirDefault", "\\upload\\images\\headIcon\\user");
		WebProperties.propertiesMap.put("redirectTime", "3");
		user.setName("useruser");
		user.setType("user");
		user.setPassword("111111111");
		user.setEnable("use");
		int result=userDao.register(user, databaseDao);
		assertEquals(1,result);
	}
	
	@Test
	public void testGetOnePage() throws Exception{
		PageInformation pageInformation=new PageInformation();
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");
		List<User> users=userDao.getOnePage(pageInformation, databaseDao);
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testChangeEnable() throws Exception{
		String id="34";
		int result=userDao.changeEnable(id, databaseDao);
		assertEquals(1,result);
	}
	
	@Test
	public void testChangePassword()throws Exception{
		String id="34";
		String newPassword="123456789";
		int result=userDao.changePassword(id, newPassword);
		assertEquals(1,result);
	}
	
	@Test
	public void testDeletes()throws Exception{
		String ids="43,44";
		int result=userDao.deletes(ids, databaseDao);
		assertEquals(2,result);
	}
	
	@Test
	public void testUpdateHeadIcon()throws Exception{
		user.setUserId(40);
		user.setHeadIconUrl("\\news\\upload\\images\\headIcon\\2.jpg");
		int result=userDao.updateHeadIcon(user, databaseDao);
		assertEquals(1,result);
	}

}
