package tools;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bean.Authority;
import bean.User;

public class AuthorityTool {
	static public Map<String,Authority> authorityMap=new Hashtable<String,Authority>();
	
	public static String getKey(HttpServletRequest request){
		String originalUrl=request.getRequestURI();
		String param="";
		
		if(!originalUrl.endsWith("jsp")){
			param=request.getParameter("type1");
			if(param==null){
				param="";
			}
		}
		String key=originalUrl+param;
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			key=key+"anonymous";
		}else{
			key=key+user.getType();
		}
		return key;
	}
}
