package edu.ssafy.enjoytrip.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.repository.member.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	MemberRepository memberRepository;
	
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public boolean idCheck(String userId) {
		if (memberRepository.idCheck(userId) == 0) return true;
		return false;
	}

	@Override
	public void joinMember(MemberDto memberDto) {
		memberRepository.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", userId);
		map.put("password", userPwd);
		return memberRepository.loginMember(map);
	}

	@Override
	public MemberDto select(String userId) {
		return memberRepository.select(userId);
	}

	@Override
	public List<MemberDto> list(String[] userId) {
		return memberRepository.list(userId);
	}

	@Override
	public void update(MemberDto memberDto) {
		memberRepository.update(memberDto);
	}

	@Override
	public void delete(String userId) {
		memberRepository.delete(userId);
	}

	@Override
	public boolean isAdmin(String userId) {
		if (memberRepository.checkAdmin(userId) == 'Y') return true;
		return false;
	}

}
