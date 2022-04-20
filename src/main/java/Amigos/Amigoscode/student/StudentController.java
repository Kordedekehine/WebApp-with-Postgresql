package Amigos.Amigoscode.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController //This anotation makes the whole class a rest EndPoint
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //we use get mapping because we want to get something out of server
    @GetMapping
    public List<Student> getStudent(){
     return studentService.getStudent();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
      studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}") //Update should be change of id, name and email
    public void updateStudent(@PathVariable ("studentId") Long id, @RequestParam (required = false)String name,
                              @RequestParam (required = false) String email){
        studentService.updateStudent(id,name,email);
    }
}
