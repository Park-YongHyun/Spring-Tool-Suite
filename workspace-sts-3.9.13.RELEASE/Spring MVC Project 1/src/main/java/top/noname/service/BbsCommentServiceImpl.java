package top.noname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.noname.domain.BbsCommentVO;
import top.noname.domain.BbsPageVO;
import top.noname.mapper.BbsCommentMapper;

@Service
public class BbsCommentServiceImpl implements BbsCommentService {
	@Autowired
	BbsCommentMapper mapper;
	
	// 댓글 작성
	@Override
	public boolean writeComment(BbsCommentVO commentVO) {
		if (commentVO.getParent() != 0) { // 대댓글
			BbsCommentVO parentCommentVO = mapper.selectOne(commentVO.getParent());
			
			if (parentCommentVO == null) {
				return false;
			}
			
			commentVO.setPostNum(parentCommentVO.getPostNum());
			commentVO.setDepth(parentCommentVO.getDepth() + 1);
		}
		
		return mapper.insert(commentVO) == 1;
	}

	// 댓글 목록 읽기
	@Override
	public List<BbsCommentVO> readCommentList(int postNum, BbsPageVO pageVO) {
		pageVO.setPages(mapper.count(postNum));
		List<BbsCommentVO> list = mapper.selectList(postNum, pageVO);
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
	public boolean editComment(BbsCommentVO commentVO) {
		return mapper.update(commentVO) == 1;
	}

	/* 댓글 삭제
	 * 과정:
		 * 타겟에 자식이 있으면 임시삭제 후 리턴, 없으면 삭제 및 재귀함수
		 * 재귀함수: 임시삭제 상태의 부모도 삭제
			 * 삭제된 타겟의 부모를 타겟팅
			 * 타겟이 임시상태 and 자식이 없으면 삭제 후 재귀, 아니면 리턴
	 */
	@Override
	public boolean deleteComment(BbsCommentVO commentVO) {
		BbsCommentVO targetCommentVO = mapper.selectOne(commentVO.getNum()); // ! 입력 받은 데이터와 혼동 주의
		
		if (mapper.hasChild(targetCommentVO)) {
			return mapper.tempDelete(commentVO) == 1;
		} else {
			if (mapper.delete(commentVO) == 1) {
				deleteParentComment(targetCommentVO);
				return true;
			}
			return false;
		}
	}
	private int deleteParentComment(BbsCommentVO commentVO) {
		BbsCommentVO targetCommentVO = mapper.selectOne(commentVO.getParent());
		
		if (targetCommentVO != null && targetCommentVO.getWriteDate() == null && !mapper.hasChild(targetCommentVO)) {
			return mapper.delete(targetCommentVO) + deleteParentComment(targetCommentVO);
		} else {
			return 0;
		}
	}
}




