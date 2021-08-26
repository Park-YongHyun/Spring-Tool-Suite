package top.noname.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import top.noname.domain.BbsCommentVO;
import top.noname.domain.BbsPageVO;
import top.noname.service.BbsCommentService;

@RestController
@RequestMapping("/bbs/comment")
public class BbsCommentController {
	private static final Logger log = LoggerFactory.getLogger(BbsCommentController.class);
	
	@Autowired
	private BbsCommentService service;
	
	// 댓글 작성
	@PostMapping("write")
	public ResponseEntity<String> write(@RequestBody BbsCommentVO commentVO) {
		log.info("bbs/comment/write");
		
		return service.writeComment(commentVO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 목록 읽기
	@GetMapping("readList")
	public ResponseEntity<Map<String, Object>> read(int postNum, BbsPageVO pageVO) {
		log.info("bbs/comment/readList");
		
		Map<String, Object> map = new HashMap<>();
		map.put("commentList", service.readCommentList(postNum, pageVO));
		map.put("page", pageVO);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 댓글 수정
	@PutMapping("edit")
	public ResponseEntity<String> edit(@RequestBody BbsCommentVO commentVO) {
		log.info("bbs/comment/edit");
		
		return service.editComment(commentVO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 삭제
	@DeleteMapping("delete")
	public ResponseEntity<String> delete(@RequestBody BbsCommentVO commentVO) {
		log.info("bbs/comment/delete");
		
		return service.deleteComment(commentVO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
