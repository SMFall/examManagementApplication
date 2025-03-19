package org.amu.examManagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String examTitle;
    private LocalDate examDate;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Users> usersListE;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public Exam() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public LocalDate getExamDate() { return examDate; }

    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }

    public Users getUsers() { return users; }

    public void setUsers(Users users) { this.users = users; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course;}
}
