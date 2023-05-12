package edu.ssafy.enjoytrip.dto.infoboard;

import java.util.List;

public class InfoBoardCommentPaeDto {
	int pageCount;
	List<InfoBoardCommentDto> list;
	
	public InfoBoardCommentPaeDto(int pageCount, List<InfoBoardCommentDto> list) {
		this.pageCount = pageCount;
		this.list = list;
	}
}
