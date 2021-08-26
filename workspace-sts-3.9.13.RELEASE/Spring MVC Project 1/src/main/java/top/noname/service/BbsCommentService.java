package top.noname.service;

import java.util.List;

import top.noname.domain.BbsCommentVO;
import top.noname.domain.BbsPageVO;

public interface BbsCommentService {
	// 댓글 작성
	public boolean writeComment(BbsCommentVO commentVO);
	
	// 댓글 목록 읽기
	public List<BbsCommentVO> readCommentList(int postNum, BbsPageVO pageVO);
	
	// 댓글 수정
	public boolean editComment(BbsCommentVO commentVO);
	
	// 댓글 삭제
	public boolean deleteComment(BbsCommentVO commentVO);
}
