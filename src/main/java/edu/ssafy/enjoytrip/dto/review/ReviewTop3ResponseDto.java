package edu.ssafy.enjoytrip.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewTop3ResponseDto extends ReviewDto {
	int count;
	
	public ReviewTop3ResponseDto(ReviewDto reviewDto, int count) {
		super(reviewDto.getReviewId(), reviewDto.getUserId(), reviewDto.getPlanId(), 
				reviewDto.getTitle(), reviewDto.getContent(), reviewDto.getVisitDate(), 
				reviewDto.getRegisterTime(), reviewDto.getHit(),
				reviewDto.getSpotName(), reviewDto.getSpotAddress(),
				reviewDto.getX(), reviewDto.getY());
		this.count = count;
	}

}
