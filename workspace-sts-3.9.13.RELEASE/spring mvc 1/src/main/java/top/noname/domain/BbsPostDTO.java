package top.noname.domain;

import java.util.Date;

import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;

public class BbsPostDTO {
	private int num;

	private String title, content;
	private String writer, password;
	private Date writeDate, updateDate;
	
	public void useXssFilter() {
		title = XssPreventer.escape(title);
		writer = XssPreventer.escape(writer);
		
		LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
		content = filter.doFilter(content);
	}

	public int getNum() {
		return num;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriter() {
		return writer;
	}
	public String getPassword() {
		return password;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "BbsPostDTO [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", password=" + password + ", writeDate=" + writeDate + ", updateDate=" + updateDate + "]";
	}
}
