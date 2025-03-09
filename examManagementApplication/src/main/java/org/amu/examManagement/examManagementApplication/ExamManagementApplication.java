package org.amu.examManagement.examManagementApplication;

import org.amu.examManagement.model.Exam;
import org.amu.examManagement.model.Question;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.Users;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class ExamManagementApplication implements CommandLineRunner {

	public static void main(String[] args) { SpringApplication.run(ExamManagementApplication.class, args); }

	@Override
	public void run(String... args) throws Exception {
		Exam exam = new Exam();
		Question question = new Question();
		Quiz quiz = new Quiz();
		Users users = new Users();
		System.out.println("hello World");
	}

}
