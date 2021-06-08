package com.hs.s1.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.hs.s1.util.FileManager;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(rollbackFor = Exception.class)
	public Integer setJoin(MemberVO memberVO, MultipartFile multipartFile) throws Exception {
		// 0. 사전작업
		// a. password 암호화
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		// b. 사용자 활성화
		memberVO.setEnabled(true);
		
		// 1. MEMBER table save
		int result = memberMapper.setJoin(memberVO);
		
		// 2. Role Table 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", memberVO.getUsername());
		map.put("roleName", "ROLE_MEMBER");
		result = memberMapper.setMemberRole(map);
		
		// 3. HDD save
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
	
//	Security =====================================================================
	// Login 메서드, 원래 Login 메서드는 사용 X
	// 개발자가 호출 X
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		memberVO = memberMapper.getLogin(memberVO);
		return memberVO;
	}
//	===========================================================================	
//	public MemberVO getLogin(MemberVO memberVO) throws Exception {
//		return memberMapper.getLogin(memberVO);
//	}
	
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
	

	
}// =============
