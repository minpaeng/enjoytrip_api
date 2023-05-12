package edu.ssafy.enjoytrip.controller.view;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.service.member.MemberService;
import edu.ssafy.enjoytrip.util.SHA256;

//@Controller
//@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	private SHA256 sha256;
	
	public MemberController(MemberService memberService, SHA256 sha256) {
		this.memberService = memberService;
		this.sha256 = sha256;
	}
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam Map<String, String> map,
								HttpSession session, ModelAndView mav) {
		String userId = map.get("login-user-id");
		String userPwd = map.get("login-password");
		
		try {
			MemberDto memberDto = memberService.loginMember(userId, sha256.SHA(userPwd));
			if (memberDto != null) {
				// session 설정
				memberDto.setUserPassword(null);
				session.setAttribute("userInfo", memberDto);
				
				mav.setViewName("redirect:/");
			} else {
				mav.setViewName("error/alert");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "로그인 오류");
			mav.setViewName("error/error");
		}
		
		return mav;
	}
	
	@GetMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/join")
	private ModelAndView join(@RequestParam Map<String, String> map, ModelAndView mav) {
		String id = map.get("user-id");
		String name = map.get("name");
		String pw = map.get("password");
		String email = map.get("email");
		String domain = map.get("domain");

		try {
			memberService.joinMember(new MemberDto(id, name, sha256.SHA(pw), email, domain, null, 'N'));
			mav.setViewName("redirect:/");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "회원가입 오류");
			mav.setViewName("error/error");
		}
		
		return mav;
	}

	@PostMapping("/update")
	private ModelAndView update(@RequestParam Map<String, String> map, ModelAndView mav, HttpSession session) {
		try {
			MemberDto member = (MemberDto)session.getAttribute("userInfo");
			String id = member.getUserId();
			member = memberService.select(id);
			
			String name = map.get("name");
			String pw = map.get("password");
			String email = map.get("email");
			String domain = map.get("domain");
			MemberDto newMember = new MemberDto(id, name, sha256.SHA(pw), email, domain, null, 'N');
			memberService.update(newMember);
			
			session.removeAttribute("userInfo");
			newMember.setUserPassword(null);
			session.setAttribute("userInfo", newMember);
			mav.setViewName("redirect:/");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "회원정보수정 오류");
			mav.setViewName("error/error");
		}
		return mav;
	}


}
