package edu.ssafy.enjoytrip.dto.plan;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlanAttractionResponseDto {
	int totalCount;
	List<PlanAttractionDto> plans;
}
