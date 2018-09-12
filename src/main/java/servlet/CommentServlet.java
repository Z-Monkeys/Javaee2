package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import bean.User;
import service.CommentService;
import tools.Message;
import tools.PageInformation;
import tools.Tool;


public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type1");
		String newsId=request.getParameter("newsId");
		CommentService commentService=new CommentService();
		if("showComment".equals(type)){
			List<Comment> comments=new ArrayList<Comment>();
			PageInformation pageInformation=new PageInformation();
			pageInformation=Tool.getPageInformation("comment", request, pageInformation);
			pageInformation.setSearchSql(" newsId='"+newsId+"' ");
			comments=commentService.getOnePage(pageInformation);
			request.setAttribute("oldStair", request.getAttribute("oldStair"));
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("comments", comments);
			request.getRequestDispatcher("/comment/showComment.jsp").include(request, response);
		}else if("praise".equals(type)){
			Integer commentId=Integer.parseInt(request.getParameter("commentId"));
			commentService.praise(commentId);
			String url="/servlet/NewsServlet?type1=showANews&newsId="+newsId+
					"&page="+request.getParameter("page")+"&pageSize="+request.getParameter("pageSize")+
					"&totalPageCount="+request.getParameter("totalPageCount");
			request.getRequestDispatcher(url).forward(request, response);	
		}else if("addComment".equals(type)){
			String content=request.getParameter("content");
			Comment comment=new Comment();
			comment.setContent(content);
			comment.setNewsId(Integer.parseInt(newsId));
			User user=(User) request.getSession().getAttribute("user");
			comment.setUserName(user.getName());
			String commentId=request.getParameter("commentId");
			if(commentId==null||commentId.isEmpty()){
				commentService.addComment(comment);
			}else{
				comment.setCommentId(Integer.parseInt(commentId));
				commentService.addCommentToComment(comment);
			}
			String url="/servlet/NewsServlet?type1=showANews&newsId="+newsId+"&page=1";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
