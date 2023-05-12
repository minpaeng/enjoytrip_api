package edu.ssafy.enjoytrip.controller.rest;

import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.service.member.MemberService;
import edu.ssafy.enjoytrip.util.SHA256;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/member")
public class MemberRestController {
    private MemberService memberService;
    private SHA256 sha256;

    public MemberRestController(MemberService memberService, SHA256 sha256) {
        this.memberService = memberService;
        this.sha256 = sha256;
    }

    @PostMapping("/rest/join")
    ResponseEntity<Map<String, Object>> joinMember(@RequestBody Map<String, Object> map){

        String userId = (String) map.get("userId");
        String userName = (String) map.get("userName");
        String userPassword = (String) map.get("userPassword");
        String emailId = (String) map.get("emailId");
        String emailDomain = (String) map.get("emailDomain");

        try {
            memberService.joinMember(new MemberDto(userId, userName, sha256.SHA(userPassword), emailId, emailDomain, null, 'N'));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rest")
    ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> map){

        String userId = (String) map.get("userId");
        String userPassword = (String) map.get("userPassword");

        try {
            Map<String, Object> resMap = new HashMap<>();
            memberService.loginMember(userId, sha256.SHA(userPassword));
            resMap.put("login", "로그인 성공^^");
            return new ResponseEntity<>(resMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/rest/{userId}")
    ResponseEntity<Map<String, Object>> idCheck(@PathVariable("userId") String userId){
        try {
            memberService.idCheck(userId);
            Map<String, Object> map = new HashMap<>();
            map.put("idCheck", "중복^^");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rest/userId/{userId}")
    ResponseEntity<Map<String,Object>> select(@PathVariable("userId") String userId){
        Map<String, Object> map = new HashMap<>();
        try {
            MemberDto memberDto = memberService.select(userId);
            map.put("memberDto", memberDto);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rest/list")
    ResponseEntity<Map<String, Object>> list(@RequestBody Map<String, Object> map){
        Map<String, Object> resMap = new HashMap<>();
        List<String> idList = (List<String>) map.get("idList");

        List<MemberDto> memberList = new ArrayList<>();

//        String[] list = new String[idList.size()];
//        int idx = 0;
//        for (String s : idList) list[idx++] = s;

        try {
            for (String id : idList) {
                MemberDto memberDto = memberService.select(id);
                memberList.add(memberDto);
            }
            resMap.put("memberList", memberList);
            return new ResponseEntity<>(resMap, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/rest")
    ResponseEntity<Map<String, Object>> update(@RequestBody Map<String, Object> map){

        String userId = (String) map.get("userId");
        String userName = (String) map.get("userName");
        String userPassword = (String) map.get("userPassword");
        String emailId = (String) map.get("emailId");
        String emailDomain = (String) map.get("emailDomain");

        try {
            memberService.update(new MemberDto(userId, userName, sha256.SHA(userPassword), emailId, emailDomain, null, 'N'));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/rest")
    ResponseEntity<Map<String, Object>> delete(@RequestBody Map<String, Object> map){
        String userId = (String) map.get("userId");
        try {
            memberService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rest/check/{userId}")
    ResponseEntity<Map<String, Object>> checkAdmin(@PathVariable("userId") String userId){
        Map<String, Object> map = new HashMap<>();
        try {
            boolean admin = memberService.isAdmin(userId);
            map.put("admin", admin);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
