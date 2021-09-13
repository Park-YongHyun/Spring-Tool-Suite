package top.noname.service;

import java.util.List;

import top.noname.domain.pageDTO;
import top.noname.domain.BbsPostDTO;

public interface BbsPostService {
	// 게시글 작성
	public boolean writePost(BbsPostDTO postDTO);
	
	// 게시글 읽기
	public BbsPostDTO readPost(int num);
	
	// 게시글 목록 조회
	public List<BbsPostDTO> readPostList(pageDTO pageDTO);
	
	// 게시글 수정
	public boolean editPost(BbsPostDTO postDTO);
	
	// 게시글 삭제
	public boolean deletePost(BbsPostDTO postDTO);
}
