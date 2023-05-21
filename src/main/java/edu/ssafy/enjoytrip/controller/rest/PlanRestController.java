package edu.ssafy.enjoytrip.controller.rest;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanAttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import edu.ssafy.enjoytrip.dto.review.ReviewDto;
import edu.ssafy.enjoytrip.service.plan.PlanService;
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
		BasicDto response;
		List<PlanAttractionDto> reslist = new ArrayList<>();
		
		try {
			
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
			
			response = BasicDto.builder()
					.status(StatusEnum.OK)
					.message("success")
					.data(reslist)
					.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			response = BasicDto.builder()
					.status(StatusEnum.INTERNAL_SERER_ERROR)
					.message("failed")
					.build();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// 특정 사용자의 여행 계획 목록 조회
	@GetMapping("/rest/{userId}")
	public ResponseEntity<BasicDto> list(@PathVariable("userId") String userId){
		BasicDto response;
		List<PlanAttractionDto> reslist = new ArrayList<>();
		try{
			List<PlanDto> planDtoList = planService.listByUserId(userId);
			for (PlanDto planDto : planDtoList) {
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
			
			response = BasicDto.builder()
					.status(StatusEnum.OK)
					.message("success")
					.data(reslist)
					.build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e){
			response = BasicDto.builder()
					.status(StatusEnum.INTERNAL_SERER_ERROR)
					.message("failed")
					.build();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 플랜 아이디로 계획 조회
	@GetMapping("/rest/planId/{planId}")
	public ResponseEntity<Map<String , Object>> select(@PathVariable("planId") int id){
		Map<String, Object> map = new HashMap<>();
		PlanAttractionDto planAttractionDto = new PlanAttractionDto();
		try{
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
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/rest")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody Map<String, Object> map){

		PlanDto planDto = new PlanDto();
		planDto.setId(Integer.parseInt((String) map.get("planId")));
		planDto.setStartDate((String) map.get("startDate"));
		planDto.setEndDate((String) map.get("endDate"));
		planDto.setTitle((String) map.get("title"));
		planDto.setMemo((String) map.get("memo"));
		planDto.setShare((String) map.get("share"));

		List<String> contentId = (List<String>) map.get("contentId");
		try {
			planService.modify(planDto);

			int planId = Integer.parseInt((String) map.get("planId"));
			int topSequence = planService.topSequence(planId);
			int idx = 1;
			for (String id : contentId) {
				PlanInfoDto planInfoDto = new PlanInfoDto();
				planInfoDto.setPlanId(planId);
				planInfoDto.setContentId(Integer.parseInt(id));
				planInfoDto.setSequence(idx);
				if (idx++ <= topSequence) planService.planInfo(planInfoDto);
				else planService.addPlanInfo(planInfoDto);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/rest/{planId}")
	public ResponseEntity<Map<String,Object>> delete(@PathVariable("planId") int id){
		try {
			planService.deletePlanInfo(id);
			planService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 후기 작성
	@PostMapping("/rest")
	public ResponseEntity<Map<String, Object>> write(@RequestBody Map<String,Object> map){

		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setUserId((String) map.get("userId"));
		reviewDto.setPlanId(Integer.parseInt((String) map.get("planId")));
		reviewDto.setTitle((String) map.get("title"));
		reviewDto.setContent((String) map.get("content"));

		try {
			planService.write(reviewDto);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
