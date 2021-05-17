package com.hs.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hs.s1.board.BoardFileVO;
import com.hs.s1.board.BoardService;
import com.hs.s1.board.BoardVO;
import com.hs.s1.util.FileManager;
import com.hs.s1.util.Pager;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		pager.makeRow();
		Long totalCount = qnaMapper.getTotalCount(pager);
		pager.makePage(totalCount);
		return qnaMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.setHitUpdate(boardVO);
		return qnaMapper.getSelect(boardVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub
		// 1. QNA table insert
		int result = qnaMapper.setInsert(boardVO);
		// 2. QNA ref update
		result = qnaMapper.setRefUpdate(boardVO);
		// 3. File save
		String filePath="upload/qna/";
		for(MultipartFile file:files) {
			if(file.getSize() == 0) {
				continue;
			}
			String fileName = fileManager.saveFile(file, filePath);
			System.out.println(fileName);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileName);
			boardFileVO.setOgName(file.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
			qnaMapper.setFileInsert(boardFileVO);
		}
		
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		// 1. files table의 fileName 조회
		// 2. board table에서 글 삭제
		// 3. HDD에 파일들을 삭제
		return qnaMapper.setDelete(boardVO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setReplyInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		//boardVO.num = 부모의 글번호
		// 1. step 1 증가 update
		int result = qnaMapper.setReplyUpdate(boardVO);
		
		// 2. reply insert
		result = qnaMapper.setReplyInsert(boardVO);
		
		// 3. File HDD에 저장
		String filePath="upload/qna/reply/";
		for(MultipartFile file:files) {
			if(file.getSize() == 0) {
				continue;
			}
			String fileName = fileManager.saveFile(file, filePath);
			System.out.println(fileName);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileName);
			boardFileVO.setOgName(file.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
			qnaMapper.setFileInsert(boardFileVO);
		}
		
		return result;
	}

	
	
	
	
	
}
