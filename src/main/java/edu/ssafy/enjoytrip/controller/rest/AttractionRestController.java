package edu.ssafy.enjoytrip.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ssafy.enjoytrip.dto.attraction.GugunDto;
import edu.ssafy.enjoytrip.dto.attraction.SidoDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.service.attraction.AttractionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/attraction")
public class AttractionRestController {
	
	private AttractionService attractionService;

	public AttractionRestController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@GetMapping("/rest")
	public ResponseEntity<Map<String, Object>> sido(){

		Map<String, Object> map = new HashMap<>();
		List<SidoDto> sido = attractionService.selectSido();
		map.put("sido", sido);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/rest/{code}")
	public ResponseEntity<Map<String, Object>> gugun(@PathVariable("code") int code){
		Map<String, Object> map = new HashMap<>();
		List<GugunDto> gugun = attractionService.selectGugun(code);
		map.put("gugun", gugun);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/rest/{contentTypeId}/{sidoCode}/{gugunCode}")
	public ResponseEntity<Map<String, Object>> select(@PathVariable("contentTypeId") int contentTypeId,
													@PathVariable("sidoCode") int sidoCode,
													@PathVariable("gugunCode") int gugunCode){
		Map<String, Object> map = new HashMap<>();
		AttractionDto attractionDto = new AttractionDto();
		attractionDto.setContentTypeId(contentTypeId);
		attractionDto.setSidoCode(sidoCode);
		attractionDto.setGugunCode(gugunCode);
		log.info(attractionDto.toString());
		List<AttractionDto> list = attractionService.select(attractionDto);
		map.put("list", list);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/rest/contentId/{contentId}")
	public ResponseEntity<Map<String, Object>> selectByContentId(@PathVariable("contentId") int contentId) {
		Map<String, Object> map = new HashMap<>();
		AttractionDto attractionDto = attractionService.selectByContentId(contentId);
		map.put("attractionDto", attractionDto);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping("/rest")
	public ResponseEntity<BasicDto> makePlan(@RequestBody Map<String, Object> map) {
		PlanDto planDto = new PlanDto();
		planDto.setUserId((String) map.get("userId"));
		planDto.setStartDate((String) map.get("startDate"));
		planDto.setEndDate((String) map.get("endDate"));
		planDto.setTitle((String) map.get("title"));
		planDto.setMemo((String) map.get("memo"));
		planDto.setShare((String) map.get("share"));
		attractionService.makePlan(planDto);
		int planId = attractionService.getPlanId((String) map.get("userId"));
		
		int idx = 1;
		List<Integer> contentIds = (List<Integer>) map.get("contentIds");
		for (Integer id : contentIds) {
			PlanInfoDto planInfoDto = new PlanInfoDto();
			planInfoDto.setPlanId(planId);
			planInfoDto.setContentId(id);
			planInfoDto.setSequence(idx++);
			attractionService.planInfo(planInfoDto);
		}
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("플랜 생성 완료")
				.data(planDto)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
