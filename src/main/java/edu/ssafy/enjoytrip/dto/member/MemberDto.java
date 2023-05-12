package edu.ssafy.enjoytrip.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberDto {
	
	String userId;
	String userName;
	String userPassword;
	String emailId;
	String emailDomain;
	String joinDate;
	char admin; // Y, N

}
