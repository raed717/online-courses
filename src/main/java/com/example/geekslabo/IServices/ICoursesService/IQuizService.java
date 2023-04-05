package com.example.geekslabo.IServices.ICoursesService;


import com.example.geekslabo.Entities.Quiz;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IQuizService {
    List<Quiz> findAllQuizzes();
    Quiz findQuizById(Integer id);
    Quiz createQuiz(Quiz quiz);


    Quiz updateQuiz(Integer id, Quiz quizDetails);

    void deleteQuiz(Integer id);

    Quiz addQuestionToQuiz(Integer quizId, Integer questionId);

}
