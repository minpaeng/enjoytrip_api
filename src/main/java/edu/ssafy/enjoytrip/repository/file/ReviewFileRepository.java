package edu.ssafy.enjoytrip.repository.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.dto.file.FileDto;
import edu.ssafy.enjoytrip.dto.file.ReviewFileDto;

@Mapper
public interface ReviewFileRepository {
	ReviewFileDto selectFileByFid(int pid);
	List<ReviewFileDto> selectFileByReviewId(int reviewId);
	void saveFile(FileDto fileDto);
	void deleteFile(int pid);
}
