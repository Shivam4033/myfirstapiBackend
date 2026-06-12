package com.example.myfirstapi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController    // covert  entire class into link ..
public class StudentController {
    @Autowired
    private StudentService studentService; // Ab controller direct service se baat karega..

    @PostMapping("/add-student")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> addStudent(@org.springframework.web.bind.annotation.RequestBody @jakarta.validation.Valid Student student){
        Student saveStudent = studentService.addStudent(student);
        return ResponseEntity.ok(saveStudent);
    }


    @GetMapping("/get-all-students")
    @org.springframework.security.access.prepost.PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudent();
    }

    @GetMapping("get-student/{id}")
    public Student getStudetById(@PathVariable int id)
    {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/delete-student/{id}")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public org.springframework.http.ResponseEntity<String> deleteStudent(@PathVariable int id)
    {
        studentService.deleteStudent(id);
        return org.springframework.http.ResponseEntity.ok("Bhai , ID " + id + "ka data database se permanent delete ho gaya hai!");
    }
    @GetMapping("/get-by-laptop-brand")
    public List<Student> getStudentByBrand(@RequestParam String brand)
    {
        return studentService.getStudentByLaptopBrand(brand);
    }
    @PutMapping("/update-student/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student )
    {
            return studentService.updateStudent(id, student);
    }


}
