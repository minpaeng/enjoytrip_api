package edu.ssafy.enjoytrip.repository.infoboard;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardDto;

@Mapper
public interface InfoBoardRepository {
	
	void modify(InfoBoardDto infoBoardDto); // 게시글 수정
	InfoBoardDto select(@Param("id") int infoBoardId); // 단건 조회
	List<InfoBoardDto> list(Map<String, String> map); // 목록 조회
	void delete(@Param("id") int infoBoardId); // 게시긇 삭제
	void write(InfoBoardDto infoBoardDto); // 게시글 작성
	void updateHit(@Param("id") int infoBoardId);
	int totalCount(@RequestParam Map<String, String> map); // 총 게시글 수 계산
	
}
