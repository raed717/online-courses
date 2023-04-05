package com.example.geekslabo.Services.Course;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.IServices.ICoursesService.IQuizService;
import com.example.geekslabo.Repositories.CourseRepo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import java.io.IOException;
import java.util.List;
@Service
public class QuizServiceImpl implements IQuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz findQuizById(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) { return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Integer id, Quiz quizDetails) {

        Quiz existingQuiz = quizRepository.findById(id).orElse(null);
        existingQuiz.setName(quizDetails.getName());
        return quizRepository.save(existingQuiz);
    }

    @Override
    public void deleteQuiz(Integer id) {

    }

    @Override
    public Quiz addQuestionToQuiz(Integer quizId, Integer questionId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElse(null);

        Question question = questionRepository.findById(questionId)
                .orElse(null);

        if (quiz.getQuestions().contains(question)) {
            throw new IllegalArgumentException("Question already exists in quiz");
        }

        quiz.addQuestion(question);

        return quizRepository.save(quiz);
    }


}
