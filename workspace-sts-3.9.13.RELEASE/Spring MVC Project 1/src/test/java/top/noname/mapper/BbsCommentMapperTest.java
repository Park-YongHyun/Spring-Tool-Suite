package top.noname.mapper;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.BbsCommentVO;
import top.noname.domain.BbsPageVO;
import top.noname.test.TestTemplate;

public class BbsCommentMapperTest extends TestTemplate {
	@Autowired
	private BbsCommentMapper mapper;

	@Test
	public void test() {
		selectOne();
	}
	
	// 댓글 작성
	public void insert() {
		BbsCommentVO commentVO = new BbsCommentVO();
		commentVO.setPostNum(3999);
		commentVO.setContent("댓글 test");
		commentVO.setWriter("유저");
		commentVO.setPassword("1");
		
		log.info("result: " + mapper.insert(commentVO));
		
//		for (int i = 1; i <= 10; i++) {
//			commentVO.setContent("댓글 " + i);
//			
//			log.info("result: " + mapper.insert(commentVO));
//		}
	}
	
	// 대댓글 작성
	public void insertReply() {
		BbsCommentVO commentVO = new BbsCommentVO();
		commentVO.setPostNum(3999);
		commentVO.setContent("대댓글 test");
		commentVO.setWriter("유저");
		commentVO.setPassword("1");
		commentVO.setDepth(2);
		commentVO.setParent(172);
		
		log.info("result: " + mapper.insert(commentVO));
	}
	
	// 댓글 읽기
	public void selectOne() {
		log.info("result: " + mapper.selectOne(0));
//		log.info(mapper.selectOne(0).toString());
	}
	
	// 댓글 목록 읽기
	public void selectList() {
		BbsPageVO pageVO = new BbsPageVO(1,40);
		
		List<BbsCommentVO> list = mapper.selectList(3999, pageVO);
		
		log.info(list.toString());
		for (BbsCommentVO commentVO : list) {
			log.info(commentVO.toString());
		}
	}
	
	// 댓글 수정
	public void update() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setNum(132);
		commentVO.setContent("수정 test");
		commentVO.setPassword("1");
		
		log.info("result: " + mapper.update(commentVO));
	}
	
	// 댓글 삭제
	public void delete() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setNum(125);
		commentVO.setPassword("1");
		
		log.info("result: " + mapper.delete(commentVO));
	}
	// 댓글 자식 여부
	public void selectExistsChild() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setPostNum(3999);
		commentVO.setParent(141);
		
		log.info("result: " + mapper.hasChild(commentVO));
	}
	// 댓글 임시 삭제
	public void updateDeleteComment() {
		BbsCommentVO commentVO = new BbsCommentVO();
		
		commentVO.setNum(158);
		commentVO.setPassword("1");
		
		log.info("result: " + mapper.tempDelete(commentVO));
		
	}
}




