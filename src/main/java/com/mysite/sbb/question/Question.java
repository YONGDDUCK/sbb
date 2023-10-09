package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

// lombok Getterm Setter 메서드 자동 생성을 위한 애너테이션 적용
@Getter
@Setter
@Entity // Entitiy 애너테이션을 적용해야 JAP가 엔티티로 인식한다.
public class Question {
	
	@Id // id 속성을 기본키(primary key) 로 지정한다.	
	/* 데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장됨
	   strategy 는 고유번로를 생성하는 옵션으로 GenerationType.IDENTITY는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용한다.
	   strategy 옵션을 생격할 경우 @GeneratedValue 애너테이션이 지정된 컬럼들이 모두 동일한 시퀀스로 번호를 생성하기 떄문에 일정한 순서의 고유번호를 가질수 없게 된다.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	// columnDefinition 컬럼 속성을 정의할 때 사용함 text는 글자 수 제한 안둠
	@Column(columnDefinition = "TEXT")
	private String content;
	
	// @Column 애너테이션을 사용하지 않아도 @Entity 속성은 테이블 컬럼으로 인식함. 테이블 컬럼으로 인식하고 싶지 않으면 @Transient 애너테이션을 사용한다.
	private LocalDateTime createDate;
	
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
}
