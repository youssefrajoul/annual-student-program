package g59939.pae.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g59939.pae.business.Pae;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class PaeRestController {

    @GetMapping("/courses")
    public Pae hello() {
        return new Pae();
    }
}
