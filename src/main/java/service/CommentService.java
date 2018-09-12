package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Comment;
import bean.User;
import dao.CommentDao;
import dao.DatabaseDao;
import tools.PageInformation;

public class CommentService {
	public List<Comment> getOnePage(PageInformation pageInformation){
		List<Comment> comments=new ArrayList<Comment>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			CommentDao commentDao=new CommentDao();
			comments=commentDao.getOnePage(pageInformation, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	public Integer praise(Integer commentId){
		Integer result=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			CommentDao commentDao=new CommentDao();
			result = commentDao.praise(commentId, databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	public Integer addComment(Comment comment){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			CommentDao commentDao=new CommentDao();
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(), databaseDao);
			comment.setStair(stair);
			return commentDao.addComment(comment, databaseDao);
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	public Integer addCommentToComment(Comment comment){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			CommentDao commentDao=new CommentDao();
			Integer oldStair;
			Comment oldComment=commentDao.getCommentById(comment.getCommentId(), databaseDao);
			oldStair=oldComment.getStair();
			comment.setContent(oldStair+"楼:<br>"+comment.getContent());
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(), databaseDao);
			comment.setStair(stair);
			return commentDao.addComment(comment, databaseDao);
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		
	}
}
