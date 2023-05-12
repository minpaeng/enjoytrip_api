package edu.ssafy.enjoytrip.dto.infoboard;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InfoBoardPageDto {
	int pageCount;
	List<InfoBoardDto> list;
	
	public InfoBoardPageDto(int pageCount, List<InfoBoardDto> list) {
		this.pageCount = pageCount;
		this.list = list;
	}
}
