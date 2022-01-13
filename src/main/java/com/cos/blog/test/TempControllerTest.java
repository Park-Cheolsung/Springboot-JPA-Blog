package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller				//해당경로에 있는 파일을 리턴해준다.
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명 : /home.html
		//플경로 : sec/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/image")
	public String imageTest() {
		System.out.println("tempHome()");
		
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명 : /home.html
		//플경로 : sec/main/resources/static/home.html
		return "/girl.png";
	}

	@GetMapping("/temp/jsp")
	public String imageJsp() {
		System.out.println("tempJsp()");
		
//	      prefix: /WEB-INF/views/
//	      suffix: .jsp
		return "test";
	}	
	
}
