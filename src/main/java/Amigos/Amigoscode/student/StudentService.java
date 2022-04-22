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
    }

    public void addNewStudent( Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long StudentId) {
      boolean theId = studentRepository.existsById(StudentId);
      if (!theId){
          throw new IllegalStateException("The " + StudentId + " does not exist");
      }
      studentRepository.deleteById(StudentId);
    }

    @Transactional //we don't use query when using transactional annotation
    //Transactional creates a proxy class that intercept a call from external source
    public void updateStudent(Long StudentId, String name, String email){
       Student student = studentRepository.findById(StudentId).orElseThrow(() -> new IllegalStateException(
             "student with " + StudentId + " does not exist"
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
