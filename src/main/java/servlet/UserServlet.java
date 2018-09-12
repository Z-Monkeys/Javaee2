package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import bean.UserInformation;
import service.UserService;
import tools.Message;
import tools.PageInformation;
import tools.SearchTool;
import tools.Tool;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result=-1;
		String type=request.getParameter("type1");
		Message message=new Message();
		UserService userService=new UserService();
		if("register".equals(type)){
			User user=new User();
			user.setType(request.getParameter("type"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setEnable("use");
			result=userService.register(user);
			if(result==1){
				message.setMessage("注册成功！");
				message.setRedirectUrl("/news/jsp/login.jsp");
			}else if(result==0){
				message.setMessage("用户名已存在，修改用户名注册！");
				message.setRedirectUrl("/news/jsp/register.jsp");
			}else{
				message.setMessage("注册失败，请重新注册！");
				message.setRedirectUrl("/news/jsp/register.jsp");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}else if("login".equals(type)){
			User user=new User();
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			result=userService.login(user);
			if(result==1){
				request.getSession().setAttribute("user", user);
				if("manager".equals(user.getType())){
					request.getRequestDispatcher("/jsp/manager/manage.jsp").forward(request, response);
				}else if("newsAuthor".equals(user.getType())){
					request.getRequestDispatcher("/jsp/newsAuthor/manage.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}	
				return;
			}else if(result==0){
				message.setMessage("用户已被停用，请联系管理员！");
				message.setRedirectUrl("/news/jsp/login.jsp");
			}else if(result==-1){
				message.setMessage("用户不存在或密码错误，请重新登陆！");
				message.setRedirectUrl("/news/jsp/login.jsp");
			}else{
				message.setMessage("出现错误，请重新登陆！");
				message.setRedirectUrl("/news/jsp/login.jsp");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}else if("logout".equals(type)){
			request.getSession().removeAttribute("user");
			response.sendRedirect("/news/index.jsp");
		}else if("skinUser".equals(type)){
			PageInformation pageInformation=new PageInformation();
			pageInformation=Tool.getPageInformation("user", request, pageInformation);
			List<User> users = userService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			request.getRequestDispatcher("/jsp/manager/skinUser.jsp").forward(request, response);
		}
		else if("checkUser".equals(type)){
			PageInformation pageInformation=new PageInformation();
			pageInformation=Tool.getPageInformation("user", request, pageInformation);
			String id=pageInformation.getIds();
			pageInformation.setIds(null);
			List<User> users=userService.check(pageInformation, id);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			request.getRequestDispatcher("/jsp/manager/checkUser.jsp").forward(request, response);
		}else if("searchUser".equals(type)){
			PageInformation pageInformation=new PageInformation();
			pageInformation=Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(SearchTool.user(request));
			List<User> users=userService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			request.getRequestDispatcher("/jsp/manager/skinUser.jsp").forward(request, response);
		}
		else if("deleteUser".equals(type)){
			PageInformation pageInformation=new PageInformation();
			pageInformation=Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(" type!='manager'");
			List<User> users=userService.delete(pageInformation);
			request.setAttribute("users", users);
			request.setAttribute("pageInformation", pageInformation);
			request.getRequestDispatcher("/jsp/manager/deleteUser.jsp").forward(request, response);
		}else if("changePassword".equals(type)){
			String newPassword=request.getParameter("newPassword1");
			String oldPassword=request.getParameter("oldPassword");
			User user=(User) request.getSession().getAttribute("user");
			if(user.getPassword().equals(oldPassword)){
				Integer result2=userService.changePassword(user, newPassword);
				message.setResult(result2);
				if(result2==1){
					user.setPassword(newPassword);
					message.setMessage("密码修改成功！");
					message.setRedirectUrl("/news/index.jsp");
				}else if(result2==0){
					message.setMessage("密码修改失败！");
					message.setRedirectUrl("/news/jsp/user/changePassword.jsp");
				}			
			}else{
				message.setMessage("旧密码错误，请重新输入！");
				message.setRedirectUrl("/news/jsp/user/changePassword.jsp");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}else if("showInformation".equals(type)){
			User user=(User) request.getSession().getAttribute("user");
			if("user".equals(user.getType())){
				UserInformation userInformation=userService.getInformation(user.getUserId());
				request.setAttribute("userInformation", userInformation);
			}
			request.getRequestDispatcher("/jsp/user/showInformation.jsp").forward(request, response);
		}else if("changeInformation1".equals(type)){
			User user=(User) request.getSession().getAttribute("user");
			if("user".equals(user.getType())){
				UserInformation userInformation=userService.getInformation(user.getUserId());
				request.setAttribute("userInformation", userInformation);
			}
			request.getRequestDispatcher("/jsp/user/changeInformation.jsp").forward(request, response);
		}else if("changeInformation2".equals(type)){
			User user=(User) request.getSession().getAttribute("user");
			if("user".equals(user.getType())){
				UserInformation userinformation=new UserInformation();
				userinformation.setUserId(user.getUserId());
				userinformation.setSex(request.getParameter("sex"));
				userinformation.setHobby(request.getParameter("hobby"));
			}
			Integer result3 = userService.updateInformation(user, request);
			message.setResult(result3);
			if(result3==5||result3==4||result3==3){
				message.setMessage("修改个人信息成功！");
				message.setRedirectUrl("/news/servlet/UserServlet?type1=showInformation");
			}else if(result3==0){
				message.setMessage("修改个人信息失败！");
				message.setRedirectUrl("/jsp/user/changeInformation.jsp");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}
