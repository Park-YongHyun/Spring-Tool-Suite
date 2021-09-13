package top.noname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.noname.domain.pageDTO;
import top.noname.domain.BbsPostDTO;
import top.noname.mapper.BbsPostMapper;

@Service
public class BbsPostServiceImpl implements BbsPostService {
	@Autowired
	private BbsPostMapper mapper;

	// 게시글 작성
	@Override
	public boolean writePost(BbsPostDTO postDTO) {
		return mapper.insert(postDTO) == 1;
	}

	// 게시글 읽기
	@Override
	public BbsPostDTO readPost(int num) {
		BbsPostDTO postDTO = mapper.selectOne(num);
		postDTO.useXssFilter();
		return postDTO;
	}

	// 게시글 목록 조회
	@Override
	public List<BbsPostDTO> readPostList(pageDTO pageDTO) {
		pageDTO.setPages(mapper.count(pageDTO));
		List<BbsPostDTO> list = mapper.selectList(pageDTO);
		list.forEach(postDTO -> {
			postDTO.useXssFilter();
		});
		return list;
	}

	// 게시글 수정
	@Override
	public boolean editPost(BbsPostDTO postDTO) {
		return mapper.update(postDTO) == 1;
	}

	// 게시글 삭제
	@Override
	public boolean deletePost(BbsPostDTO postDTO) {
		return mapper.delete(postDTO) == 1;
	}
}
