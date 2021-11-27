package top.noname.mapper;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import top.noname.domain.BbsCommentDTO;
import top.noname.domain.pageDTO;
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
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		commentDTO.setPostNum(3999);
		commentDTO.setContent("댓글 test");
		commentDTO.setWriter("유저");
		commentDTO.setPassword("1");
		
		log.info("result: " + mapper.insert(commentDTO));
		
//		for (int i = 1; i <= 10; i++) {
//			commentDTO.setContent("댓글 " + i);
//			
//			log.info("result: " + mapper.insert(commentDTO));
//		}
	}
	
	// 대댓글 작성
	public void insertReply() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		commentDTO.setPostNum(3999);
		commentDTO.setContent("대댓글 test");
		commentDTO.setWriter("유저");
		commentDTO.setPassword("1");
		commentDTO.setDepth(2);
		commentDTO.setParent(172);
		
		log.info("result: " + mapper.insert(commentDTO));
	}
	
	// 댓글 읽기
	public void selectOne() {
		log.info("result: " + mapper.selectOne(0));
//		log.info(mapper.selectOne(0).toString());
	}
	
	// 댓글 목록 읽기
	public void selectList() {
		pageDTO pageDTO = new pageDTO(1,40);
		
		List<BbsCommentDTO> list = mapper.selectList(3999, pageDTO);
		
		log.info(list.toString());
		for (BbsCommentDTO commentDTO : list) {
			log.info(commentDTO.toString());
		}
	}
	
	// 댓글 수정
	public void update() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setNum(132);
		commentDTO.setContent("수정 test");
		commentDTO.setPassword("1");
		
		log.info("result: " + mapper.update(commentDTO));
	}
	
	// 댓글 삭제
	public void delete() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setNum(125);
		commentDTO.setPassword("1");
		
		log.info("result: " + mapper.delete(commentDTO));
	}
	// 댓글 자식 여부
	public void selectExistsChild() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setPostNum(3999);
		commentDTO.setParent(141);
		
		log.info("result: " + mapper.hasChild(commentDTO));
	}
	// 댓글 임시 삭제
	public void updateDeleteComment() {
		BbsCommentDTO commentDTO = new BbsCommentDTO();
		
		commentDTO.setNum(158);
		commentDTO.setPassword("1");
		
		log.info("result: " + mapper.tempDelete(commentDTO));
		
	}
}




