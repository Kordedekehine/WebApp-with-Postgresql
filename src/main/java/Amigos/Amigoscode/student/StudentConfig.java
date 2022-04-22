package Amigos.Amigoscode.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student korede = new Student
                    (
                            "korede",
                            "salamikehinde345@gmail.com",
                            LocalDate.of(1999, Month.MARCH, 9)
                            );

            Student salami = new Student(
                    "Salami",
                            "salamikorede@gmail.com",
                            LocalDate.of(1978, Month.APRIL, 9)
            );

                   repository.saveAll(List.of(korede,salami));
        };
    }
}
