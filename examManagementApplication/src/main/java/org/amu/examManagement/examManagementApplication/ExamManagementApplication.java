package org.amu.examManagement.examManagementApplication;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.User;
import org.amu.examManagement.services.ExamService;
import org.amu.examManagement.services.QuestionService;
import org.amu.examManagement.services.QuizService;
import org.amu.examManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamManagementApplication implements CommandLineRunner {

	@Autowired
	private ExamService examService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) { SpringApplication.run(ExamManagementApplication.class, args); }

	@Override
	public void run(String... args) throws Exception {
		Exam exam = new Exam();
		Question question = new Question();
		Quiz quiz = new Quiz();
		User user = new User();

		// Sauvegarde des données dans la BD
		examService.saveExam(exam);
		questionService.saveQuestion(question);
		quizService.saveQuiz(quiz);
		userService.saveUser(user);

		System.out.println("Hello World!");
	}

}
