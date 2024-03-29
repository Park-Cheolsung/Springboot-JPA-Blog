package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> 자바(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity			//User 클래스가 Mysql 에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert			//inser시에 널인 필드를 제외시켜준다.
public class User {

	@Id																													//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 
	private int userId;																					//오라클 시퀸스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username;	//id
	
	@Column(nullable = false, length = 100)											//해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
	//DB는 RoleType 이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;																					//Enum을 쓰는게 좋다.		admin, user, manager
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
