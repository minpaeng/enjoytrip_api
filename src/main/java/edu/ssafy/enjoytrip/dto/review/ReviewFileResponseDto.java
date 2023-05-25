package edu.ssafy.enjoytrip.dto.review;

import java.util.List;

import edu.ssafy.enjoytrip.dto.file.FileResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewFileResponseDto extends ReviewDto {
	private int count;
	private List<FileResponseDto> files;
	
	public ReviewFileResponseDto(ReviewDto reviewDto, int count, List<FileResponseDto> files) {
		super(reviewDto.getReviewId(), reviewDto.getUserId(), reviewDto.getPlanId(), 
				reviewDto.getTitle(), reviewDto.getContent(), reviewDto.getVisitDate(), 
				reviewDto.getRegisterTime(), reviewDto.getHit(),
				reviewDto.getSpotName(), reviewDto.getSpotAddress(),
				reviewDto.getX(), reviewDto.getY());
		this.count = count;
		this.files = files;
	}
}
