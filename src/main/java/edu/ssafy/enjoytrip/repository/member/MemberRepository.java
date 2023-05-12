package edu.ssafy.enjoytrip.repository.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.ssafy.enjoytrip.dto.member.MemberDto;

@Mapper
public interface MemberRepository {

	int idCheck(@Param("id") String userId); //아이디 중복 체크
	void joinMember(MemberDto memberDto); // 회원가입
	MemberDto loginMember(Map<String, Object> info); // 로그인
	MemberDto select(@Param("id") String userId); //회원 단건 조회
	List<MemberDto> list(String[] userId); //회원 여러명 조회
	void update(MemberDto memberDto); //회원정보 수정	
	void delete(@Param("id") String userId); //회원정보 삭제
	char checkAdmin(@Param("id") String userId); // admin 여부 확인
	
}
