package edu.ssafy.enjoytrip.controller.rest;

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
@RequestMapping("/plan")
public class PlanRestController {

	private PlanService planService;

	public PlanRestController(PlanService planService) {
		this.planService = planService;
	}
	@GetMapping("/rest")
	// 페이징 처리를 어떻게 해야할지 모르겠습니다 ㅠ.ㅠ
	// 최근에 등록된 여행 계획 10개 조회
	public ResponseEntity<Map<String, Object>> list(){

		Map<String , Object> map = new HashMap<>();
		List<PlanAttractionDto> reslist = new ArrayList<>();
		try {
			List<PlanDto> list = planService.list();
			for (PlanDto planDto : list) {
				PlanAttractionDto planAttractionDto = new PlanAttractionDto();
				planAttractionDto.setPlanDto(planDto);
				int id = planDto.getId();
				List<Integer> contentId = planService.selectContentId(id);
				List<AttractionDto> attractionList = new ArrayList<>();
				for (Integer content : contentId) {
					AttractionDto attractionDto = planService.selectAttraction(content);
					attractionList.add(attractionDto);
				}
				planAttractionDto.setAttractionDtoList(attractionList);
				reslist.add(planAttractionDto);
			}
			map.put("reslist", reslist);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/rest/{userId}")
	public ResponseEntity<Map<String, Object>> list(@PathVariable("userId") String userId){
		Map<String, Object> map = new HashMap<>();
		List<PlanAttractionDto> reslist = new ArrayList<>();
		try{
			List<PlanDto> planDtoList = planService.listByUserId(userId);
			for (PlanDto planDto : planDtoList) {
				PlanAttractionDto planAttractionDto = new PlanAttractionDto();
				planAttractionDto.setPlanDto(planDto);
				int id = planDto.getId();
				List<Integer> contentId = planService.selectContentId(id);
				List<AttractionDto> attractionList = new ArrayList<>();
				for (Integer content : contentId) {
					AttractionDto attractionDto = planService.selectAttraction(content);
					attractionList.add(attractionDto);
				}
				planAttractionDto.setAttractionDtoList(attractionList);
				reslist.add(planAttractionDto);
			}
			map.put("reslist", reslist);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/rest/planId/{planId}")
	public ResponseEntity<Map<String , Object>> select(@PathVariable("planId") int id){
		Map<String, Object> map = new HashMap<>();
		PlanAttractionDto planAttractionDto = new PlanAttractionDto();
		try{
			PlanDto planDto = planService.select(id);
			planAttractionDto.setPlanDto(planDto);
			int planId = planDto.getId();
			List<Integer> contentId = planService.selectContentId(planId);
			List<AttractionDto> attractionList = new ArrayList<>();
			for (Integer content : contentId) {
				AttractionDto attractionDto = planService.selectAttraction(content);
				attractionList.add(attractionDto);
			}
			planAttractionDto.setAttractionDtoList(attractionList);
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
