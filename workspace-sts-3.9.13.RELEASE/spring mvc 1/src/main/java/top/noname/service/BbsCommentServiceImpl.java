package top.noname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.noname.domain.BbsCommentDTO;
import top.noname.domain.pageDTO;
import top.noname.mapper.BbsCommentMapper;

@Service
public class BbsCommentServiceImpl implements BbsCommentService {
	@Autowired
	BbsCommentMapper mapper;
	
	// 댓글 작성
	@Override
	public boolean writeComment(BbsCommentDTO commentDTO) {
		if (commentDTO.getParent() != 0) { // 대댓글
			BbsCommentDTO parentCommentDTO = mapper.selectOne(commentDTO.getParent());
			
			if (parentCommentDTO == null) {
				return false;
			}
			
			commentDTO.setPostNum(parentCommentDTO.getPostNum());
			commentDTO.setDepth(parentCommentDTO.getDepth() + 1);
		}
		
		return mapper.insert(commentDTO) == 1;
	}

	// 댓글 목록 읽기
	@Override
	public List<BbsCommentDTO> readCommentList(int postNum, pageDTO pageDTO) {
		pageDTO.setPages(mapper.count(postNum));
		List<BbsCommentDTO> list = mapper.selectList(postNum, pageDTO);
		list.forEach(comment -> {
			if (comment.getWriteDate() == null) { // 임시 삭제된 댓글 처리
				comment.setWriter(null);
				comment.setContent("삭제되었습니다.");
				comment.setUpdateDate(null);
			}
		});
		return list;
	}

	// 댓글 수정
	@Override
	public boolean editComment(BbsCommentDTO commentDTO) {
		return mapper.update(commentDTO) == 1;
	}

	/* 댓글 삭제
	 * 과정:
		 * 타겟에 자식이 있으면 임시삭제 후 리턴, 없으면 삭제 및 재귀함수
		 * 재귀함수: 임시삭제 상태의 부모도 삭제
			 * 삭제된 타겟의 부모를 타겟팅
			 * 타겟이 임시상태 and 자식이 없으면 삭제 후 재귀, 아니면 리턴
	 */
	@Override
	public boolean deleteComment(BbsCommentDTO commentDTO) {
		BbsCommentDTO targetCommentDTO = mapper.selectOne(commentDTO.getNum()); // ! 입력 받은 데이터와 혼동 주의
		
		if (mapper.hasChild(targetCommentDTO)) {
			return mapper.tempDelete(commentDTO) == 1;
		} else {
			if (mapper.delete(commentDTO) == 1) {
				deleteParentComment(targetCommentDTO);
				return true;
			}
			return false;
		}
	}
	private int deleteParentComment(BbsCommentDTO commentDTO) {
		BbsCommentDTO targetCommentDTO = mapper.selectOne(commentDTO.getParent());
		
		if (targetCommentDTO != null && targetCommentDTO.getWriteDate() == null && !mapper.hasChild(targetCommentDTO)) {
			return mapper.delete(targetCommentDTO) + deleteParentComment(targetCommentDTO);
		} else {
			return 0;
		}
	}
}




