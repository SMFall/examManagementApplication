package org.amu.examManagement;

import org.amu.examManagement.model.Course;
import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.Teacher;
import org.amu.examManagement.model.Student;
import org.amu.examManagement.services.CourseService;
import org.amu.examManagement.services.ExamService;
import org.amu.examManagement.services.QuestionService;
import org.amu.examManagement.services.QuizService;
import org.amu.examManagement.services.TeacherService;
import org.amu.examManagement.services.StudentService;
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
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	public static void main(String[] args) {
		SpringApplication.run(ExamManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Création d'un enseignant
		Teacher teacher = new Teacher();
		teacher.setActive(true);
		teacher.setEmail("david.doe@gmail.com");
		teacher.setPassword("password");
		teacher.setFirstName("David");
		teacher.setLastName("Doe");
		teacher.setUsername("david.doe");
		teacherService.saveTeacher(teacher);

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
		exam.setTeacher(teacher);
		examService.saveExam(exam);

		Exam exam2 = new Exam();
		exam2.setCourse(course);
		exam2.setExamTitle("Examen de Mathématiques approfondies");
		exam2.setExamDate(LocalDate.of(2025, 3, 26));
		exam2.setTeacher(teacher);
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

		// Création d'un étudiant
		Student student = new Student();
		student.setFirstName("Alice");
		student.setLastName("Smith");
		student.setEmail("alice.smith@gmail.com");
		studentService.saveStudent(student);

		System.out.println("Données initialisées !");
	}
}