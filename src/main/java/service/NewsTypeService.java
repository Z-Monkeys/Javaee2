package service;

import java.util.ArrayList;
import java.util.List;

import bean.NewsType;
import dao.NewsTypeDao;

public class NewsTypeService {
	public List<NewsType>getAll(){
		List<NewsType> newsTypes=new ArrayList<NewsType>();
		NewsTypeDao newsTypeDao=new NewsTypeDao();
		try {
			newsTypes=newsTypeDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsTypes;
	}
}
