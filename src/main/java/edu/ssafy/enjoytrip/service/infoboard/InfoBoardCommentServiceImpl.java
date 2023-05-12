package edu.ssafy.enjoytrip.service.infoboard;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardCommentDto;
import edu.ssafy.enjoytrip.repository.infoboard.InfoBoardCommentRepository;

@Service
public class InfoBoardCommentServiceImpl implements InfoBoardCommentService {
	
	private InfoBoardCommentRepository infoBoardCommentRepository;
	
	public InfoBoardCommentServiceImpl(InfoBoardCommentRepository infoBoardCommentRepository) {
		this.infoBoardCommentRepository = infoBoardCommentRepository;
	}
	
	@Override
	public void write(InfoBoardCommentDto infoBoardCommentDto) {
		infoBoardCommentRepository.write(infoBoardCommentDto);
	}

	@Override
	public InfoBoardCommentDto modify(InfoBoardCommentDto infoBoardCommentDto) {
		return infoBoardCommentRepository.modify(infoBoardCommentDto);
	}

	@Override
	public void delete(int infoBoardCommentId) {
		infoBoardCommentRepository.delete(infoBoardCommentId);
	}

	@Override
	public List<InfoBoardCommentDto> list(int infoBoardId) {
		return infoBoardCommentRepository.list(infoBoardId);
	}

	@Override
	public void deleteByPost(int infoBoardId) {
		infoBoardCommentRepository.deleteByPost(infoBoardId);
	}

	@Override
	public int totalCount(int infoBoardId) {
		return infoBoardCommentRepository.totalCount(infoBoardId);
	}

}
