package edu.ssafy.enjoytrip.config.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BasicDto> runtimeException(RuntimeException e) {
        log.error(e.getMessage());
        
        BasicDto body = BasicDto.builder()
        .status(StatusEnum.INTERNAL_SERVER_ERROR)
        .message("failed")
        .data(e.getMessage())
        .build();
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({IOException.class, FileNotFoundException.class})
    public ResponseEntity<BasicDto> exception(Exception e) {
        log.error(e.getMessage());
        
        BasicDto body = BasicDto.builder()
        .status(StatusEnum.BAD_REQUEST)
        .message("failed")
        .data(e.getMessage())
        .build();
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<BasicDto> fileSizeLimitExceededException(MultipartException e) {
        Throwable cause = e.getCause().getCause();
        BasicDto body;
        
        if(cause instanceof FileSizeLimitExceededException) {
        	body = BasicDto.builder()
        	        .status(StatusEnum.BAD_REQUEST)
        	        .message("파일 업로드 용량을 초과했습니다. 제한된 용량: 100MB")
        	        .data(e.getMessage())
        	        .build();
        }
        else {
        	body = BasicDto.builder()
        	        .status(StatusEnum.BAD_REQUEST)
        	        .message("지원하지 않는 파일 첨부 요청입니다.")
        	        .data(e.getMessage())
        	        .build();
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}