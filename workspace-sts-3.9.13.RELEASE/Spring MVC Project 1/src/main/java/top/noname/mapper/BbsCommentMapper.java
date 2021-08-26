package top.noname.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.noname.domain.BbsCommentVO;
import top.noname.domain.BbsPageVO;

public interface BbsCommentMapper {
	// 댓글 작성
	public int insert(BbsCommentVO commentVO);
	
	// 댓글 읽기
	public BbsCommentVO selectOne(int num);
	
	// 댓글 목록 읽기
	public List<BbsCommentVO> selectList(@Param("postNum") int postNum, @Param("page") BbsPageVO pageVO);
	
	// 댓글 수정
	public int update(BbsCommentVO commentVO);
	
	// 댓글 삭제
	public int delete(BbsCommentVO commentVO);
	// 댓글 자식 존재 여부
	public boolean hasChild(BbsCommentVO commentVO);
	// 댓글 임시 삭제
	public int tempDelete(BbsCommentVO commentVO);
	
	// 댓글 카운트
	public int count(int postNum);
}
