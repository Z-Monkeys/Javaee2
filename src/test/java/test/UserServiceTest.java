package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import bean.UserInformation;
import dao.DatabaseDao;
import dao.UserDao;
import service.UserService;
import tools.PageInformation;
import tools.WebProperties;

public class UserServiceTest {
	static protected DatabaseDao databaseDao;
	static protected UserDao userDao;
	static protected PageInformation pageInformation;
	static protected UserService userService;
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
		userService=new UserService();
		pageInformation=new PageInformation(); 
	}
	
	@AfterClass
	static public void afterClass()throws Exception{
		databaseDao.close();
	}
	
	@Test
	public void testLogin()throws Exception {
		user.setName("admin");
		user.setPassword("123abc");
		int result=userService.login(user);
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
		user.setName("123456789");
		user.setType("user");
		user.setPassword("111111111");
		user.setEnable("use");
		int result=userService.register(user);
		assertEquals(1,result);
	}
	
	@Test
	public void testChangePassword()throws Exception{
		user.setName("123456789");
		user.setUserId(53);
		String newPassword="110110110";
		int result=userService.changePassword(user, newPassword);
		assertEquals(1,result);
	}
	
	@Test
	public void testGetOnePage()throws Exception{
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");
		List<User> users=userService.getOnePage(pageInformation);
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testCheck()throws Exception{
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");
		String id="54";
		List<User> users=userService.check(pageInformation, id);
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testDelete()throws Exception{
		pageInformation.setIds("55");
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");
		List<User> users=userService.delete(pageInformation);
		assertEquals(new Integer(users.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testGetInformation()throws Exception{
		Integer userId=41;
		UserInformation userInformation=new UserInformation();
		userInformation=userService.getInformation(userId);
		assertEquals(new Integer(2),userInformation.getUserInformationId());
	}
	
	/*@Test
	public void testUpdateInformation()throws Exception{
		
	}*/
}
