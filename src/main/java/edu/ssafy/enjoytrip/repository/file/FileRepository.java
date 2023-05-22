package edu.ssafy.enjoytrip.repository.file;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.dto.file.FileDto;

@Mapper
public interface FileRepository {
	void selectFile(int pid);
	void saveFile(FileDto fileDto);
	void deleteFile(int pid);
}
