package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	// 댓글 입력
	public int insert(ReplyVO vo);
	
	// 댓글 조회
	public ReplyVO read(Long bno);
	
	// 게시물의 댓글 조회
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	// 댓글 수정
	public int update(ReplyVO reply);
	
	// 댓글 삭제
	public int delete(Long rno);
}
