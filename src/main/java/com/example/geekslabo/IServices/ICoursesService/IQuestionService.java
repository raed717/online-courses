package com.example.geekslabo.IServices.ICoursesService;


import com.example.geekslabo.Entities.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> findAllQuestions();
    Question findQuestionById(Integer id);
    Question createQuestion(Question question);
    Question updateQuestion(Integer id, Question questionDetails);
    void deleteQuestion(Integer id);

    Question addAnswerToQuestion(Integer questionId, Integer answerId);
}
