package edu.ssafy.enjoytrip.repository.infoboard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardCommentDto;

@Mapper
public interface InfoBoardCommentRepository {
	
	void write(InfoBoardCommentDto infoBoardCommentDto); // 댓글 작성
	InfoBoardCommentDto modify(InfoBoardCommentDto infoBoardCommentDto); // 댓글 수정
	void delete(@Param("id") int infoBoardCommentId);
	void deleteByPost(@Param("id") int infoBoardId);
	List<InfoBoardCommentDto> list(@Param("boardId") int infoBoardId); 
	int totalCount(@Param("id") int infoBoardId);
}
