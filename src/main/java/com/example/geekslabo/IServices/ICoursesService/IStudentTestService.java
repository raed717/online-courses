package com.example.geekslabo.IServices.ICoursesService;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Quiz;
import com.example.geekslabo.Entities.StudentTest;

import java.util.List;

public interface IStudentTestService {

    StudentTest save(StudentTest studentTest);

    StudentTest getById(Integer id);

    List<StudentTest> getAll();

    List<StudentTest> getByQuiz(Quiz quiz);

    List<StudentTest> getByUser(AppUser user);

    List<StudentTest> getByQuizAndUser(Quiz quiz, AppUser user);


    StudentTest updateTestStudent(Integer id, StudentTest studentTest);


    void deleteStudentTest(Integer id);

    StudentTest addQuizToStudentTest(Integer quizId, Integer studentTestId);

    StudentTest addChosenAnswer(Integer ChosenAnswerId, Integer studentTestId, Integer id_question);

    Boolean validateTest(Integer studentTestId);
}
