package org.amu.examManagement;

import org.amu.examManagement.model.Course;
import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.Users;
import org.amu.examManagement.services.CourseService;
import org.amu.examManagement.services.ExamService;
import org.amu.examManagement.services.QuestionService;
import org.amu.examManagement.services.QuizService;
import org.amu.examManagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExamManagementApplication implements CommandLineRunner {

	@Autowired
	private ExamService examService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private CourseService courseService;

	public static void main(String[] args) {
		SpringApplication.run(ExamManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Création de trois enseignants
		Users users = new Users();
		users.setActive(true);
		users.setEmail("david.doe@gmail.com");
		users.setPassword("password");
		users.setFirstName("David");
		users.setLastName("Doe");
		users.setUsername("david.doe");
		users.setRole("teacher");
		usersService.saveUsers(users);

		Users users2 = new Users();
		users2.setActive(true);
		users2.setEmail("john.true@yahoo.fr");
		users2.setPassword("abc123");
		users2.setFirstName("John");
		users2.setLastName("True");
		users2.setUsername("john.true");
		users2.setRole("teacher");
		usersService.saveUsers(users2);

		Users users3 = new Users();
		users3.setActive(true);
		users3.setEmail("bob.low@outlook.com");
		users3.setPassword("boblow");
		users3.setFirstName("Bob");
		users3.setLastName("Low");
		users3.setUsername("bob.low");
		users3.setRole("teacher");
		usersService.saveUsers(users3);

		// Création d'un étudiant
		Users users4 = new Users();
		users4.setFirstName("Alice");
		users4.setLastName("Smith");
		users4.setEmail("alice.smith@gmail.com");
		users4.setPassword("password");
		users4.setRole("student");
		usersService.saveUsers(users4);

		// Création d'un cours
		Course course = new Course();
		course.setCourseName("Mathématiques");
		course.setCourseDescription("Cours avancé de mathématiques");
		courseService.saveCourse(course);

		Course course2 = new Course();
		course2.setCourseName("Mathématiques approfondies");
		course2.setCourseDescription("Cours avancé de mathématiques approfondies");
		courseService.saveCourse(course2);

		// Création d'un examen associé à l'enseignant et au cours
		Exam exam = new Exam();
		exam.setCourse(course);
		exam.setExamTitle("Examen de Mathématiques");
		exam.setExamDate(LocalDate.of(2025, 3, 25));
		exam.setUsers(users);
		examService.saveExam(exam);

		Exam exam2 = new Exam();
		exam2.setCourse(course);
		exam2.setExamTitle("Examen de Mathématiques approfondies");
		exam2.setExamDate(LocalDate.of(2025, 3, 26));
		exam2.setUsers(users);
		examService.saveExam(exam2);

		// Création de plusieurs quiz

		Quiz quiz1 = new Quiz();
		Quiz quiz2 = new Quiz();
		Quiz quiz3 = new Quiz();
		Quiz quiz4 = new Quiz();
		Quiz quiz5 = new Quiz();
		Quiz quiz6 = new Quiz();
		Quiz quiz7 = new Quiz();
		Quiz quiz8 = new Quiz();

		quiz1.setTitle("Quiz Mathématiques");
		quiz2.setTitle("Quiz Français");
		quiz3.setTitle("Quiz Anglais");
		quiz4.setTitle("Quiz Histoire");
		quiz5.setTitle("Quiz Géographie");
		quiz6.setTitle("Quiz Espagnol");
		quiz7.setTitle("Quiz Biologie");
		quiz8.setTitle("Quiz Chimie");

		List<Quiz> quizList = new ArrayList<>();
		quizList.add(quiz1);
		quizList.add(quiz2);
		quizList.add(quiz3);
		quizList.add(quiz4);
		quizList.add(quiz5);
		quizList.add(quiz6);
		quizList.add(quiz7);
		quizList.add(quiz8);

		quizService.saveAllQuiz(quizList);

		// Création d'une question
		Question question = new Question();
		question.setCategory("Mathématiques");
		question.setDifficultyLevel("Facile");
		question.setOption1("2x");
		question.setOption2("x²");
		question.setOption3("3x");
		question.setOption4("x + 2");
		question.setQuestionTitle("Quelle est la dérivée de x² ?");
		question.setRightAnswer(1);
		question.setExam(exam);
		questionService.saveQuestion(question);

		System.out.println("Données initialisées !");
	}
}