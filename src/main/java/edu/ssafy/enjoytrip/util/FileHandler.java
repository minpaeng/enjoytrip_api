package edu.ssafy.enjoytrip.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import edu.ssafy.enjoytrip.dto.file.FileDto;
import edu.ssafy.enjoytrip.dto.file.ReviewFileDto;
import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.repository.file.ReviewFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class FileHandler {
	@Value("${file.path.review}")
	private String reviewPath;

	@Value("${file.path.profile}")
	private String profilePath;
	
	private final ReviewFileRepository reviewFileRepository;

	// review에 등록할 사진 파일 저장
	public void parseFileInfo(List<MultipartFile> multipartFiles, int reviewId) {
		// 파일이 첨부된 경우
		if (!CollectionUtils.isEmpty(multipartFiles)) {
			// 파일명을 업로드 한 날짜로 변환하여 저장
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String current_date = now.format(dateTimeFormatter);

			// 파일을 저장할 세부 경로 지정
			String path = reviewPath + File.separator + current_date;
			File file = new File(path);

			// 디렉터리가 존재하지 않을 경우
			if (!file.exists()) {
				// 디렉터리 생성
				if (!file.mkdirs())
					throw new IllegalStateException("파일 저장 디렉토리 생성중 오류 발생");
			}

			// 다중 파일 처리
			for (MultipartFile multipartFile : multipartFiles) {

				// 이미지 파일인지 검증
				validateContentType(multipartFile);

				// 유니크한 파일명 설정
				String new_file_name = System.nanoTime() + multipartFile.getOriginalFilename();

				// 업로드 한 파일 데이터를 지정한 파일에 저장
				file = new File(path + File.separator + new_file_name);
				log.info("저장될 파일 경로: " + path + "/" + new_file_name);
				try {
					multipartFile.transferTo(file);
					
					//DB에 파일 정보 저장
					ReviewFileDto fileDto = new ReviewFileDto();
					fileDto.setReviewId(reviewId);
					fileDto.setName(file.getName());
					fileDto.setPath(file.getAbsolutePath());
					reviewFileRepository.saveFile(fileDto);
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
	}

	public String parseFileInfo(MultipartFile multipartFile, MemberDto member, String memberProfilePath) {
		// 파일이 첨부되지 않은 경우
		if (multipartFile == null)
			throw new IllegalArgumentException("파일이 첨부되지 않았습니다.");

		// 확장자명이 존재하지 않을 경우 에러
		validateContentType(multipartFile);

		// 프로필 사진이 설정되어 있다면 삭제
		if (memberProfilePath != null) {
			File originalFilePath = new File(profilePath + memberProfilePath);

			if (originalFilePath.exists()) {
				if (!originalFilePath.delete()) {
					log.error("기존에 설정된 프로필 사진 삭제에 실패했습니다.");
					throw new IllegalStateException("기존에 설정된 프로필 사진 삭제에 실패했습니다.");
				}
				
			}
		}

		// 파일명 저장 경로 및 파일명 설정
		String fileName = member.getUserId() + "_" + multipartFile.getOriginalFilename();
		String filePath = profilePath + fileName;

		// 업로드 한 파일 데이터를 지정한 파일에 저장
		File file = new File(filePath);
		log.info("프로필 사진 저장 경로: " + filePath);
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			throw new IllegalArgumentException("프로필 사진 변경에 실패했습니다: " + e);
		}

		return fileName;
	}

	private void validateContentType(MultipartFile multipartFile) {
		// 파일의 확장자 추출
		String contentType = multipartFile.getContentType();

		if (ObjectUtils.isEmpty(contentType)) {
			throw new IllegalArgumentException("사진 파일만 업로드해주세요.");
		}
		// 확장자가 jpeg, png인 파일들만 받아서 처리
		else if (!contentType.contains("image/jpeg") && !contentType.contains("image/png")) {

			throw new IllegalArgumentException("사진 파일만 업로드해주세요.");
		}
	}

	public void deletePhotosInServer(List<FileDto> photos) {
		for (FileDto photo : photos) {
			String filePath = reviewPath + photo.getName();
			try {
				File file = new File(filePath);
				if (!file.delete()) {
					throw new IllegalStateException("포스트 사진 삭제에 실패했습니다.");
				}
			} catch (Exception e) {
				throw new IllegalStateException("포스트 사진 삭제에 실패했습니다: " + e);
			}
		}
	}

}
