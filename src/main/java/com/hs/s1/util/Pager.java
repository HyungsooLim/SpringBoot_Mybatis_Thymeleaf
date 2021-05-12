package com.hs.s1.util;

import lombok.Data;

@Data
public class Pager {

	private Long curPage; // 현재 페이지 번호
	private Long perPage; // 1페이지 당 몇개의 Row?
	private Long startRow; // row 시작 번호
	
	private Long startPage;
	private Long lastPage;
	
	private boolean previous;
	private boolean next;
	
	private String kind;
	private String search;

	public void makeRow() {
		// curPage 	startRow
		// 1 		0
		// 2 		10
		// 3 		20
		this.startRow = (this.getCurPage() - 1) * this.getPerPage();
	}

	public void makePage(Long totalCount) {
		Long perBlock = 5L;
		// 1. totalCount

		// 2. totalCount 이용해서 totalPage 구하기
		Long totalPage = totalCount / this.getPerPage();
		if (totalCount / this.getPerPage() != 0) {
			totalPage++;
		}

		// 3. totalPage 이용해서 totalBlock 구하기
		// totalPage 	totalBlock
		// 1 			1
		// 5 			1
		// 6 			2
		// 10 			2
		Long totalBlock = totalPage / perBlock;
		if (totalPage % perBlock != 0) {
			totalBlock++;
		}

		// 4. curPage 이용해서 curBlock 구하기
		// curPage 	curBlock
		// 1 		1
		// 2 		1
		// 6 		2
		// 11 		3
		Long curBlock = this.getCurPage() / perBlock;
		if(this.getCurPage() % perBlock != 0) {
			curBlock++;
		}
		
		// 5. curBlock 이용해서 startPage, lastPage 구하기
		this.startPage = (curBlock - 1) * perBlock+1;
		this.lastPage = curBlock * perBlock;
		
		// 6. curBlock이 처음, 마지막(totalBlock)
		this.previous = true;
		this.next = true;
		
		if(curBlock == totalBlock) {			
			lastPage = totalPage;
			this.next = false;
		}
		
		if(curBlock == 1) {
			this.previous = false;
		}
	}

// Custom Getter, Setter ===========================================
	public Long getPerPage() {
		if (this.perPage == null || this.perPage == 0) {
			this.perPage = 10L;
		}
		return perPage;
	}

	public void setPerPage(Long perPage) {
		if (perPage == null || perPage == 0) {
			this.perPage = 10L;
		} else {
			this.perPage = perPage;
		}
	}

	public void setCurPage(Long curPage) {
		if (curPage == null || curPage == 0) {
			this.curPage = 1L;
		} else {
			this.curPage = curPage;
		}
	}

	public Long getCurPage() {
		if (this.curPage == null || this.curPage == 0) {
			this.curPage = 1L;
		}
		return curPage;
	}
	
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return search;
	}

}
