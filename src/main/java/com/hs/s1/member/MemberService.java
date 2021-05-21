package com.hs.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
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
	
//	Custom Validation method ===================================================
	public boolean memberError(MemberVO memberVO, Errors errors) throws Exception {
		boolean result = false;
		
		// 기본 제공 검증 결과 가져와서 담기
		// 기본 검증은 이미 Controller에서 끝남
//		if(errors.hasErrors()) {
//			result = true;
//		}
		result = errors.hasErrors();
		
		//password 일치 여부 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
							 //form path, field // properties의 custom key 값
			errors.rejectValue("passwordCheck", "memberVO.password.notEqual");
			result = true;
		}
		
		//username 중복 여부
		MemberVO checkMember = memberMapper.getUsername(memberVO);
		if(checkMember != null) {
			errors.rejectValue("username", "memberVO.username.memberCheck");
			result = true;
		}
		
		//admin, administrator 안되게 메세지
		MemberVO adminCheck = memberMapper.getUsername(memberVO);
		String strAdmin = adminCheck.getUsername();
		if(strAdmin.equals("admin") || strAdmin.equals("administrator")) {
			errors.rejectValue("username", "memberVO.username.adminCheck");
		}
		
		
		
		
		return result;
	}
	
	
	
}
