package com.hs.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;
	
//	@Test
	public void setMemberTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("id2");
		memberVO.setPassword("pw2");
		memberVO.setName("name2");
		memberVO.setPhone("phone2");
		memberVO.setEmail("email2@gmail.com");
		memberVO.setEnabled(true);
		int result = memberMapper.setJoin(memberVO);
		assertEquals(1, result);
	}

//	@Test
	public void setmemberRoleTest() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "id2");
		map.put("roleName", "MEMBER");
		int result = memberMapper.setMemberRole(map);
		assertEquals(1, result);
	}
	
	@Test
	public void getLoginTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("id2");
		memberVO = memberMapper.getLogin(memberVO);
		
		for(RoleVO roleVO:memberVO.getRoles()) {
			System.out.println(roleVO.getRoleName());
		}
		
		assertNotNull(memberVO);
	}
	
	
	
	
	
	
	
}
