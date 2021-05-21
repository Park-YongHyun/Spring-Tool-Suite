package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	// ReplyMapper 사용 가능 확인
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	// 댓글 등록
//	@Test
//	public void testCreate() {
//		Long[] bnoArr = { 409711L, 409712L, 409713L, 409714L }; // 존재하는 게시물 번호만 입력
//		IntStream.rangeClosed(1, 8).forEach(i -> {
//			ReplyVO vo = new ReplyVO();
//			vo.setBno(bnoArr[i % 4]);
//			vo.setReply("댓글 테스트 " + i);
//			vo.setReplyer("replyer" + i);
//			mapper.insert(vo);
//		});
//	}
	
//	// 댓글 조회
//	@Test
//	public void testRead() {
//		Long targetRno = 5L;
//		ReplyVO vo = mapper.read(targetRno);
//		log.info(vo);
//	}
	
//	// 댓글 삭제
//	@Test
//	public void testDelete() {
//		Long targetRno = 1L;
//		mapper.delete(targetRno);
//	}
	
//	// 댓글 수정
//	@Test
//	public void testUpdate() {
//		Long targetRno = 3L;
//		ReplyVO vo = mapper.read(targetRno);
//		vo.setReply("Update Reply");
//		int count = mapper.update(vo);
//		log.info("UPDATE COUNT: " + count);
//	}
	
	// 게시물의 댓글 조회
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		Long targetBno = 409711L;
		List<ReplyVO> replies = mapper.getListWithPaging(cri, targetBno);
		replies.forEach(reply -> log.info(reply));
	}
}




