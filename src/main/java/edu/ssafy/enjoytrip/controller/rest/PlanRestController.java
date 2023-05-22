package edu.ssafy.enjoytrip.controller.rest;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanAttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanAttractionResponseDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.service.plan.PlanService;
import edu.ssafy.enjoytrip.util.SizeConstant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plan")
public class PlanRestController {

	private PlanService planService;

	public PlanRestController(PlanService planService) {
		this.planService = planService;
	}
	
	// 여행 계획 목록 조회
	@GetMapping("/shareboard")
	public ResponseEntity<BasicDto> list(){
		List<PlanAttractionDto> reslist = new ArrayList<>();
		
		List<PlanDto> list = planService.list();
		for (PlanDto planDto : list) {
			PlanAttractionDto planAttractionDto = new PlanAttractionDto();
			planAttractionDto.setPlan(planDto);
			int id = planDto.getId();
			List<Integer> contentId = planService.selectContentId(id);
			List<AttractionDto> attractionList = new ArrayList<>();
			for (Integer content : contentId) {
				AttractionDto attractionDto = planService.selectAttraction(content);
				attractionList.add(attractionDto);
			}
			planAttractionDto.setAttractionList(attractionList);
			reslist.add(planAttractionDto);
		}
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("success")
				.data(reslist)
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	// 특정 사용자의 여행 계획 목록 조회
	@GetMapping("/rest/{userId}")
	public ResponseEntity<BasicDto> list(@PathVariable("userId") String userId, 
			@RequestParam(required = false, defaultValue = "1") int pgno){

		PlanAttractionResponseDto res = planService.listByUserId(userId, pgno - 1, SizeConstant.LIST_SIZE);
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("success")
				.data(res)
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// 플랜 아이디로 계획 조회
	@GetMapping("/{planId}")
	public ResponseEntity<Map<String , Object>> select(@PathVariable("planId") int id){
		Map<String, Object> map = new HashMap<>();
		PlanAttractionDto planAttractionDto = new PlanAttractionDto();
		PlanDto planDto = planService.select(id);
		planAttractionDto.setPlan(planDto);
		int planId = planDto.getId();
		List<Integer> contentId = planService.selectContentId(planId);
		List<AttractionDto> attractionList = new ArrayList<>();
		
		for (Integer content : contentId) {
			AttractionDto attractionDto = planService.selectAttraction(content);
			attractionList.add(attractionDto);
		}
		
		planAttractionDto.setAttractionList(attractionList);
		map.put("planAttractionDto", planAttractionDto);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	// 플랜 수정
	@PutMapping("/rest")
	public ResponseEntity<BasicDto> modify(@RequestBody Map<String, Object> map){
		PlanDto planDto = new PlanDto();
		planDto.setId((Integer) map.get("planId"));
		planDto.setStartDate((String) map.get("startDate"));
		planDto.setEndDate((String) map.get("endDate"));
		planDto.setTitle((String) map.get("title"));
		planDto.setMemo((String) map.get("memo"));
		planDto.setShare((String) map.get("share"));

		List<Integer> contentIds = (List<Integer>) map.get("contentIds");
		
		planService.modify(planDto, contentIds);

		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("success")
				.data(planDto)
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@DeleteMapping("/rest/{planId}")
	public ResponseEntity<Map<String,Object>> delete(@PathVariable("planId") int id){
		planService.deletePlanInfo(id);
		planService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 후기 작성
	@PostMapping("/rest")
	public ResponseEntity<Map<String, Object>> write(@RequestBody Map<String,Object> map){

		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setUserId((String) map.get("userId"));
		reviewDto.setPlanId(Integer.parseInt((String) map.get("planId")));
		reviewDto.setTitle((String) map.get("title"));
		reviewDto.setContent((String) map.get("content"));

		planService.write(reviewDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 플랜 공유 모드 설정
	@PutMapping("/{planId}/share")
	public ResponseEntity<BasicDto> changeShareMode(@PathVariable int planId,
			@RequestBody Map<String,Object> map) {
		
		String shareStr = (String) map.get("share");
		char share = shareStr.charAt(0);
		if (!(share == 'Y' || share == 'N')) throw new IllegalArgumentException("도메인 오류: Y 또는 N으로 요청");
		planService.changeShareMode(planId, share);
		
		BasicDto response = BasicDto.builder()
				.status(StatusEnum.OK)
				.message("success")
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
