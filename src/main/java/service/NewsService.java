package service;

import java.util.ArrayList;
import java.util.List;

import bean.News;
import dao.DatabaseDao;
import dao.NewsDao;
import tools.PageInformation;

public class NewsService {
	public Integer addNews(News news){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.addNews(news, databaseDao);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<News> getOnePage(PageInformation pageInformation){
		List<News> newses=new ArrayList<News>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			newses=newsDao.getOnePage(pageInformation, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newses;
	}
	
	public News getNewsById(Integer newsId){
		NewsDao newsDao=new NewsDao();
		try {
			return newsDao.getById(newsId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<News> deletes(PageInformation pageInformation){
		List<News> newses=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			newsDao.deletes(pageInformation.getTableName(), pageInformation.getIds(), databaseDao);
			pageInformation.setIds(null);
			newses=newsDao.getOnePage(pageInformation, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newses;
	}
	
	public Integer updateNews(News news){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.updateNews(news, databaseDao);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
