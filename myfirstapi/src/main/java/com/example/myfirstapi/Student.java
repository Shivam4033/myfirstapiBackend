package com.example.myfirstapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // yeh line hibernate ke aalasi/proxy objects ko ignore karne ko bohot payar se bolti hai
public class Student {
    @Id
    private int id;
    @NotBlank(message = "Bhai, student ka naam khali  nahi chhod sakte !")
    @Size(min = 2, message =  "Bhai, naam kam se kam 2 letter ka hona chahiye hai!")
    private  String name;
    @NotBlank(message = "Bhai course ka naam dena zaroori hai !")
    private String course;

    // Laptop table..
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //Cascade ka matalb yeh hota hai ki student save/delete hoga
                                        // toh Laptop apne aap save/ delete ho jayega ..
    @JoinColumn(name = "fk_laptop_id")  //MYSQL mai foreign key column ka naam kya hoga
    private Laptop laptop;

    // course table....
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_student_id")
    private List<Course> courses;

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name) {this.name = name;}

    public String getCourse() {return course;}
    public void setCourse(String course){this.course=course;}

    public Laptop getLaptop() {return laptop;}
    public void setLaptop(Laptop laptop) {this.laptop=laptop;}

    public List<Course> getCourses() {return courses;}
    public void setCourses(List<Course> courses) {this.courses=courses;}

}
