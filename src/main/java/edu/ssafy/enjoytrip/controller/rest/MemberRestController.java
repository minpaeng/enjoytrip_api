package edu.ssafy.enjoytrip.controller.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> login(@RequestBody Map<String, String> map) {
		String userId = map.get("id");
		String userPwd = map.get("password");
		MemberDto memberDto = memberService.loginMember(userId, sha256.SHA(userPwd));
        String token = jwtTokenProvider.createToken(memberDto.getUserId(), memberDto.getEmailId());
        
        return ResponseEntity.ok()
                .header("Access-Token", token)
                .body("토큰 발급 완료");
    }

}
