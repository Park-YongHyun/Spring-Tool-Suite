package top.noname.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.pageDTO;
import top.noname.domain.BbsPostDTO;
import top.noname.mapper.BbsPostMapper;
import top.noname.service.BbsPostService;
import top.noname.test.TestTemplate;

public class BbsPostServiceTest extends TestTemplate {
	@Autowired
	private BbsPostService service;

	@Test
	public void test() {
		readPostList();
	}

	// 게시글 추가
	public void writePost() {
		BbsPostDTO postDTO = new BbsPostDTO();
		
		postDTO.setTitle("제목 " + 1);
		postDTO.setContent("내용 " + 1);
		postDTO.setWriter("유저 " + 1);
		postDTO.setPassword("" + 1);
		log.info("result: " + service.writePost(postDTO));
		
//		int count = 0;
//		for (int i = 1; i <= 1000; i++) {
//			postDTO.setTitle("제목 " + i);
//			postDTO.setContent("내용 " + i);
//			postDTO.setWriter("유저 " + i);
//			postDTO.setPassword("" + i);
//			if (service.addPost(postDTO)) {
//				count++;
//			}
//		}
//		log.info("result: " + count);
	}

	// 게시글 읽기
	public void readPost() {
		BbsPostDTO postDTO = service.readPost(1);
		log.info(postDTO.toString());
	}

	// 게시글 목록 읽기
	public void readPostList() {
		pageDTO pageDTO = new pageDTO(1, 20);
		pageDTO.setSearchType("TCW");
		pageDTO.setSearchKeyword("aa");
		List<BbsPostDTO> list = service.readPostList(pageDTO);
		log.info(list.toString());
		for (BbsPostDTO postDTO : list) {
			log.info(postDTO.toString());
		}
		log.info(pageDTO.toString());
	}

	// 게시글 수정
	public void editPost() {
		BbsPostDTO postDTO = new BbsPostDTO();
		postDTO.setNum(1);
		postDTO.setTitle("제목 1");
		postDTO.setContent("내용 1");
		postDTO.setPassword("1");
		log.info("result: " + service.editPost(postDTO));
	}

	// 게시글 삭제
	public void deletePost() {
		BbsPostDTO postDTO = new BbsPostDTO();
		postDTO.setNum(1);
		postDTO.setPassword("1");
		log.info("result: " + service.deletePost(postDTO));
	}
}
