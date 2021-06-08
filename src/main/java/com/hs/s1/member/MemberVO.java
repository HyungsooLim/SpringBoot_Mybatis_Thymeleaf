package com.hs.s1.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	
	private String username;
	@Length(max = 10, min = 2)
	private String password;	
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String phone;
	
	private boolean enabled;
	
	//Role 저장
	private List<RoleVO> roles;

	// 검증용----------------------
	private String passwordCheck;

	
	// overriding ====================================================
	
	// Role 처리하는 메서드
	// 위에 멤버변수 roles를 가져와 담아줌
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Collection의 자식인 ArrayList로 변환
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// roleVO에 담겨있는 roleName들을 authorities에 담아
		for(RoleVO roleVO:this.roles) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return authorities;
	}

	// 계정이 만료되지 됐는지?
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정이 잠겨있는지?
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}
	
}
