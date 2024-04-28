package g59939.pae.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String title;

    @Min(1)
    @Max(30)
    private int credits;

    @ManyToMany(mappedBy = "courses")
    private Collection<Student> students;
}
