package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
// 게시글
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
}
