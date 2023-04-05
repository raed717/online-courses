package com.example.geekslabo.IServices.ICoursesService;


import com.example.geekslabo.Entities.Lesson;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface ILessonService {
    List<Lesson> findAllLessons();
    Lesson findLessonById(Integer id);
    Lesson createLesson(Lesson lesson);
    Lesson updateLesson(Integer id, Lesson lessonDetails);
    void deleteLesson(Integer id);

}
