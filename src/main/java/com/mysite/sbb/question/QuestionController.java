package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
		
	private final QuestionService questionService;
	
	@GetMapping("/list")	
	// Model 객체는 따로 생성할 필요없이 컨트롤러 메서드의 매개변수로 지정하기만 하면 스프링부트가 자동으로 Model 객체를 생성한다.
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
		System.out.println("page page -> " + page);
		// Model 객체는 자바 클래스와 테블릿 간의 연결고리 역할을 한다.
		Page<Question> paging = this.questionService.getList(page);

		// Model 객체에 값을 담아부면 템플릿에서 그 값을 사용할 수 있다.
		model.addAttribute("paging", paging);
		return "question_list";
	}
	
	// @GetMappin에 value는 생략가능
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
					
		Question question = this.questionService.getQuestion(id);				
		model.addAttribute("question", question);				
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		// 질문을 저장한다.
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list"; // 질문 저장 후 질문목록으로 이동
	}
	
}
