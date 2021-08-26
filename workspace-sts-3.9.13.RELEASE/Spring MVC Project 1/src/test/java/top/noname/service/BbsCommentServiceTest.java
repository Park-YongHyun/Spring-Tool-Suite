package top.noname.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.BbsCommentVO;
import top.noname.test.TestTemplate;

public class BbsCommentServiceTest extends TestTemplate {
	@Autowired
	private BbsCommentService service;
	
	@Test
	public void test() {
		writeComment();
	}
	
	// 댓글 작성
	public void writeComment() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setPostNum(3998);
		commentVO.setWriter("유저");
		commentVO.setPassword("1");
		commentVO.setContent("댓글 test");
		
		switch (2) {
		case 1:
			log.info("result: " + service.writeComment(commentVO));
			break;
		case 2:
			int count = 0;
			for (int i = 1; i <= 200; i++) {
				commentVO.setContent("댓글 " + i);
				count += service.writeComment(commentVO) ? 1 : 0; 
			}
			log.info("result: " + count);
			break;
		}
	}
	
	// 대댓글 작성
	public void writeReplyComment() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setContent("대댓글 test");
		commentVO.setWriter("유저");
		commentVO.setPassword("1");
		commentVO.setParent(122);
		
		log.info("result: " + service.writeComment(commentVO));
	}
	
	// 댓글 수정
	public void editComment() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setNum(137);
		commentVO.setContent("댓글 수정 서비스 test");
		commentVO.setPassword("1");
		
		log.info("result: " + service.editComment(commentVO));
	}
	
	// 댓글 삭제
	public void deleteComment() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setNum(159);
		commentVO.setPassword("1");
		
		log.info("result: " + service.deleteComment(commentVO));
	}
	
}
