package dao;

import java.util.ArrayList;
import java.util.List;

import bean.NewsType;

public class NewsTypeDao {
	public List<NewsType> getAll()throws Exception{
		List<NewsType> newsTypes=new ArrayList<NewsType>();
		String sql="select * from newstype";
		DatabaseDao databaseDao=new DatabaseDao();
		databaseDao.query(sql);
		while(databaseDao.next()){
			NewsType newsType=new NewsType();
			newsType.setNewsTypeId(databaseDao.getInteger("newsTypeId"));
			newsType.setName(databaseDao.getString("name"));
			newsTypes.add(newsType);
		}
		return newsTypes;
	}
}
