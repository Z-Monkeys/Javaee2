package service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.User;
import bean.UserInformation;
import dao.UserDao;
import dao.UserInformationDao;
import tools.FileTool;
import tools.PageInformation;
import tools.WebProperties;
import dao.DatabaseDao;

public class UserService {
	public Integer register(User user){
		int result=-1;//数据库操作失败
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			//不存在同名用户时注册成功，否则失败
			if(userDao.hasUser(user, databaseDao)==0){
				userDao.register(user, databaseDao);
				return 1;
			}else{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer login(User user){
		int result=-2;//数据库操作失败
		try {
			UserDao userDao=new UserDao();
			return userDao.login(user);//进行登陆验证，并返回验证结果
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer changePassword(User user,String newPassword){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			if(userDao.hasUser(user, databaseDao)==1){
				if(userDao.changePassword(user.getUserId().toString(), newPassword)>0){
					return 1;
				}else{
					return 0;
				}
			}else{
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public List<User> getOnePage(PageInformation pageInformation){
		List<User> users=new ArrayList<User>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			users=userDao.getOnePage(pageInformation, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public List<User> check(PageInformation pageInformation,String id){
		List<User> users=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			if(id!=null&&!id.isEmpty()){
				userDao.changeEnable(id, databaseDao);
			}
			users=userDao.getOnePage(pageInformation, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public List<User> delete(PageInformation pageInformation){
		List<User> users=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			userDao.deletes(pageInformation.getIds(), databaseDao);
			pageInformation.setIds(null);
			users = userDao.getOnePage(pageInformation, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public UserInformation getInformation(Integer userId){
		UserInformation userInformation=null;
		try {
			UserInformationDao userInformationDao=new UserInformationDao();
			DatabaseDao databaseDao=new DatabaseDao();
			userInformation=userInformationDao.getInformation(userId, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInformation;
	}
	//修改用户信息
	public Integer updateInformation(User user,HttpServletRequest request){
		Integer result = null;
		try {
			UserInformation userInformation=new UserInformation();
			if("user".equals(user.getType())){
				userInformation.setUserId(user.getUserId());		
			}
			String oldHeadIconUrl=user.getHeadIconUrl();
			//创建磁盘文件对象
			DiskFileItemFactory factory=new DiskFileItemFactory();
			String fullPath=request.getServletContext().getRealPath(WebProperties.propertiesMap.get("tempDir"));//获取相对路径的绝对路径
			//配置临时文件，保证一个安全的临时存放空间
			File repository=new File(fullPath);
			factory.setRepository(repository);
			//创建一个上传文件
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			//通过读取request字节流将表单元素和上传文件提取出来
			List<FileItem> items=upload.parseRequest(request);
			Iterator<FileItem> iter=items.iterator();//存放表单元素
			while(iter.hasNext()){//遍历表单元素
				FileItem item=iter.next();
				
				if(item.isFormField()){//表单元素
					if("sex".equals(item.getFieldName())){//获取表单元素的name属性
						userInformation.setSex(item.getString("UTF-8"));//获取表单元素的值（通常时Value）
					}
					if("hobby".equals(item.getFieldName())){//获取表单元素的name属性
						userInformation.setHobby(item.getString("UTF-8"));//获取表单元素的值（通常时Value）
					}
				}else{//上传文件
					File uploadedFile;
					String randomFileName;
					do{//创建新的临时文件
						randomFileName=FileTool.getRandomFileNameByCurrentTime(item.getName());
						String full=request.getServletContext().getRealPath(
								WebProperties.propertiesMap.get("headIconDirDefault")+"\\"+randomFileName);
						uploadedFile=new File(full);
					}while(uploadedFile.exists());//确保文件不存在
					
					item.write(uploadedFile);//将临时文件保存成新的文件
					result=1;//文件上传成功
					item.delete();//删除临时文件
					result=2;//文件上传成功，且临时文件删除
					user.setHeadIconUrl("\\"+WebProperties.propertiesMap.get("projectName")+
							WebProperties.propertiesMap.get("headIconDirDefault")+"\\"+randomFileName);
				}
			}
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao userDao=new UserDao();
			UserInformationDao userInformationDao=new UserInformationDao();
			
			//开始事务
			databaseDao.setAutoCommit(false);
			userDao.updateHeadIcon(user, databaseDao);//更新用户头像
			if("user".equals(user.getType())){
				userInformation.setUserId(user.getUserId());
				if(userInformationDao.hasUserId(user.getUserId(), databaseDao)){
					userInformationDao.update(userInformation, databaseDao);//更新用户信息
				}else{
					userInformationDao.insert(userInformation, databaseDao);//插入用户信息
				}
			}
			databaseDao.commit();//事务提交
			databaseDao.setAutoCommit(true);//事务结束
			result=3;//文件上传成功，临时文件删除，用户信息保存到数据库
			
			//老头像是系统默认头像，不需要删除
			if(oldHeadIconUrl.contains(FileTool.getFileName(WebProperties.propertiesMap.get("headIconFileDefault")))){
				result=5;//文件上传成功，临时文件删除，用户信息保存到数据库，旧头像没有删除
			}else{//老头像不是系统默认头像，删除老头像
				if(FileTool.deleteFile(new File(
				FileTool.root.replace("\\"+WebProperties.propertiesMap.get("projectName"), "")+oldHeadIconUrl))){
					result=5;//文件上传成功，临时文件删除，用户信息保存到数据库，旧头像被删除
				}else{
					result=4;//文件上传成功，临时文件删除，用户信息保存到数据库，旧头像无法删除
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
