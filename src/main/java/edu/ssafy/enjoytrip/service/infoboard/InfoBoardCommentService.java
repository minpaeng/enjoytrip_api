package edu.ssafy.enjoytrip.service.infoboard;

import java.util.List;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardCommentDto;

public interface InfoBoardCommentService {
	void write(InfoBoardCommentDto infoBoardCommentDto); // 댓글 작성
	void modify(InfoBoardCommentDto infoBoardCommentDto); // 댓글 수정
	void delete(int infoBoardCommentId);
	void deleteByPost(int infoBoardId);
	List<InfoBoardCommentDto> list(int infoBoardId);
	int totalCount(int infoBoardId);
}
