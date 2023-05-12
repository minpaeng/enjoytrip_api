package edu.ssafy.enjoytrip.service.attraction;

import java.util.List;

import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.attraction.GugunDto;
import edu.ssafy.enjoytrip.dto.attraction.SidoDto;
import edu.ssafy.enjoytrip.repository.attraction.AttractionRepository;

@Service
public class AttractionServiceImpl implements AttractionService {

	AttractionRepository attractionRepository;

	public AttractionServiceImpl(AttractionRepository attractionRepository) {
		super();
		this.attractionRepository = attractionRepository;
	}

	@Override
	public List<AttractionDto> select(AttractionDto attractionDto) {
		return attractionRepository.select(attractionDto);
	}

	@Override
	public List<SidoDto> selectSido() {
		// TODO Auto-generated method stub
		return attractionRepository.selectSido();
	}

	@Override
	public List<GugunDto> selectGugun(int sidoCode) {
		return attractionRepository.selectGugun(sidoCode);
	}

	@Override
	public AttractionDto selectByContentId(int contentId) {
		return attractionRepository.selectByContentId(contentId);
	}

	@Override
	public void makePlan(PlanDto planDto) {
		attractionRepository.makePlan(planDto);
	}

	@Override
	public int getPlanId(String userId) {
		return attractionRepository.getPlanId(userId);
	}

	@Override
	public void planInfo(PlanInfoDto planInfoDto) {
		attractionRepository.planInfo(planInfoDto);
	}


}
