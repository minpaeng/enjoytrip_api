package edu.ssafy.enjoytrip.service.plan;

import edu.ssafy.enjoytrip.dto.plan.PlanCommentDto;
import edu.ssafy.enjoytrip.dto.review.ReviewCommentDto;

import java.util.List;

public interface PlanCommentService {

    void write(PlanCommentDto planCommentDto); // 댓글 작성
    ReviewCommentDto modify(PlanCommentDto planCommentDto); // 댓글 수정
    void delete(String planCommentId); // 댓글 삭제
    List<PlanCommentDto> select(String planId);
}
