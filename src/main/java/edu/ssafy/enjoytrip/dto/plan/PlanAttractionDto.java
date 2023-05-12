package edu.ssafy.enjoytrip.dto.plan;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import lombok.Data;

import java.util.List;

@Data
public class PlanAttractionDto {
    PlanDto planDto;
    List<AttractionDto> attractionDtoList;
}
