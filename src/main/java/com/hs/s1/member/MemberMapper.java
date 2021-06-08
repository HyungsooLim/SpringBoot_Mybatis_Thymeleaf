package com.hs.s1.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

//	setInsert======================================
	public Integer setJoin(MemberVO memberVO) throws Exception;
	
	public Integer setFileJoin(MemberFileVO memberFileVO) throws Exception;
	
	public Integer setMemberRole(Map<String, String> map) throws Exception;
	
	public MemberVO getLogin(MemberVO memberVO);
	
	public MemberVO getUsername(MemberVO memberVO) throws Exception;
	
	
	
	
}
