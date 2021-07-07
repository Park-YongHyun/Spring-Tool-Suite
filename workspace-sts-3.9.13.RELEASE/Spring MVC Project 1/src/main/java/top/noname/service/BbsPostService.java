package top.noname.service;

import java.util.List;

import top.noname.domain.BbsPageVO;
import top.noname.domain.BbsPostVO;

public interface BbsPostService {
	// 게시글 쓰기
	public boolean writePost(BbsPostVO postVO);
	
	// 게시글 읽기
	public BbsPostVO readPost(int num);
	
	// 게시글 목록 조회
	public List<BbsPostVO> readPostList(BbsPageVO pageVO);
	
	// 게시글 수정
	public boolean editPost(BbsPostVO postVO);
	
	// 게시글 삭제
	public boolean deletePost(BbsPostVO postVO);
}
