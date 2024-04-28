package g59939.pae.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import g59939.pae.business.Pae;
import g59939.pae.model.Student;
import jakarta.validation.Valid;
import lombok.Data;

@Controller
@Data
public class StudentController {
    @Autowired
    private Pae business;

    @GetMapping("/student")
    public String student(Model model, @RequestParam String studentId) {
        model.addAttribute("student", business.getStudent(Integer.valueOf(studentId)).get());
        model.addAttribute("courses", business.getCourses());
        model.addAttribute("courseId", new String());
        model.addAttribute("studentCourses", business.getStudentsCourses(Integer.valueOf(studentId)));
        return "student";
    }

    @GetMapping("/students")
    public String students(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("student", new Student());
        model.addAttribute("students", business.getStudents());
        return "students";
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("students", business.getStudents());
            return "students";
        }
        business.addStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/courseEnrolment")
    public String courseEnrolment(@RequestParam String courseId, @RequestParam int studentId, Model model) {
        business.courseEnrolment(studentId, courseId);
        return "redirect:/student?studentId=" + studentId;
    }

}
