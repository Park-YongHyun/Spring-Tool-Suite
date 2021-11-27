package top.noname.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.noname.domain.BbsCommentDTO;
import top.noname.domain.pageDTO;

public interface BbsCommentMapper {
	// 댓글 작성
	public int insert(BbsCommentDTO commentDTO);
	
	// 댓글 읽기
	public BbsCommentDTO selectOne(int num);
	
	// 댓글 목록 읽기
	public List<BbsCommentDTO> selectList(@Param("postNum") int postNum, @Param("page") pageDTO pageDTO);
	
	// 댓글 수정
	public int update(BbsCommentDTO commentDTO);
	
	// 댓글 삭제
	public int delete(BbsCommentDTO commentDTO);
	// 댓글 자식 존재 여부
	public boolean hasChild(BbsCommentDTO commentDTO);
	// 댓글 임시 삭제
	public int tempDelete(BbsCommentDTO commentDTO);
	
	// 댓글 카운트
	public int count(int postNum);
}
