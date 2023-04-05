package com.example.geekslabo.Services.Course;


import com.example.geekslabo.Entities.Lesson;
import com.example.geekslabo.IServices.ICoursesService.ILessonService;
import com.example.geekslabo.Repositories.CourseRepo.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class LessonServiceImpl implements ILessonService {
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> findAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findLessonById(Integer id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson updateLesson(Integer id, Lesson lessonDetails) {
        Lesson existingLesson = lessonRepository.findById(id)
                .orElse(null);

        existingLesson.setName(lessonDetails.getName());
        existingLesson.setDescription(lessonDetails.getDescription());
        existingLesson.setVideoUrl(lessonDetails.getVideoUrl());

        return lessonRepository.save(existingLesson);
    }

    @Override
    public void deleteLesson(Integer id) {

    }





}
