package edu.ssafy.enjoytrip.controller.rest;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;
import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.jwt.JwtTokenProvider;
import edu.ssafy.enjoytrip.service.member.MemberService;
import edu.ssafy.enjoytrip.util.SHA256;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberRestController {
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberService memberService;
	private final SHA256 sha256;

	@PostMapping("/login")
    public ResponseEntity<BasicDto> login(@RequestBody Map<String, String> map) {
		String userId = map.get("id");
		String userPwd = map.get("password");

		BasicDto response;
		
		try {
			MemberDto memberDto = memberService.loginMember(userId, sha256.SHA(userPwd));
	        String token = jwtTokenProvider.createToken(memberDto.getUserId(), memberDto.getEmailId());
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("access-token", token);
	        
	        response = BasicDto.builder()
	        		.status(StatusEnum.OK)
	        		.message("success")
	        		.build();
	        
	        return new ResponseEntity<>(response, headers, HttpStatus.OK);
		} catch (Exception e) {
			response = BasicDto.builder()
					.status(StatusEnum.BAD_REQUEST)
					.message("failed")
					.build();
			
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
    }

}
