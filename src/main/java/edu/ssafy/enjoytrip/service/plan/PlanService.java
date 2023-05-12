package edu.ssafy.enjoytrip.service.plan;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;

import java.util.List;

public interface PlanService {
    void modify(PlanDto planDto); // 게시글 수정
    PlanDto select(int planId); // 단건 조회
    List<PlanDto> list(); // 목록 조회
    List<PlanDto> listByUserId(String userId); // 특정 사용자 여행 계획 조회(전체)
//    List<PlanDto> listByUserIdPlanId(PlanDto planDto); // 다른 사용자의 특정 여행 계획 조회
    void delete(int PlanId); // 게시긇 삭제
    void write(ReviewDto reviewDto); // 게시글 작성
//    void updateLike(String reievewId, String userId); // 좋아요
//    void deleteLike(String reievewId, String userId); // 좋아요 취소
//    List<PlanDto> topList(); // 좋아요 많은 녀석들
    List<Integer> selectContentId(int planId);
    AttractionDto selectAttraction(int contentId);
    void planInfo(PlanInfoDto planInfoDto);
    int topSequence(int planId);
    void addPlanInfo(PlanInfoDto planInfoDto);
    void deletePlanInfo(int planId);
}
