package edu.ssafy.enjoytrip.dto.plan;

import edu.ssafy.enjoytrip.dto.attraction.AttractionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlanAttractionDto extends PlanDto{
    List<AttractionDto> attractionList;
    
    public void setPlan(PlanDto planDto) {
    	this.id = planDto.id;
		this.userId = planDto.userId;
		this.startDate = planDto.startDate;
		this.endDate = planDto.endDate;
		this.memo = planDto.memo;
		this.title = planDto.title;
		this.registerDate = planDto.registerDate;
		this.share = planDto.share;
    }
}
