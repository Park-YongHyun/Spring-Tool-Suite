package top.noname.domain;

import java.util.Date;

import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;

public class BbsCommentVO {
	private int num, postNum;
	private String content;
	private String writer, password;
	private Date writeDate, updateDate;
	private int depth, parent;

	public void useXssFilter() {
		writer = XssPreventer.escape(writer);
		
		LucyXssFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
		content = filter.doFilter(content);
	}
	
	public int getNum() {
		return num;
	}
	public int getPostNum() {
		return postNum;
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
	public int getDepth() {
		return depth;
	}
	public int getParent() {
		return parent;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
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
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "BbsCommentVO [num=" + num + ", postNum=" + postNum + ", content=" + content + ", writer=" + writer
				+ ", password=" + password + ", writeDate=" + writeDate + ", updateDate=" + updateDate + ", depth="
				+ depth + ", parent=" + parent + "]";
	}
}
