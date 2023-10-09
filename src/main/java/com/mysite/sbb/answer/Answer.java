package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	/* [@ManyToOne]
	 * 부모 자식 관계를 갖는 구조에서 사용함. 부모는(Question) 자식은(Answer)
	 * 답변(answer)은 하나의 질문(question)에 여러개가 달릴 수 있는 구조이다
	 * 따라서 답변 : Many(많은 것) 질문 : One(하나) 임으로 ManyToOne == N:1 관계임.
	 * @MabyToOne 애너테이션을 설정하면 Answer 엔티티의 question 속성과 Question 엔티티가 서로 연결된다.
	 * (데이터베이스에서 외래키(ForeginKey) 관계가 생성됨 
	 */
	@ManyToOne
	private Question question;
}
