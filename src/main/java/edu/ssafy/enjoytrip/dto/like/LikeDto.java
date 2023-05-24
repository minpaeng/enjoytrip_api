package edu.ssafy.enjoytrip.dto.like;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LikeDto {
	private String user_id;
	private int review_id;
}
