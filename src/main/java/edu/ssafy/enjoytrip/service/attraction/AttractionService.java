package edu.ssafy.enjoytrip.service.attraction;

import java.util.List;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.attraction.GugunDto;
import edu.ssafy.enjoytrip.dto.attraction.SidoDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;

public interface AttractionService {

	List<AttractionDto> select(AttractionDto attractionDto); // 다 입력했을 때 조회
	List<SidoDto> selectSido(); // 시도 조회
	List<GugunDto> selectGugun(int sidoCode); // 구군 조회
	AttractionDto selectByContentId(int contentId);

	void makePlan(PlanDto planDto);
	int getPlanId(String userId);
	void planInfo(PlanInfoDto planInfoDto);
	
}
