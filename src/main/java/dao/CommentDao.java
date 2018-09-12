package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Comment;
import tools.PageInformation;
import tools.Tool;

public class CommentDao {
	public List<Comment> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao)throws Exception{
		List<Comment> comments=new ArrayList<Comment>();
		String sqlCount=Tool.getSql(pageInformation, "count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);
		Tool.setPageInformation(allRecordCount, pageInformation);
		
		String sqlSelect=Tool.getSql(pageInformation, "select");
		databaseDao.query(sqlSelect);
		while(databaseDao.next()){
			Comment comment=new Comment();
			comment.setCommentId(databaseDao.getInteger("commentId"));
			comment.setNewsId(databaseDao.getInteger("newsId"));
			comment.setUserName(databaseDao.getString("userName"));
			comment.setContent(databaseDao.getString("content"));
			comment.setPraise(databaseDao.getInteger("praise"));
			comment.setStair(databaseDao.getInteger("stair"));
			comment.setTime(databaseDao.getTimestamp("time"));
			comments.add(comment);
		}
		return comments;
	}
	
	public Integer addComment(Comment comment,DatabaseDao databaseDao)throws Exception{
		String sql="insert into comment (newsId,userName,content,stair) values('"+comment.getNewsId()+
					"','"+comment.getUserName()+"','"+comment.getContent()+"','"+comment.getStair()+"') ";
		return databaseDao.update(sql);
		
	}
	
	public Integer praise(Integer commentId,DatabaseDao databaseDao)throws Exception{
		String sql="update comment set praise=praise+1 where commentId="+commentId;
		return databaseDao.update(sql);
	}
	
	public Integer getStairByNewsId(Integer newsId,DatabaseDao databaseDao)throws Exception{
		String sql="select count(*) as count1 from comment where newsId='"+newsId+"' ";
		Integer stair=0;
		databaseDao.query(sql);
		while(databaseDao.next()){
			stair=databaseDao.getInteger("count1");
		}
		return stair+1;
	}
	
	public Comment getCommentById(Integer commentId,DatabaseDao databaseDao)throws Exception{
		databaseDao.getById("comment", commentId);
		while(databaseDao.next()){
			Comment comment=new Comment();
			comment.setCommentId(databaseDao.getInteger("commentId"));
			comment.setNewsId(databaseDao.getInteger("newsId"));
			comment.setUserName(databaseDao.getString("userName"));
			comment.setContent(databaseDao.getString("content"));
			comment.setPraise(databaseDao.getInteger("praise"));
			comment.setStair(databaseDao.getInteger("stair"));
			comment.setTime(databaseDao.getTimestamp("time"));
			return comment;
		}
		return null;
	}
}
