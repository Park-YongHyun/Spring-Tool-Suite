package top.noname.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.BbsPageVO;
import top.noname.domain.BbsPostVO;
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
		BbsPostVO postVO = new BbsPostVO();
		
		postVO.setTitle("제목 " + 1);
		postVO.setContent("내용 " + 1);
		postVO.setWriter("유저 " + 1);
		postVO.setPassword("" + 1);
		log.info("result: " + service.writePost(postVO));
		
//		int count = 0;
//		for (int i = 1; i <= 1000; i++) {
//			postVO.setTitle("제목 " + i);
//			postVO.setContent("내용 " + i);
//			postVO.setWriter("유저 " + i);
//			postVO.setPassword("" + i);
//			if (service.addPost(postVO)) {
//				count++;
//			}
//		}
//		log.info("result: " + count);
	}

	// 게시글 읽기
	public void readPost() {
		BbsPostVO postVO = service.readPost(1);
		log.info(postVO.toString());
	}

	// 게시글 목록 읽기
	public void readPostList() {
		BbsPageVO pageVO = new BbsPageVO(390, 10);
		List<BbsPostVO> list = service.readPostList(pageVO);
		log.info(list.toString());
		for (BbsPostVO postVO : list) {
			log.info(postVO.toString());
		}
		log.info(pageVO.toString());
	}

	// 게시글 수정
	public void editPost() {
		BbsPostVO postVO = new BbsPostVO();
		postVO.setNum(1);
		postVO.setTitle("제목 1");
		postVO.setContent("내용 1");
		postVO.setPassword("1");
		log.info("result: " + service.editPost(postVO));
	}

	// 게시글 삭제
	public void deletePost() {
		BbsPostVO postVO = new BbsPostVO();
		postVO.setNum(1);
		postVO.setPassword("1");
		log.info("result: " + service.deletePost(postVO));
	}
}
