package g59939.pae.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g59939.pae.business.Pae;
import g59939.pae.model.Course;
import g59939.pae.model.Student;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class PaeRestController {
    @Autowired
    private Pae pae;

    @GetMapping("/courses")
    public List<Course> courses() {
        return (List<Course>) pae.getCourses();
    }

    @GetMapping("/students")
    public List<Student> students() {
        return (List<Student>) pae.getStudents();
    }

    @GetMapping("")
    public String hello() {
        return "Hello World, Welcome to Youssef Rajoul's spring boot web app";
    }
    
}
