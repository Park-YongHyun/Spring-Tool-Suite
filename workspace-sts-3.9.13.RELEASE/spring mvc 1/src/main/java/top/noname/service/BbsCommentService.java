package top.noname.service;

import java.util.List;

import top.noname.domain.BbsCommentDTO;
import top.noname.domain.pageDTO;

public interface BbsCommentService {
	// 댓글 작성
	public boolean writeComment(BbsCommentDTO commentDTO);
	
	// 댓글 목록 읽기
	public List<BbsCommentDTO> readCommentList(int postNum, pageDTO pageDTO);
	
	// 댓글 수정
	public boolean editComment(BbsCommentDTO commentDTO);
	
	// 댓글 삭제
	public boolean deleteComment(BbsCommentDTO commentDTO);
}
