package com.example.myfirstapi;
import jakarta.persistence.*;

@Entity
@Table(name = "users") // mysql mai table  ka naam user rahega ....
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private  String password;

    @Column( nullable = false)
    private String role; //Role_USEr ya "ROLe_Admin

    public User() {}

    public User(String username, String password, String role)
    {
        this.username= username;
        this.password = password;
        this. role = role;

    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username= username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password=password;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
