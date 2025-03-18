package org.amu.examManagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String username;
    private String role;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exam> exams = new ArrayList<>();

    @ManyToMany(mappedBy="usersListE",fetch=FetchType.EAGER)
    private List<Exam> examList;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Course> courseList;

    //Constructor
    public Users() {};
    public Users(Long id, boolean active, String email, String username, String password, String firstName, String lastName, String role ) {
        this.id = id;
        this.active = active;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    //Getters and Setters
    public Long getUser_id() {
        return id;
    }
    public void setUser_id(Long user_id) {
        this.id = user_id;
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
            return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


}
