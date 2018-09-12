package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import bean.News;

public class ServletTool {
public static News getNews(HttpServletRequest request){
		News news=new News();
		String newsId=request.getParameter("newsId");
		if(newsId!=null&&!newsId.isEmpty()){
			news.setNewsId(Integer.parseInt(request.getParameter("newsId")));
		}
		news.setCaption(request.getParameter("caption"));
		news.setAuthor(request.getParameter("author"));
		news.setNewsType(request.getParameter("newsType"));
		news.setContent(request.getParameter("editorValue"));
		String str=request.getParameter("newsTime");
		LocalDateTime newsTime=LocalDateTime.parse(str,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		news.setNewsTime(newsTime);
		return news;
	}
}
