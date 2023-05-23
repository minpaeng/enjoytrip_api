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
		return ReviewDto.builder()
				.userId(this.getUserId())
				.title(this.getTitle())
				.content(this.getContent())
				.visitDate(this.getVisitDate())
				.build();
	}
}
