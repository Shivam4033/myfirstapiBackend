package com.example.myfirstapi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Spring ko bata rahe hai ki ye Business class hai..
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student)
    {
        return studentRepository.save(student);
    }
    public List<Student> getAllStudent()
    {
        return studentRepository.findAll();
    }
    public Student getStudentById(int id)
    {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bhai ID " +id + "ka koi student id nahi mila"));
    }
    public void deleteStudent(int id)
    {
        studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Bhai , delete karne ke liye Id " +id+ "mili hi nahi! "));
        studentRepository.deleteById(id);
    }
    public List<Student> getStudentByLaptopBrand(String brand)
    {
        return studentRepository.findStudentByLaptopBrand(brand);
    }
    public Student updateStudent(int id, Student updateStudent)
    {
        Student existingStudent = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Bhai, " +id + "ID wala student databse me hai hi nahi!"));
        existingStudent.setName((updateStudent.getName()));
        existingStudent.setCourse(updateStudent.getCourse());

        if(updateStudent.getLaptop() !=null)
        {
            existingStudent.setLaptop(updateStudent.getLaptop());
        }
        return studentRepository.save(existingStudent);
    }

    public Student addStudent(@Valid Student student) {
        return studentRepository.save(student);
    }
}
