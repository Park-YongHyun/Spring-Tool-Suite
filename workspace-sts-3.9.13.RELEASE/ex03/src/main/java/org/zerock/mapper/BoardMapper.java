package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	// 모든 게시글 조회
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	// 다수 게시글 조회(+ 페이징, 검색)
	public List<BoardVO> getListWithPaging(Criteria cri);

	// 게시글 등록
	public void insert(BoardVO board);

	// 게시글 등록(bno(기본키) 미리 처리)
	public void insertSelectKey(BoardVO board);

	// 게시글 조회
	public BoardVO read(Long bno);

	// 게시글 삭제
	public int delete(Long bno);

	// 게시글 수정
	public int update(BoardVO board);
	
	// 다수 게시글의 수 카운트(+ 검색)
	public int getTotalCount(Criteria cri);
}
