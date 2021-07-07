package top.noname.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.BbsPageVO;
import top.noname.domain.BbsPostVO;
import top.noname.mapper.BbsPostMapper;
import top.noname.test.TestTemplate;

public class BbsPostMapperTest extends TestTemplate {
	@Autowired
	private BbsPostMapper mapper;

	@Test
	public void test() {
		count();
	}

	// 게시글 추가
	public void insert() {
		BbsPostVO postVO = new BbsPostVO();
		
//		postVO.setTitle("제목 " + 1);
//		postVO.setContent("내용 " + 1);
//		postVO.setWriter("유저 " + 1);
//		postVO.setPassword("" + 1);
//		log.info("result: " + mapper.insert(postVO));
		
		int count = 0;
		for (int i = 1; i <= 1000; i++) {
			postVO.setTitle("제목 " + i);
			postVO.setContent("내용 " + i);
			postVO.setWriter("유저 " + i);
			postVO.setPassword("" + i);
			count += mapper.insert(postVO);
		}
		log.info("result: " + count);
	}

	// 게시글 읽기
	public void selectOne() {
		BbsPostVO postVO = mapper.selectOne(1);
		log.info(postVO.toString());
	}

	// 게시글 목록 읽기
	public void selectList() {
		BbsPageVO pageVO = new BbsPageVO(1, 10);
		List<BbsPostVO> list = mapper.selectList(pageVO);
		log.info(list.toString());
		for (BbsPostVO postVO : list) {
			log.info(postVO.toString());
		}
	}

	// 게시글 수정
	public void update() {
		BbsPostVO postVO = new BbsPostVO();
		postVO.setNum(1);
		postVO.setTitle("제목 1");
		postVO.setContent("내용 1");
		postVO.setPassword("1");
		log.info("result: " + mapper.update(postVO));
	}

	// 게시글 삭제
	public void delete() {
		BbsPostVO postVO = new BbsPostVO();
		postVO.setNum(1);
		postVO.setPassword("1");
		log.info("result: " + mapper.delete(postVO));
	}
	
	// 게시글 카운트
	public void count() {
		log.info("result: " + mapper.count());
	}
}
