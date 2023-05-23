package edu.ssafy.enjoytrip.dto.review;

import java.util.List;

import edu.ssafy.enjoytrip.dto.file.FileResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewFileResponseDto extends ReviewDto {
	private List<FileResponseDto> files;
	
	public ReviewFileResponseDto(ReviewDto reviewDto, List<FileResponseDto> files) {
		super(reviewDto.reviewId, reviewDto.userId, reviewDto.planId, 
				reviewDto.title, reviewDto.content, reviewDto.visitDate, 
				reviewDto.registerTime, reviewDto.hit);
		this.files = files;
	}
}
