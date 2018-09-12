package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import bean.Authority;
import tools.AuthorityTool;
import tools.Message;

public class AuthorityFilter implements Filter {

    public AuthorityFilter() {      
    }

	public void destroy() {	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		
		String type=request.getParameter("type1");
		if("login".equals(type)||"register".equals(type)){
			chain.doFilter(request, response);
		}else{
			String key=AuthorityTool.getKey(req);
			Authority authority=AuthorityTool.authorityMap.get(key);
				
			if(authority==null){
				Message message=new Message();
				message.setMessage("你没有权限，不支持访问！");
				message.setRedirectTime(1000);
				request.setAttribute("message", message);
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}else{
				chain.doFilter(request, response);
			}
		}
			
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
