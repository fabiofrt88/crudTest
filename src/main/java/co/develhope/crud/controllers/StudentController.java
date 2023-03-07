package co.develhope.crud.controllers;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import co.develhope.crud.services.StudentService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    // crea uno studente
    @PostMapping("")
    public @ResponseBody
    Student create(@RequestBody Student student){
        return studentRepository.save(student);
    }

    // restituisce tutti gli studenti
    @GetMapping("/")
    public @ResponseBody
    List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // cerca uno studente tramite id
    @GetMapping("/{id}")
    public @ResponseBody  Student getAStudent(@PathVariable long id){
        Optional<Student> student =  studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else{
            return null;
        }
    }

    // aggiorna uno studente dall'id
    @PutMapping("/{id}")
    public @ResponseBody Student update(@PathVariable long id, @RequestBody  @NotNull Student student){
        student.setId(id);
        return studentRepository.save(student);
    }

    // aggiorna il booleano isWorking
    @PutMapping("/{id}/work")
    public @ResponseBody Student setStudentIsWorking(@PathVariable long id, @RequestParam("working") boolean working){
        return studentService.setStudentIsWorkingStatus(id, working);
    }

    // cancella studente
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        studentRepository.deleteById(id);
    }
}
