package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	private BoardMapper mapper;

	// 게시글 등록
	@Override
	public void register(BoardVO board) {
		log.info("register......." + board);
		mapper.insertSelectKey(board);
	}

	// 게시글 조회
	@Override
	public BoardVO get(Long bno) {
		log.info("get........" + bno);
		return mapper.read(bno);
	}

	// 게시글 수정
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......" + board);
		return mapper.update(board) == 1;
	}

	// 게시글 삭제
	@Override
	public boolean remove(Long bno) {
		log.info("remove......." + bno);
		return mapper.delete(bno) == 1;
	}

//	// 다수 게시글 조회
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.......");
//		return mapper.getList();
//	}

	// 다수 게시글 조회(+ 페이징)
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		return mapper.getListWithPaging(cri);
	}

	// 다수 게시글의 수 카운트(+ 검색)
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
}
