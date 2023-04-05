package com.example.geekslabo.IServices.ICoursesService;


import com.example.geekslabo.Entities.Course;
import com.google.zxing.WriterException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICourseService {
    List<Course> findAllCourses();
    Course findCourseById(Integer id);
    Course createCourse(Course course);
    Course updateCourse(Integer id, Course courseDetails);
    void deleteCourse(Integer id);
    public Course findCourseByName(String name);

    /* ------------------------ PDF ------------------------ */
    void uploadPdfFile(Integer id, MultipartFile file) throws IOException;
    byte[] downloadPdfFile(Integer id) throws IOException;
    /* ------------------------ PDF ------------------------ */

    Course addlessontocourse(Integer id_course,Integer id_lesson);


    void Qrgenerator(Integer id_course) throws WriterException, IOException;
}
