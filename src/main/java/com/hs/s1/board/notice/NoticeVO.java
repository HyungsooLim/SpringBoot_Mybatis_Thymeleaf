package com.hs.s1.board.notice;

import java.util.List;

import com.hs.s1.board.BoardFileVO;
import com.hs.s1.board.BoardVO;

import lombok.Data;

@Data
public class NoticeVO extends BoardVO {
	
	private List<BoardFileVO> files;

}
