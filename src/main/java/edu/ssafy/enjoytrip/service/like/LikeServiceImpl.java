package edu.ssafy.enjoytrip.service.like;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.dto.like.LikeDto;
import edu.ssafy.enjoytrip.repository.like.LikeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
	private final LikeRepository likeRepository;

	@Override
	public void markLike(LikeDto likeDto, String userId) {
		if (!userId.equals(likeDto.getUserId())) {
			throw new IllegalAccessError("잘못된 접근입니다.");
		}
		likeRepository.createLike(likeDto);
	}

	@Override
	public void cancelLike(LikeDto likeDto, String userId) {
		if (!userId.equals(likeDto.getUserId())) {
			throw new IllegalAccessError("잘못된 접근입니다.");
		}
		likeRepository.deleteLike(likeDto);
	}

	@Override
	public boolean markStatus(LikeDto likeDto, String userId) {
		if (!userId.equals(likeDto.getUserId())) {
			throw new IllegalAccessError("잘못된 접근입니다.");
		}
		int n = likeRepository.checkMarkStatus(likeDto);
		if (n == 1) return true;
		else return false;
	}

}
