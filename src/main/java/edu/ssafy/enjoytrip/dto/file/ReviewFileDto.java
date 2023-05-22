package edu.ssafy.enjoytrip.dto.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ReviewFileDto extends FileDto {
	private int reviewId;
	private int fid;
}
