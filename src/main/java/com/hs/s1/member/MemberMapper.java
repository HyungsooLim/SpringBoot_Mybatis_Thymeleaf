package com.hs.s1.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

//	setInsert======================================
	public Integer setJoin(MemberVO memberVO) throws Exception;
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	
	
	
}
