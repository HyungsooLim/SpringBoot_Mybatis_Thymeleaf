package com.hs.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hs.s1.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Transactional(rollbackFor = Exception.class)
	public Integer setJoin(MemberVO memberVO, MultipartFile multipartFile) throws Exception {
		// 1. MEMBER table save
		int result = memberMapper.setJoin(memberVO);
		// 2. HDD save
		String filePath="upload/member/";
		if(multipartFile.getSize() != 0) {
			String fileName = fileManager.saveFile(multipartFile, filePath);
			System.out.println(fileName);
			MemberFileVO memberFileVO = new MemberFileVO();
			memberFileVO.setFileName(fileName);
			memberFileVO.setOgName(multipartFile.getOriginalFilename());
			memberFileVO.setUsername(memberVO.getUsername());
			
		// 3. MEMBERFILE table save
			result = memberMapper.setFileJoin(memberFileVO);
		}
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberMapper.getLogin(memberVO);
	}
}
