package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService {
	// 댓글 등록
	public int register(ReplyVO vo);
	
	// 댓글 조회
	public ReplyVO get(Long rno);
	
	// 게시물의 댓글 조회
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	// 댓글 수정
	public int modify(ReplyVO vo);
	
	// 댓글 삭제
	public int remove(Long rno);
}
