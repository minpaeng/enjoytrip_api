package edu.ssafy.enjoytrip.dto.infoboard;

import lombok.Data;

@Data
public class InfoBoardDto {
	int id;
	String userId;
	String title;
	String content;
	int hit;
	String registerDate;
}
