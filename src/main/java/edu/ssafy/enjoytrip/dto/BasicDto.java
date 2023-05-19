package edu.ssafy.enjoytrip.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicDto {
	private StatusEnum status;
    private String message;
    private Object data;
}
