package edu.ssafy.enjoytrip.dto.file;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FileDto {
	private String fid;
	private String name;
	private String path;
}
