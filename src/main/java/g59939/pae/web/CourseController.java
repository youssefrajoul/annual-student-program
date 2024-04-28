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
import jakarta.validation.Valid;
import lombok.Data;

@Controller
@Data
public class CourseController {

    @Autowired
    private Pae business;

    public CourseController() {
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "youssef");
        return "home";
    }

    @GetMapping("/course")
    public String course(Model model, @RequestParam String courseId) {
        model.addAttribute("course", business.getCourse(courseId));
        model.addAttribute("students", business.getStudentsInCourse(courseId));
        return "course";
    }

    @GetMapping("/courses")
    public String courses(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("course", new Course());
        model.addAttribute("courses", business.getCourses());
        return "courses";
    }

    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", business.getCourses());
            return "courses";
        }
        
        business.addCourse(course);
        return "redirect:/courses";
    }
}
