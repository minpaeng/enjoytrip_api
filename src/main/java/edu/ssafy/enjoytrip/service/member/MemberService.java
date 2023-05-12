package edu.ssafy.enjoytrip.service.member;

import java.util.List;

import edu.ssafy.enjoytrip.dto.member.MemberDto;

public interface MemberService {
	/**
	 * 중복된 아이디가 있는지 확인하여 사용 가능한 아이디이면 true, 사용 불가능한 아이디이면 false 리턴
	 * 
	 * @param userId
	 * @return 사용 가능한 아이디이면 true, 사용 불가능한 아이디이면 false 리턴
	 */
	boolean idCheck(String userId); //아이디 중복 체크
	
	void joinMember(MemberDto memberDto); // 회원가입
	
	MemberDto loginMember(String userId, String userPwd); // 로그인
	
	MemberDto select(String userId); //회원 단건 조회
	
	List<MemberDto> list(String[] userId); //회원 여러명 조회
	
	void update(MemberDto memberDto); //회원정보 수정	
	
	void delete(String userId); //회원정보 삭제
	
	/**
	 * 관리자인지 체크하여 관리자라면 true, 관리자가 아니라면 false를 리턴
	 * @param userId
	 * @return 관리자라면  true, 관리자가 아니라면 false를 리턴
	 */
	boolean isAdmin(String userId); // admin 여부 확인
}
