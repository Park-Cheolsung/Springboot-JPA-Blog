package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html이 아니라 data를 리턴해주는 controller = @RestController
//html은 @Ccontroller 사용
@RestController
public class DummyControllerTest {

	@Autowired			//의존성 주입(DI)
	private UserRepository userRepository;
	
	//http://localhost:8080/blog/dummy/user (Request)
	@DeleteMapping("/dummy/user/{userId}")	
	public String deleteUser (@PathVariable int userId) {
		
		try {
//			userRepository.deleteById(empSeq);
		} catch (EmptyResultDataAccessException e) {
//			return new EmptyResultDataAccessException("삭제에 실패하였습니다. 해당 empSeq는 DB에 없습니다.  empSeq: " + empSeq);
			
			return "삭제에 실패하였습니다. 해당 empSeq는 DB에 없습니다.  empSeq: " + userId;
		}
		
		return "삭제되었습니다.  empSeq: " + userId;
	}
	
	//save 함수는 Primary 키를 전달하지 않으면 insert를 해주고
	//save  함수는 Primary 키를 전달하면 해당 키에 대한 데이터가 있으면 update를 해주고
	//save  함수는 Primary 키를 전달하면 해당 키에 대한 데이터가 없으면 insert를 해준다.
	//email, password
	//http://localhost:8080/blog/dummy/user (Request)
	@Transactional			//함수종료시에 자동 commit이됨.
	@PutMapping("/dummy/user/{userId}")
	public User updateUser (@PathVariable int userId, @RequestBody User requestUser ) {
		//Json 데이터를 요청 => Java Object(MessageConvert의 Jacson 라이브러리) 로 변환해서 받아준다.
		System.out.println("userId : " + userId);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());

		//영속화 처리
		User user = userRepository.findById(userId).orElseThrow(()-> {
			return new IllegalArgumentException("해당 사용자가 없어 수정할 수 없습니다. userId : " + userId);
		});	
		
		//영속화된 오브젝트 값 변경
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
				
		userRepository.save(user);
//		더티체킹 : 함수 종료시에 변경된 사항이 있으면 업데이트 처리한다.
		return user;
	}	
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴받는다.
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "userId", direction = Sort.Direction.DESC) Pageable pageable) {
	
		Page<User> pageUsers = userRepository.findAll(pageable);
		
		//예시
		if (pageUsers.isLast()) {
			
		}
		
		List<User> users = pageUsers.getContent();
		return users;
	}	
	
	//http://localhost:8080/blog/dummy/user/page (Request)
	//http://localhost:8080/blog/dummy/user/page?page=0 첫번째 페이지
//	@GetMapping("/dummy/user")
//	public Page<User> pageList(@PageableDefault(size = 2, sort = "empId", direction = Sort.Direction.DESC) Pageable pageable) {
//		
//		Page<User> users = userRepository.findAll(pageable);
//		
//		return users;
//	}	
	//{id} 주소로 파라메터를 전달받을 수 있음.
	//http://localhost:8080/blog/dummy/user/1000002 (Request)
	@GetMapping("/dummy/user/{userId}")
	public User detail(@PathVariable int userId) {
		
		System.out.println("Run user detail !!!");
		//user/1000002 를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		//그럼 return null이 리턴 되잖냐? 그럼 프로그램에 문제가 있지 않겠니?
		//그래서 Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!!!
		
//		User user = userRepository.findById(userId).get();
		//람다식 처리
//		User user = userRepository.findById(userId).orElseThrow(()-> {
//				return new IllegalArgumentException("해당유저는 없습니다. userId : " + userId);
//		});
		User user = userRepository.findById(userId).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
			
			return new IllegalArgumentException("해당 사용자가 없습니다. userId : " + userId);
		}
	});

//		User user = userRepository.findById(userId).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		});
		
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 (웹브라우저가 이해할 수 있는 데이터) -> Json (Gson 라이브러리)
		//스프링부트 = MessageConverter 가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에 던져줍니다.

//		System.out.println("userName : " + user.getUsername());
		return user;
	}
	
	//http://localhost:8080/blog/dummy/join (Request)
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("userId : " + user.getUserId());
		System.out.println("userName : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("Role : " + user.getRole());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
}

