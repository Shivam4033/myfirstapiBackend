package com.example.myfirstapi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
    List<Student> findByCourse(String course);
    @Query("SELECT s FROM Student s JOIN s.laptop l WHERE l.brand = :brandName")
    List<Student> findStudentByLaptopBrand(@Param("brandName") String brandName);

}
