package com.example.geekslabo.Services.Course;


import com.example.geekslabo.Entities.Course;
import com.example.geekslabo.Entities.Lesson;
import com.example.geekslabo.IServices.ICoursesService.ICourseService;
import com.example.geekslabo.Repositories.CourseRepo.* ;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public CourseServiceImpl(CourseRepository courseRepository, LessonRepository lessonRepository,
                              QuizRepository quizRepository,
                             QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    //************************************  COURSE ************************************ //
    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Integer id, Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElse(null);

        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Integer id) {

    }

    @Override
    public Course findCourseByName(String name) {

        Course course = courseRepository.findByName(name);
        return course;
    }

    @Override
    public void uploadPdfFile(Integer id, MultipartFile file) throws IOException {
        Course course = findCourseById(id);
        course.setPdfFileName(file.getOriginalFilename());
        course.setPdfFileContentType(file.getContentType());
        course.setPdfFileData(file.getBytes());
        courseRepository.save(course);
    }

    @Override
    public byte[] downloadPdfFile(Integer id) throws IOException {
        Course course = findCourseById(id);
        return course.getPdfFileData();
    }

    @Override
    public Course addlessontocourse(Integer id_course, Integer id_lesson) {
        Course course = courseRepository.findById(id_course).orElse(null);
        Lesson lesson = lessonRepository.findById(id_lesson).orElse(null);

        if(course.getLessons().contains(lesson)){
            throw new IllegalArgumentException("lesson already exists in Lesson");
        }
        course.addLesson(lesson);
        return courseRepository.save(course);
    }

    @Override
    public void Qrgenerator(Integer id_course) throws WriterException, IOException {
        Course course = courseRepository.findById(id_course).orElse(null);
        String data = "name :"+course.getName()+ "\n" + "Description :"+course.getDescription()+ "\n" + "PDF  :"+course.getPdfFileName();
        String path ="E:\\QR\\qr.jpg";

        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500,500);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
    }


}


