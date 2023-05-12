package edu.ssafy.enjoytrip.service.plan;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.repository.plan.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{
    PlanRepository planRepository;
    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void modify(PlanDto planDto) {
        planRepository.modify(planDto);
    }

    @Override
    public PlanDto select(int planId) {
        return planRepository.select(planId);
    }

    @Override
    public List<PlanDto> list() {
        return planRepository.list();
    }

    @Override
    public List<PlanDto> listByUserId(String userId) {
        return planRepository.listByUserId(userId);
    }

//    @Override
//    public List<PlanDto> listByUserIdPlanId(PlanDto planDto) {
//        return planRepository.listByUserIdPlanId(planDto);
//    }

    @Override
    public void delete(int PlanId) {
        planRepository.delete(PlanId);
    }

    @Override
    public void write(ReviewDto reviewDto) {
        planRepository.write(reviewDto);
    }

    @Override
    public List<Integer> selectContentId(int planId) {
        return planRepository.selectContentId(planId);
    }

    @Override
    public AttractionDto selectAttraction(int contentId) {
        return planRepository.selectAttraction(contentId);
    }

    @Override
    public void planInfo(PlanInfoDto planInfoDto) {
        planRepository.planInfo(planInfoDto);
    }

    @Override
    public int topSequence(int planId) {
        return planRepository.topSequence(planId);
    }

    @Override
    public void addPlanInfo(PlanInfoDto planInfoDto) {
        planRepository.addPlanInfo(planInfoDto);
    }

    @Override
    public void deletePlanInfo(int planId) {
        planRepository.deletePlanInfo(planId);
    }


//    @Override
//    public void updateLike(String reievewId, String userId) {
//        planRepository.updateLike(reievewId, userId);
//    }
//
//    @Override
//    public void deleteLike(String reievewId, String userId) {
//        planRepository.deleteLike(reievewId, userId);
//    }
//
//    @Override
//    public List<PlanDto> topList() {
//        return planRepository.topList();
//    }
}
