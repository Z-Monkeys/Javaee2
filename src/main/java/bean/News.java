package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class News {
	private Integer newsId;//新闻编号
	private String newsType;//新闻类别
	private String author;//作者
	private String caption;//标题
	private String content;//评论
	private LocalDateTime newsTime;//新闻发生时间
	private Timestamp publishTime;//新闻发表时间
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(LocalDateTime newsTime) {
		this.newsTime = newsTime;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	
	
}
