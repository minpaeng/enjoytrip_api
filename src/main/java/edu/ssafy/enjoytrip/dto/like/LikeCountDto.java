package edu.ssafy.enjoytrip.dto.like;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LikeCountDto {
	int reviewId;
	int count;
}
