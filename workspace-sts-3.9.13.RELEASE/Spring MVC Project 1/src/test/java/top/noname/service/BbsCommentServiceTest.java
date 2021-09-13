package top.noname.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.BbsCommentDTO;
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
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setPostNum(3998);
		commentDTO.setWriter("유저");
		commentDTO.setPassword("1");
		commentDTO.setContent("댓글 test");
		
		switch (2) {
		case 1:
			log.info("result: " + service.writeComment(commentDTO));
			break;
		case 2:
			int count = 0;
			for (int i = 1; i <= 200; i++) {
				commentDTO.setContent("댓글 " + i);
				count += service.writeComment(commentDTO) ? 1 : 0; 
			}
			log.info("result: " + count);
			break;
		}
	}
	
	// 대댓글 작성
	public void writeReplyComment() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setContent("대댓글 test");
		commentDTO.setWriter("유저");
		commentDTO.setPassword("1");
		commentDTO.setParent(122);
		
		log.info("result: " + service.writeComment(commentDTO));
	}
	
	// 댓글 수정
	public void editComment() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setNum(137);
		commentDTO.setContent("댓글 수정 서비스 test");
		commentDTO.setPassword("1");
		
		log.info("result: " + service.editComment(commentDTO));
	}
	
	// 댓글 삭제
	public void deleteComment() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setNum(159);
		commentDTO.setPassword("1");
		
		log.info("result: " + service.deleteComment(commentDTO));
	}
	
}
