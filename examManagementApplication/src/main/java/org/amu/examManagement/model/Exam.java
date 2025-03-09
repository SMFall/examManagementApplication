package org.amu.examManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Exam {

    @Id
    private Long id;

    private Integer courseId;
    private String examTitle;
    private Integer teacherId;

    public Exam() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
