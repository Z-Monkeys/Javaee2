package dao;

import java.util.ArrayList;
import java.util.List;

import bean.News;
import tools.PageInformation;
import tools.Tool;

public class NewsDao {
	public Integer addNews(News news,DatabaseDao databaseDao)throws Exception{
		String sql="insert into news(caption,author,newsType,content,newsTime) values('"+
					news.getCaption()+"','"+news.getAuthor()+"','"+news.getNewsType()+"','"+
					news.getContent()+"','"+news.getNewsTime()+"') ";
		return databaseDao.update(sql);
	}
	
	public Integer updateNews(News news,DatabaseDao databaseDao)throws Exception{
		String sql="update news set caption='"+news.getCaption()+"',author='"+news.getAuthor()+"',newsType='"+
					news.getNewsType()+"',content='"+news.getContent()+"',newsTime='"+news.getNewsTime()+"' "+
					" where newsId='"+news.getNewsId()+"' ";
		return databaseDao.update(sql);
	}
	
	public List<News> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao)throws Exception{
		List<News> newses=new ArrayList<News>();
		String sqlCount=Tool.getSql(pageInformation, "count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);
		Tool.setPageInformation(allRecordCount, pageInformation);
		String sqlSelect=Tool.getSql(pageInformation, "select");
		databaseDao.query(sqlSelect);
		while(databaseDao.next()){
			News news=new News();
			news.setNewsId(databaseDao.getInteger("newsId"));
			news.setCaption(databaseDao.getString("caption"));
			news.setAuthor(databaseDao.getString("author"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
			newses.add(news);
		}
		return newses;
	}
	
	public News getById(Integer newsId)throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		News news=new News();
		
		databaseDao.getById("news", newsId);
		while(databaseDao.next()){
			news.setNewsId(databaseDao.getInteger("newsId"));
			news.setAuthor(databaseDao.getString("author"));
			news.setCaption(databaseDao.getString("caption"));
			news.setContent(databaseDao.getString("content"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
		}
		return news;
	}
	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws Exception{
		return databaseDao.deletes(tableName, ids, databaseDao);
	}
}
