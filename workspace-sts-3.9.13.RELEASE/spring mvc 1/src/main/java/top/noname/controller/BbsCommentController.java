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

import top.noname.domain.BbsCommentDTO;
import top.noname.domain.pageDTO;
import top.noname.service.BbsCommentService;

@RestController
@RequestMapping("/bbs/comment")
public class BbsCommentController {
	private static final Logger log = LoggerFactory.getLogger(BbsCommentController.class);
	
	@Autowired
	private BbsCommentService service;
	
	// 댓글 작성
	@PostMapping("write")
	public ResponseEntity<String> write(@RequestBody BbsCommentDTO commentDTO) {
		log.info("bbs/comment/write");
		
		return service.writeComment(commentDTO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 목록 읽기
	@GetMapping("readList")
	public ResponseEntity<Map<String, Object>> read(int postNum, pageDTO pageDTO) {
		log.info("bbs/comment/readList");
		
		Map<String, Object> map = new HashMap<>();
		map.put("commentList", service.readCommentList(postNum, pageDTO));
		map.put("page", pageDTO);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 댓글 수정
	@PutMapping("edit")
	public ResponseEntity<String> edit(@RequestBody BbsCommentDTO commentDTO) {
		log.info("bbs/comment/edit");
		
		return service.editComment(commentDTO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 삭제
	@DeleteMapping("delete")
	public ResponseEntity<String> delete(@RequestBody BbsCommentDTO commentDTO) {
		log.info("bbs/comment/delete");
		
		return service.deleteComment(commentDTO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
