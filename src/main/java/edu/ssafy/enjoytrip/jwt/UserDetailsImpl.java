package edu.ssafy.enjoytrip.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.ssafy.enjoytrip.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
	 private final MemberDto user;

	    public MemberDto getUser() {
	        return user;
	    }

	    @Override
	    public String getPassword() {
	        return user.getUserPassword();
	    }

	    @Override
	    public String getUsername() {
	        return user.getUserName();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return null;
	    }
}
