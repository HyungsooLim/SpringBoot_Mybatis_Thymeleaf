package com.hs.s1.util;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	// resources 까지의 경로를 가지고 있는 객체
	@Autowired
	private ResourceLoader resourceLoader;

	public String saveFile(MultipartFile multipartFile, String filePath) throws Exception {
		//filePath : /resources/static/ 제외한 나머지 하위경로
		// 1. 경로 설정
		
		/**
		 * - 저장할 폴더가 시스템에 고정일 경우 classpath 알아올려고 지랄 안해도 됨
		 * - String path = "저장할 경로";
		 * - File file = new File(path, filePath);
		 * */
		
		
		/** @1번방법
		String path = "classpath:/static/";
	
		 * - ResourceLoader
		 * - classpath 경로를 받아오기위해 사용
		
							// 경로 : classpath:static/		| File 객체로	,| 나머지 하위 경로
		File file  = new File(resourceLoader.getResource(path).getFile(), filePath);
 		* */
		
		/** @2번방법
		 * - ClassPathResource
		 * - classPath 경로를 받아오기 위해 사용
		 * - ResourceLoader와 같지만 시작 경로에서 classpath를 제외
		 * */
		
		
		String path="static";
		ClassPathResource classPathResource = new ClassPathResource(path);
		File file = new File(classPathResource.getFile(), filePath);
// END
		System.out.println(file.getAbsolutePath());
		// 경로에 폴더 없으면 만들기
		if(!file.exists()) {
			file.mkdirs();
		}
		
		// 2. 저장할 파일명 생성
		String fileName= UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		// 3. 저장
		//	위에 file 경로명, 만든 파일명
		file = new File(file, fileName);
		
		//transfer -> 실제 저장
		multipartFile.transferTo(file);
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		return fileName;
	}
	
}
