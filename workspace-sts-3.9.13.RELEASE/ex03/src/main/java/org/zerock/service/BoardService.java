package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	// 게시글 등록
	public void register(BoardVO board);

	// 게시글 조회
	public BoardVO get(Long bno);

	// 게시글 수정
	public boolean modify(BoardVO board);

	// 게시글 삭제
	public boolean remove(Long bno);

//	// 다수 게시글 조회
//	public List<BoardVO> getList();

	// 다수 게시글 조회(+ 페이징, 검색)
	public List<BoardVO> getList(Criteria cri);
	
	// 다수 게시글의 수 카운트(+ 검색)
	public int getTotal(Criteria cri);
}
