package edu.ssafy.enjoytrip.dto.review;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class ReviewSaveRequestDto {
	private String userId;
	private String title;
	private String content;
	private String visitDate;
	
	public ReviewDto toReviewDto() {
    	ReviewDto reviewDto = new ReviewDto();
    	
    	reviewDto.setUserId(this.getUserId());
    	reviewDto.setTitle(this.getTitle());
    	reviewDto.setContent(this.getContent());
    	reviewDto.setVisitDate(this.getVisitDate());
    	
    	return reviewDto;
    }
}
