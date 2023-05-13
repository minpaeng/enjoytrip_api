package edu.ssafy.enjoytrip.dto.infoboard;

import java.util.List;

public class InfoBoardCommentPageDto {
	int pageCount;
	List<InfoBoardCommentDto> list;
	
	public InfoBoardCommentPageDto(int pageCount, List<InfoBoardCommentDto> list) {
		this.pageCount = pageCount;
		this.list = list;
	}
}
