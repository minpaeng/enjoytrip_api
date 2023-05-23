package edu.ssafy.enjoytrip.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponseDto {
	int fid;
	String filePath;
}
