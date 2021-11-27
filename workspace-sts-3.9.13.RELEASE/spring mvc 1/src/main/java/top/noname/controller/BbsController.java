package top.noname.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import top.noname.domain.pageDTO;
import top.noname.domain.BbsPostDTO;
import top.noname.service.BbsPostService;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	private static final Logger log = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	private BbsPostService service;
	
	// 게시글 작성
	@GetMapping("/write")
	public void write() {
		log.info("@@@ bbs/write");
	}
	// 게시글 작성 제출
	@PostMapping("/write")
	public String write(BbsPostDTO postDTO, RedirectAttributes attr) {
		log.info("@@@ bbs/write submit");
		
		if (service.writePost(postDTO)) {
			attr.addFlashAttribute("result", "success");
		} else {
			attr.addFlashAttribute("result", "failure");
		}
		return "redirect:/bbs";
	}
	
	// 게시글 읽기
	@GetMapping("/read")
	public void read(int num, pageDTO pageDTO, Model model) {
		log.info("@@@ bbs/read");
		
		model.addAttribute("page", pageDTO);
		model.addAttribute("post", service.readPost(num));
	}
	
	// 게시글 목록 읽기
	@GetMapping("")
	public String list(pageDTO pageDTO, Model model) {
		log.info("@@@ bbs");
		
		model.addAttribute("postList", service.readPostList(pageDTO));
		model.addAttribute("page", pageDTO);
		return "bbs/list";
	}
	
	// 게시글 수정
	@GetMapping("/edit")
	public void edit(int num, Model model) {
		log.info("@@@ bbs/edit");
		
		model.addAttribute("post", service.readPost(num));
	}
	// 게시글 수정 제출
	@PostMapping("/edit")
	public String edit(BbsPostDTO postDTO, RedirectAttributes attr) {
		log.info("@@@ bbs/edit submit");
		
		if (service.editPost(postDTO)) {
			attr.addFlashAttribute("result", "success");
		} else {
			attr.addFlashAttribute("result", "failure");
		}
		return "redirect:/bbs/read?num=" + postDTO.getNum();
	}
	
	// 게시글 삭제
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody BbsPostDTO postDTO) {
		log.info("@@@ bbs/delete");
		
		return service.deletePost(postDTO)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
