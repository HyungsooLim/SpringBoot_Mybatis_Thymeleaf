package com.hs.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public Integer setInsert(MemberVO memberVO) throws Exception {
		return memberMapper.setInsert();
	}
}
