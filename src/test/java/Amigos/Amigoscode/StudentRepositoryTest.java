package Amigos.Amigoscode;

import Amigos.Amigoscode.student.Student;
import Amigos.Amigoscode.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void saveStudent(){
        Student student = new Student();
        student.setName("Korede");
        student.setId(1L);
        student.setAge(24);
        student.setEmail("him@gmail.com");
        student.setDob(LocalDate.of(2022, Month.APRIL,12));

        studentRepository.save(student);
        Assertions.assertThat(1L).isGreaterThan(0);
    }


    @Test
    public void getStudentTest(){
        Student student = studentRepository.findById(1L).get();
        Assertions.assertThat(student.getId()).isEqualTo(1L);
    }


}
