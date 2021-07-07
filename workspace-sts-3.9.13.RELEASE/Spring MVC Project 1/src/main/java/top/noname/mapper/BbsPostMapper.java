package top.noname.mapper;

import java.util.List;

import top.noname.domain.BbsPageVO;
import top.noname.domain.BbsPostVO;

public interface BbsPostMapper {
	// 게시글 작성
	public int insert(BbsPostVO postVO);

	// 게시글 읽기
	public BbsPostVO selectOne(int num);

	// 게시글 목록 읽기
	public List<BbsPostVO> selectList(BbsPageVO pageVO);

	// 게시글 수정
	public int update(BbsPostVO postVO);

	// 게시글 삭제
	public int delete(BbsPostVO postVO);
	
	// 게시글 카운트
	public int count();
}
