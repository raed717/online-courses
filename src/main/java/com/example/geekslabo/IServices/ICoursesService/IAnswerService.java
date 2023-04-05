package com.example.geekslabo.IServices.ICoursesService;



import com.example.geekslabo.Entities.Answer;
import com.example.geekslabo.Entities.Question;

import java.util.List;

public interface IAnswerService {
    List<Answer> findAllAnswers();
    Answer findAnswerById(Integer id);
    Answer createAnswer(Answer answer);
    Answer updateAnswer(Integer id, Answer answerDetails);
    void deleteAnswer(Integer id);

    Question addAnswerToQuestion(Integer questionId, Integer answerId);
}
