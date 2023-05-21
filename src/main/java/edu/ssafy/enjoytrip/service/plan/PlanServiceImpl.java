package edu.ssafy.enjoytrip.service.plan;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanAttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanAttractionResponseDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.repository.plan.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{
    PlanRepository planRepository;
    
    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public void modify(PlanDto planDto, List<Integer> contentIds) {
        planRepository.modify(planDto);
        planRepository.deletePlanInfo(planDto.getId());
        
        int idx = 1;
		for (Integer id : contentIds) {
			PlanInfoDto planInfoDto = new PlanInfoDto();
			planInfoDto.setPlanId(planDto.getId());
			planInfoDto.setContentId(id);
			planInfoDto.setSequence(idx);
			planRepository.addPlanInfo(planInfoDto);
		}
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
    public PlanAttractionResponseDto listByUserId(String userId, int offset, int size) {
    	List<PlanAttractionDto> plans = new ArrayList<>();
    	List<PlanDto> planDtoList = planRepository.listByUserId(userId, offset * size, size);
    	
    	for (PlanDto planDto : planDtoList) {
			PlanAttractionDto planAttractionDto = new PlanAttractionDto();
			planAttractionDto.setPlan(planDto);
			
			int id = planDto.getId();
			List<Integer> contentId = planRepository.selectContentId(id);
			List<AttractionDto> attractionList = new ArrayList<>();
			for (Integer content : contentId) {
				AttractionDto attractionDto = planRepository.selectAttraction(content);
				attractionList.add(attractionDto);
			}
			
			planAttractionDto.setAttractionList(attractionList);
			plans.add(planAttractionDto);
		}
    	
    	int totalCount = planRepository.totalListCountByUserId(userId);
		if ((totalCount != 0) && (totalCount % size == 0)) totalCount /= size;
		else totalCount = totalCount / size + 1;
		
		return PlanAttractionResponseDto.builder()
				.totalCount(totalCount)
				.plans(plans)
				.build();
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

	@Override
	public void changeShareMode(int planId, char share) {
		planRepository.changeShareMode(planId, share);
		
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
