package top.noname.mapper;

import java.util.List;

import top.noname.domain.pageDTO;
import top.noname.domain.BbsPostDTO;

public interface BbsPostMapper {
	// 게시글 작성
	public int insert(BbsPostDTO postDTO);

	// 게시글 읽기
	public BbsPostDTO selectOne(int num);

	// 게시글 목록 읽기
	public List<BbsPostDTO> selectList(pageDTO pageDTO);

	// 게시글 수정
	public int update(BbsPostDTO postDTO);

	// 게시글 삭제
	public int delete(BbsPostDTO postDTO);
	
	// 게시글 카운트
	public int count(pageDTO pageDTO);
}
