package com.example.myfirstapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    private int courseId;
    private String courseName;
    private  int durationMonths;

    public Course(){}
    public int getCourseId(){return courseId;}
    public void setCourseId(int courseId) {this.courseId=courseId;}

    public String getCourseName() {return courseName;}
    public void setCourseName(String courseName) {this.courseName=courseName;}

    public int getDurationMonths() {return durationMonths;}
    public void setDurationMonths(int durationMonths) {this.durationMonths=durationMonths;}
}
