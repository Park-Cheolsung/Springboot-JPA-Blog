package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//브라우저에서는 Get 방식만 처리함
//사용자가 요청 -> 응답(HTML)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
@Slf4j
public class HttpControllerTest {
	final String TAGPLUS = this.getClass().getSimpleName() + ": ";
	private static final String TAG =  "HttpControllerTest : ";

	@GetMapping("/http/lombok")
	public String lombokTest(Member m) {
//		Member member = Member.builder().username(m.getUsername()).password(m.getPassword()).email(m.getEmail()).build();
//		
//		System.out.println(TAGPLUS + "getter: " + m.getUsername());
//		
//		m.setUsername("cos");
//		System.out.println(TAGPLUS + "setter: " + m.getUsername());
		
		return "lombok test 완료";
		
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		log.info("Get 요청 test complited !!!");
		log.info("Get 요청 id = " + m.getId());

//		log.info(String.format("Member id = %d, username = %s, password = %s, , email = %s", m.getId(), m.getUsername(), m.getPassword(), m.getEmail()));
		
		return "Get 요청 : " + m.getId();
		
	}

	@PostMapping("/http/post")  													//text/plain, application/json
	public String postTest(@RequestBody Member m) {			//Message Conveter to json
		
		log.info("Post 요청 test complited !!!");
		log.info("Post 요청 id = " + m.getId());
		
		//  .MemberBuilder().id(10).password("12345");
//		
//		log.info(String.format("mText = %s", mText));
		log.info(String.format("Member id = %d, username = %s, password = %s, , email = %s", m.getId(), m.getUsername(), m.getPassword(), m.getEmail()));

		return "Post 요청 : " + m.getId();
	}
}
