package Amigos.Amigoscode;

import Amigos.Amigoscode.student.Student;
import Amigos.Amigoscode.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void getStudentTest(){
        Student student = studentRepository.findById(1L).get();
        Assertions.assertThat(student.getId()).isEqualTo(1L);
    }


}
