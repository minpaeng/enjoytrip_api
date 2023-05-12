package edu.ssafy.enjoytrip.service.plan;

import edu.ssafy.enjoytrip.dto.plan.PlanCommentDto;
import edu.ssafy.enjoytrip.dto.review.ReviewCommentDto;
import edu.ssafy.enjoytrip.repository.plan.PlanCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanCommentServiceImpl implements PlanCommentService{

    PlanCommentRepository planCommentRepository;

    public PlanCommentServiceImpl(PlanCommentRepository planCommentRepository) {
        this.planCommentRepository = planCommentRepository;
    }

    @Override
    public void write(PlanCommentDto planCommentDto) {
        planCommentRepository.write(planCommentDto);
    }

    @Override
    public ReviewCommentDto modify(PlanCommentDto planCommentDto) {
        return planCommentRepository.modify(planCommentDto);
    }

    @Override
    public void delete(String planCommentId) {
        planCommentRepository.delete(planCommentId);
    }

    @Override
    public List<PlanCommentDto> select(String planId) {
        return planCommentRepository.select(planId);
    }
}
