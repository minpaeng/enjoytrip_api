package edu.ssafy.enjoytrip.dto.like;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LikeDto {
	private String userId;
	private int reviewId;
}
