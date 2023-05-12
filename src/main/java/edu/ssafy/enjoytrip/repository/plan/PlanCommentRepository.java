package edu.ssafy.enjoytrip.repository.plan;

import java.util.List;

import edu.ssafy.enjoytrip.dto.plan.PlanCommentDto;
import edu.ssafy.enjoytrip.dto.review.ReviewCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface PlanCommentRepository {

	
	void write(PlanCommentDto planCommentDto); // 댓글 작성
	ReviewCommentDto modify(PlanCommentDto planCommentDto); // 댓글 수정
	void delete(String planCommentId); // 댓글 삭제
	List<PlanCommentDto> select(String planId);
}
