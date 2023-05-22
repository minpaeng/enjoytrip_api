package edu.ssafy.enjoytrip.repository.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.dto.file.ReviewFileDto;

@Mapper
public interface ReviewFileRepository {
	ReviewFileDto selectFileByFid(int pid);
	List<ReviewFileDto> selectFileByReviewId(int reviewId);
	void saveFile(ReviewFileDto fileDto);
	void deleteFile(int pid);
}
