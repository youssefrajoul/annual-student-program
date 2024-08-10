package g59939.pae.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
// import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import g59939.pae.business.Pae;
import g59939.pae.model.Course;
import g59939.pae.model.Student;
import jakarta.validation.Valid;
import lombok.Data;

@Controller
@Data
public class CourseController {

    @Autowired
    private Pae service;

    public CourseController() {
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "youssef");
        return "home";
    }

    @GetMapping("/course")
    public String course(Model model, @RequestParam String courseId) {
        System.out.println("test test test test course function start");
        model.addAttribute("course", service.getCourse(courseId));
        model.addAttribute("students", service.getStudentsInCourse(courseId));
        model.addAttribute("studentsList", service.getStudents());
        int size = 0;
        for (Student element : service.getStudentsInCourse(courseId)) {
            size++;
        }
        System.out.println("test test test test course function end " + size);
        return "course";
    }

    @GetMapping("/courses")
    public String courses(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("course", new Course());
        model.addAttribute("courses", service.getCourses());
        return "courses";
    }

    @PostMapping("/newCourse")
    public String newCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", service.getCourses());
            return "courses";
        }
        service.newCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam String courseId, @RequestParam int studentId, Model model) {
        try {
            service.addStudent(studentId, courseId);
        } catch (Exception e) {
            return "courses";
        }
        return "redirect:/course?courseId=" + courseId;
    }
}
