package top.noname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.noname.domain.BbsPageVO;
import top.noname.domain.BbsPostVO;
import top.noname.mapper.BbsPostMapper;

@Service
public class BbsPostServecieImpl implements BbsPostService {
	@Autowired
	private BbsPostMapper mapper;

	// 게시글 작성
	@Override
	public boolean writePost(BbsPostVO postVO) {
		return mapper.insert(postVO) == 1;
	}

	// 게시글 읽기
	@Override
	public BbsPostVO readPost(int num) {
		BbsPostVO postVO = mapper.selectOne(num);
		postVO.useXssFilter();
		return postVO;
	}

	// 게시글 목록 조회
	@Override
	public List<BbsPostVO> readPostList(BbsPageVO pageVO) {
		pageVO.setPages(mapper.count());
		List<BbsPostVO> list = mapper.selectList(pageVO);
		list.forEach(postVO -> {
			postVO.useXssFilter();
		});
		return list;
	}

	// 게시글 수정
	@Override
	public boolean editPost(BbsPostVO postVO) {
		return mapper.update(postVO) == 1;
	}

	// 게시글 삭제
	@Override
	public boolean deletePost(BbsPostVO postVO) {
		return mapper.delete(postVO) == 1;
	}
}
