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
		users2.setPassword("password");
		users2.setFirstName("John");
		users2.setLastName("True");
		users2.setUsername("john.true");
		users2.setRole("teacher");
		usersService.saveUsers(users2);

		Users users3 = new Users();
		users3.setActive(true);
		users3.setEmail("bob.low@outlook.com");
		users3.setPassword("password");
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
		users4.setUsername("alice.smith");
		users4.setPassword("password");
		users4.setRole("student");
		usersService.saveUsers(users4);

		List<Users> usersList = new ArrayList<>();
		usersList.add(users4);

		// Création d'autres étudiants "non actifs"
		List<Users> otherUsers = new ArrayList<>();
		for (int i = 1; i < 146; i++) {
			Users usersx = new Users();
			usersx.setActive(Boolean.FALSE);
			usersx.setFirstName("Student " + i);
			usersx.setLastName("Lastname");
			usersx.setEmail("student" + i + "@mymail.fr");
			usersx.setUsername("student" + i + ".lastname");
			usersx.setPassword("password");
			usersx.setRole("student");
			otherUsers.add(usersx);
		}
		usersService.saveAllUsers(otherUsers);

		// Création d'un administrateur
		Users usersAdmin = new Users();
		usersAdmin.setActive(Boolean.TRUE);
		usersAdmin.setFirstName("Michael");
		usersAdmin.setLastName("Brown");
		usersAdmin.setEmail("michael.brown@aol.com");
		usersAdmin.setUsername("michael.brown");
		usersAdmin.setPassword("password");
		usersAdmin.setRole("admin");
		usersService.saveUsers(usersAdmin);

		// Création d'un cours
		Course course = new Course();
		course.setCourseName("Mathématiques");
		course.setCourseDescription("Cours avancé de mathématiques");
		course.setUsersListC(usersList);
		courseService.saveCourse(course);

		Course course2 = new Course();
		course2.setCourseName("Mathématiques approfondies");
		course2.setCourseDescription("Cours avancé de mathématiques approfondies");
		course2.setUsersListC(usersList);
		courseService.saveCourse(course2);

		// Création d'un examen associé à l'enseignant et au cours
		Exam exam = new Exam();
		exam.setCourse(course);
		exam.setExamTitle("Examen de Mathématiques");
		exam.setExamDate(LocalDate.of(2025, 3, 25));
		exam.setUsers(users);
		examService.saveExam(exam);

		Exam exam2 = new Exam();
		exam2.setCourse(course2);
		exam2.setExamTitle("Examen de Mathématiques approfondies");
		exam2.setExamDate(LocalDate.of(2025, 3, 26));
		exam2.setUsers(users);
		examService.saveExam(exam2);

		// Création de questions //
		List<Question> allQuestions = new ArrayList<>();

		// Question 1
		Question question1 = new Question();
		question1.setCategory("Mathématiques");
		question1.setDifficultyLevel("Facile");
		question1.setOption1("2x");
		question1.setOption2("x²");
		question1.setOption3("3x");
		question1.setOption4("x + 2");
		question1.setQuestionTitle("Quelle est la dérivée de x² ?");
		question1.setRightAnswer(1);
		question1.setExam(exam);

		// Question 2
		Question question2 = new Question();
		question2.setCategory("Physique");
		question2.setDifficultyLevel("Moyen");
		question2.setOption1("9.8 m/s²");
		question2.setOption2("9.6 m/s²");
		question2.setOption3("9.81 m/s²");
		question2.setOption4("10 m/s²");
		question2.setQuestionTitle("Quelle est l'accélération due à la gravité sur Terre ?");
		question2.setRightAnswer(3);
		question2.setExam(null);

		// Question 3
		Question question3 = new Question();
		question3.setCategory("Chimie");
		question3.setDifficultyLevel("Difficile");
		question3.setOption1("H₂O");
		question3.setOption2("CO₂");
		question3.setOption3("NaCl");
		question3.setOption4("O₂");
		question3.setQuestionTitle("Quelle est la formule chimique de l'eau ?");
		question3.setRightAnswer(1);
		question3.setExam(null);

		// Question 4
		Question question4 = new Question();
		question4.setCategory("Histoire");
		question4.setDifficultyLevel("Facile");
		question4.setOption1("1914");
		question4.setOption2("1939");
		question4.setOption3("1945");
		question4.setOption4("1968");
		question4.setQuestionTitle("En quelle année a débuté la Première Guerre mondiale ?");
		question4.setRightAnswer(1);
		question4.setExam(null);

		// Question 5
		Question question5 = new Question();
		question5.setCategory("Géographie");
		question5.setDifficultyLevel("Moyen");
		question5.setOption1("Paris");
		question5.setOption2("Lyon");
		question5.setOption3("Marseille");
		question5.setOption4("Toulouse");
		question5.setQuestionTitle("Quelle est la capitale de la France ?");
		question5.setRightAnswer(1);
		question5.setExam(null);

		// Question 6
		Question question6 = new Question();
		question6.setCategory("Français");
		question6.setDifficultyLevel("Moyen");
		question6.setOption1("Victor Hugo");
		question6.setOption2("Emile Zola");
		question6.setOption3("Gustave Flaubert");
		question6.setOption4("Honoré de Balzac");
		question6.setQuestionTitle("Qui a écrit 'Les Misérables' ?");
		question6.setRightAnswer(1);
		question6.setExam(null);

		allQuestions.add(question1);
		allQuestions.add(question2);
		allQuestions.add(question3);
		allQuestions.add(question4);
		allQuestions.add(question5);
		allQuestions.add(question6);
		questionService.saveAllQuestions(allQuestions);

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
		List<Question> quiz1QuestionList = new ArrayList<>();
		quiz1QuestionList.add(question1);
		quiz1.setQuestionList(quiz1QuestionList);

		quiz2.setTitle("Quiz Français");
		List<Question> quiz2QuestionList = new ArrayList<>();
		quiz2QuestionList.add(question6);
		quiz2.setQuestionList(quiz2QuestionList);

		quiz3.setTitle("Quiz Anglais");

		quiz4.setTitle("Quiz Histoire");
		List<Question> quiz4QuestionList = new ArrayList<>();
		quiz4QuestionList.add(question4);
		quiz4.setQuestionList(quiz4QuestionList);

		quiz5.setTitle("Quiz Géographie");
		List<Question> quiz5QuestionList = new ArrayList<>();
		quiz5QuestionList.add(question5);
		quiz5.setQuestionList(quiz5QuestionList);

		quiz6.setTitle("Quiz Espagnol");

		quiz7.setTitle("Quiz Physique");
		List<Question> quiz7QuestionList = new ArrayList<>();
		quiz7QuestionList.add(question2);
		quiz7.setQuestionList(quiz7QuestionList);

		quiz8.setTitle("Quiz Chimie");
		List<Question> quiz8QuestionList = new ArrayList<>();
		quiz8QuestionList.add(question3);
		quiz8.setQuestionList(quiz8QuestionList);

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

		System.out.println("Données initialisées !");
	}
}