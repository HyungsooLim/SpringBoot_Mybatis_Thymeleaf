package com.hs.s1.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.hs.s1.board.BoardMapper;

@Mapper
public interface NoticeMapper extends BoardMapper {
	
	//getList() -> com.hs.s1.board.notice.NoticeMapper namespace 자동으로 찾아가서 동일한 id 실행됨

}
