package edu.ssafy.enjoytrip.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.dto.member.MemberDto;
import edu.ssafy.enjoytrip.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	 private final MemberRepository memberRepository;

	    @Override
	    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
	        try {
	        	MemberDto user =  memberRepository.findById(id);
		        return new UserDetailsImpl(user);
	        } catch (Exception e) {
	        	throw new UsernameNotFoundException("해당하는 사용자가 없습니다.");
	        }
	    }

}
