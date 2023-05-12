package edu.ssafy.enjoytrip.controller.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import edu.ssafy.enjoytrip.dto.plan.PlanInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ssafy.enjoytrip.dto.attraction.GugunDto;
import edu.ssafy.enjoytrip.dto.attraction.SidoDto;
import edu.ssafy.enjoytrip.dto.plan.PlanDto;
import edu.ssafy.enjoytrip.service.attraction.AttractionService;

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
		try {
			List<SidoDto> sido = attractionService.selectSido();
			map.put("sido", sido);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rest/{code}")
	public ResponseEntity<Map<String, Object>> gugun(@PathVariable("code") int code){
		Map<String, Object> map = new HashMap<>();
		try {
			List<GugunDto> gugun = attractionService.selectGugun(code);
			map.put("gugun", gugun);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		System.out.println(attractionDto.toString());
		try {
			List<AttractionDto> list = attractionService.select(attractionDto);
			map.put("list", list);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rest/contentId/{contentId}")
	public ResponseEntity<Map<String, Object>> selectByContentId(@PathVariable("contentId") int contentId) {
		Map<String, Object> map = new HashMap<>();
		try {
			AttractionDto attractionDto = attractionService.selectByContentId(contentId);
			map.put("attractionDto", attractionDto);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/rest")
	public ResponseEntity<Map<String, Object>> makePlan(@RequestBody Map<String, Object> map) {

		PlanDto planDto = new PlanDto();
		planDto.setUserId((String) map.get("userId"));
		planDto.setStartDate((String) map.get("startDate"));
		planDto.setEndDate((String) map.get("endDate"));
		planDto.setTitle((String) map.get("title"));
		planDto.setMemo((String) map.get("memo"));
		planDto.setShare((String) map.get("share"));

		List<String> contentId = (List<String>) map.get("contentId");

		try {
			attractionService.makePlan(planDto);
			int planId = attractionService.getPlanId((String) map.get("userId"));
			int idx = 1;
			for (String id : contentId) {
				PlanInfoDto planInfoDto = new PlanInfoDto();
				planInfoDto.setPlanId(planId);
				planInfoDto.setContentId(Integer.parseInt(id));
				planInfoDto.setSequence(idx++);
				System.out.println(planInfoDto);
				attractionService.planInfo(planInfoDto);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
