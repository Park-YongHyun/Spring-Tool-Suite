package top.noname.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.pageDTO;
import top.noname.domain.BbsPostDTO;
import top.noname.test.TestTemplate;

public class BbsPostMapperTest extends TestTemplate {
	@Autowired
	private BbsPostMapper mapper;

	@Test
	public void test() {
		selectList();
	}

	// 게시글 작성
	public void insert() {
		BbsPostDTO postDTO = new BbsPostDTO();
		
//		postDTO.setTitle("제목 " + 1);
//		postDTO.setContent("내용 " + 1);
//		postDTO.setWriter("유저 " + 1);
//		postDTO.setPassword("" + 1);
//		log.info("result: " + mapper.insert(postDTO));
		
		int count = 0;
		for (int i = 1; i <= 1000; i++) {
			postDTO.setTitle("제목 " + i);
			postDTO.setContent("내용 " + i);
			postDTO.setWriter("유저 " + i);
			postDTO.setPassword("" + i);
			count += mapper.insert(postDTO);
		}
		log.info("result: " + count);
	}

	// 게시글 읽기
	public void selectOne() {
		BbsPostDTO postDTO = mapper.selectOne(1);
		log.info(postDTO.toString());
	}

	// 게시글 목록 읽기
	public void selectList() {
		pageDTO pageDTO = new pageDTO(1, 20);
		pageDTO.setSearchKeyword("1");
		pageDTO.setSearchType("TC");
		List<BbsPostDTO> list = mapper.selectList(pageDTO);
		log.info(list.toString());
		for (BbsPostDTO postDTO : list) {
			log.info(postDTO.toString());
		}
	}

	// 게시글 수정
	public void update() {
		BbsPostDTO postDTO = new BbsPostDTO();
		postDTO.setNum(1);
		postDTO.setTitle("제목 1");
		postDTO.setContent("내용 1");
		postDTO.setPassword("1");
		log.info("result: " + mapper.update(postDTO));
	}

	// 게시글 삭제
	public void delete() {
		BbsPostDTO postDTO = new BbsPostDTO();
		postDTO.setNum(1);
		postDTO.setPassword("1");
		log.info("result: " + mapper.delete(postDTO));
	}

	// 게시글 카운트
	public void count() {
		pageDTO pageDTO = new pageDTO();
		pageDTO.setSearchKeyword("aa");
		pageDTO.setSearchType("TCW");
		log.info("result: " + mapper.count(pageDTO));
	}
}
