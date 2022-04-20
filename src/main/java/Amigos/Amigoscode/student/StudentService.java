package Amigos.Amigoscode.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
//        return List.of(new Student
//                (1L,
//                        "Korede",
//                        "salamikehinde345@gmail.com",
//                        LocalDate.of(1999, Month.MARCH,9),
//                        23)
//        );
    }

    public void addNewStudent( Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
      boolean theId = studentRepository.existsById(id);
      if (!theId){
          throw new IllegalStateException("The " + id + " does not exist");
      }
      studentRepository.deleteById(id);
    }

    @Transactional //we don't use query when using transactional annotation
    public void updateStudent(Long id, String name, String email){
       Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
             "student with " + id + " does not exist"
       ) );

       if (name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
           student.setName(name);
       }

       if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
           Optional<Student> studentOptional = studentRepository.findStudentsByEmail(email);
           if (studentOptional.isPresent()){
               throw new IllegalStateException("email taken");
           }
           student.setEmail(email);
       }
    }
}
