package edu.ssafy.enjoytrip.dto.review;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewPageResponseDto {
	int pageCount;
	List<ReviewFileResponseDto> reviews; 
}
