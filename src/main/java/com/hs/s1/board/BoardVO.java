package com.hs.s1.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
@Getter						// getter 자동생성
@Setter						// setter 자동생성
@ToString					// toString overiding BoardVO(num=1, title=title1, writer=writer1, contents=null, regDate=null, hit=null)
@NoArgsConstructor			// default 생성자
@AllArgsConstructor			// 매개변수가 있는 생성자
------------------------ @Data --------------------------
@EqualsAndHashCode			// hash, equals 메서드
@RequiredArgsConstructor	// 필수 매개변수만 있는 생성자 생성
*/
@Data						// 위 5개 다 있는 annotation
public class BoardVO {

	private Long num;
	private String title;
	private String writer;
	private String contents;
	private Date regDate;
	private Long hit;
	
	
	
}
