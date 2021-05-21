package com.hs.s1.member;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String password;
	
	@NotEmpty
	private String name;
	private String email;
	private String phone;

}
