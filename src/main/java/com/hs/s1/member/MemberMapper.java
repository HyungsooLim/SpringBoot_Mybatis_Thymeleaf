package com.hs.s1.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

//	setInsert======================================
	public Integer setInsert() throws Exception;
}
