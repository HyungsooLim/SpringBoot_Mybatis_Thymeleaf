package com.hs.s1.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.hs.s1.board.BoardMapper;
import com.hs.s1.board.BoardVO;

@Mapper
public interface QnaMapper extends BoardMapper{

	public Integer setReplyInsert(BoardVO boardVO) throws Exception;
	
	public Integer setReplyUpdate(BoardVO boardVO) throws Exception;
	
	public Integer setRefUpdate(BoardVO boardVO) throws Exception;
	
}
