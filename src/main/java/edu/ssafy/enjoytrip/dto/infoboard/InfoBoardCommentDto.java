package edu.ssafy.enjoytrip.dto.infoboard;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class InfoBoardCommentDto {
	int id;
	String userId;
	int infoBoardId;
	String registerDate;
	String content;
}
