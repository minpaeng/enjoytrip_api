package edu.ssafy.enjoytrip.dto.infoboard;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InfoBoardCommentPageDto {
	int pageCount;
	List<InfoBoardCommentDto> list;
	
	public InfoBoardCommentPageDto(int pageCount, List<InfoBoardCommentDto> list) {
		this.pageCount = pageCount;
		this.list = list;
	}
}
