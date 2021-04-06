package com.mulcam.ai.web.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	
	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) {
		System.out.println("파일인" + file + "이 들어옴");
		try {
			file.transferTo(new File("D:\\GITHUB\\STS_workspace\\SpringBoot01_Mybatis_Cafe2\\src\\main\\resources\\static\\img\\web_cam\\"+file.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
