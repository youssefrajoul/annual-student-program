package g59939.pae.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g59939.pae.Repository.CourseRepository;
import g59939.pae.Repository.StudentRepository;
import g59939.pae.model.Course;
import g59939.pae.model.Student;

@Service
public class Pae {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Pae() {
    }

    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(String courseId) {
        return courseRepository.findById(courseId).get();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Iterable<Student> getStudentsInCourse(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        return course.getStudents();
    }

    public Iterable<Course> getStudentsCourses(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return student.getCourses();
    }

    public Optional<Student> getStudent(int studentId) {
        return studentRepository.findById(studentId);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void courseEnrolment(int studentId, String courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

}
