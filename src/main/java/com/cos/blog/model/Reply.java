package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity			//Reply 클래스가  오라클에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//SEQUENCY
	private int replyId;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne																		//Many = Reply, One = Board
	@JoinColumn(name = "boardId")
	private Board board;														//DB는 오프젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	@ManyToOne																		//Many = Reply, User = One
	@JoinColumn(name = "userId")
	private User user;															//DB는 오프젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	@CreationTimestamp														//시간이 자동입력
	private Timestamp createDate;
}
