package g59939.pae.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import g59939.pae.business.Pae;
import g59939.pae.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@Data
@AllArgsConstructor
public class TestApi {

    @GetMapping("/testapi")
    public String courses(Model model, RedirectAttributes redirectAttributes) {
        RestTemplate restTemplate = new RestTemplate();
        Pae business = restTemplate.getForObject("http://localhost:8090/api/courses", Pae.class);
        model.addAttribute("courses", business.getCourses());
        model.addAttribute("course", new Course());
        return "courses";
    }
}
