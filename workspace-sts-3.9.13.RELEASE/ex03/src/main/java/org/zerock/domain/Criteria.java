package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
// 페이징, 검색 처리
public class Criteria {
	private int pageNum;
	private int amount; // 페이지당 게시글의 수

	private String type; // 검색 종류
	private String keyword;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	// url 파라미터 연결
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.getPageNum())
			.queryParam("amount", this.getAmount())
			.queryParam("type", this.getType())
			.queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}
}
