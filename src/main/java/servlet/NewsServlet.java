package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.News;
import service.NewsService;
import tools.Message;
import tools.PageInformation;
import tools.ServletTool;
import tools.Tool;

public class NewsServlet extends HttpServlet {
	public NewsServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String type=request.getParameter("type1");
		NewsService newsService=new NewsService();
		Message message=new Message();
		if("addNews".equals(type)){
			News news=ServletTool.getNews(request);
			int result=newsService.addNews(news);
			message.setResult(result);
			if(result==1){
				message.setMessage("新闻添加成功!");
				message.setRedirectUrl("/news/news/manage/addNews.jsp");
			}else{
				message.setMessage("新闻添加失败!");
				message.setRedirectUrl("/news/index.jsp");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}else if("showNews".equals(type)){
			List<News> newses=new ArrayList<News>();
			PageInformation pageInformation=new PageInformation();
			pageInformation=Tool.getPageInformation("news", request, pageInformation);
			newses=newsService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newses);
			request.getRequestDispatcher("/news/showNews.jsp").forward(request, response);
		}else if("showANews".equals(type)){
			String newsId=request.getParameter("newsId");
			News news=new News();
			news=newsService.getNewsById(Integer.parseInt(newsId));
			request.setAttribute("news", news);
			request.getRequestDispatcher("/news/showANews.jsp").forward(request, response);
		}else if("manageNews".equals(type)){
			PageInformation pageInformation=new PageInformation();
			List<News> newses=new ArrayList<News>();
			pageInformation=Tool.getPageInformation("news", request, pageInformation);
			newses=newsService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newses);
			request.getRequestDispatcher("/news/manage/manageNews.jsp").forward(request, response);
		}else if("deleteANews".equals(type)){
			PageInformation pageInformation=new PageInformation();
			List<News> newses=new ArrayList<News>();
			pageInformation=Tool.getPageInformation("news", request, pageInformation);
			newses=newsService.deletes(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newses);
			request.getRequestDispatcher("/news/manage/manageNews.jsp").forward(request, response);
		}else if("editANews".equals(type)){
			PageInformation pageInformation=new PageInformation();
			News news=new News();
			pageInformation=Tool.getPageInformation("news", request, pageInformation);
			Integer newsId=Integer.parseInt(pageInformation.getIds());
			news=newsService.getNewsById(newsId);
			request.setAttribute("news", news);
			request.setAttribute("pageInformation", pageInformation);
			request.getRequestDispatcher("/news/manage/editANews.jsp").forward(request, response);
		}else if("editNews".equals(type)){	
			News news=new News();
			news=ServletTool.getNews(request);
			int result=newsService.updateNews(news);
			message.setResult(result);
			if(result==1){
				message.setMessage("新闻修改成功!");
				message.setRedirectUrl("/news/servlet/NewsServlet?type1=showNews&page=1&pageSize=2");
			}else{
				message.setMessage("新闻修改失败!");
				message.setRedirectUrl("/news/index.jsp");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

}
