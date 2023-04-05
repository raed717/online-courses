package com.example.geekslabo.Services.Course;

import com.example.geekslabo.Entities.*;
import com.example.geekslabo.IServices.ICoursesService.IStudentTestService;
import com.example.geekslabo.Repositories.CourseRepo.AnswerRepository;
import com.example.geekslabo.Repositories.CourseRepo.QuestionRepository;
import com.example.geekslabo.Repositories.CourseRepo.QuizRepository;
import com.example.geekslabo.Repositories.CourseRepo.StudentTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentTestImpl implements IStudentTestService {
    @Autowired
    private StudentTestRepository studentTestRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public StudentTest save(StudentTest studentTest) {
        return studentTestRepository.save(studentTest);
    }

    @Override
    public StudentTest getById(Integer id) {
        Optional<StudentTest> studentTestOptional = studentTestRepository.findById(id);
        return studentTestOptional.orElse(null);
    }

    @Override
    public List<StudentTest> getAll() {
        return studentTestRepository.findAll();
    }

    @Override
    public List<StudentTest> getByQuiz(Quiz quiz) {
        return studentTestRepository.findByQuiz(quiz);
    }

    @Override
    public List<StudentTest> getByUser(AppUser user) {
        return studentTestRepository.findByUser(user);
    }

    @Override
    public List<StudentTest> getByQuizAndUser(Quiz quiz, AppUser user) {
        return studentTestRepository.findByQuizAndUser(quiz, user);
    }

    @Override
    public StudentTest updateTestStudent(Integer id, StudentTest studentTestDetails) {
        StudentTest existingTest = studentTestRepository.findById(id).orElse(null);
        existingTest.setQuiz(studentTestDetails.getQuiz());
        existingTest.setUser(studentTestDetails.getUser());
        existingTest.setChosenAnswers(studentTestDetails.getChosenAnswers());
        existingTest.setAttemptTime(studentTestDetails.getAttemptTime());
        existingTest.setAttemptNumber(studentTestDetails.getAttemptNumber());
        existingTest.setScore(studentTestDetails.getScore());
        return null;
    }

    @Override
    public void deleteStudentTest(Integer id) {
        studentTestRepository.deleteById(id);
    }

    @Override
    public StudentTest addQuizToStudentTest(Integer quizId, Integer studentTestId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        StudentTest studentTest = studentTestRepository.findById(studentTestId).orElse(null);
        studentTest.addQuiz(quiz);
        return studentTestRepository.save(studentTest);
    }

    @Override
    public StudentTest addChosenAnswer(Integer ChosenAnswerId, Integer studentTestId, Integer id_question)
    {
        Answer chosenanswer = answerRepository.findById(ChosenAnswerId).orElse(null);
        Question question = questionRepository.findById(id_question).orElse(null);
        StudentTest studentTest = studentTestRepository.findById(studentTestId).orElse(null);
        chosenanswer.setCorrect(false);
        if(question.getAnswers().contains(chosenanswer))
        {
           studentTest.setScore(studentTest.getScore()+1);
            chosenanswer.setCorrect(true);
        }
        if (studentTest.getChosenAnswers() == null) {
            studentTest.setChosenAnswers(new ArrayList<>()) ;
        }
        List ChosenAnswers = studentTest.getChosenAnswers();
        ChosenAnswers.add(chosenanswer);
        studentTest.setChosenAnswers(ChosenAnswers);
        return studentTestRepository.save(studentTest);
    }

    @Override
    public Boolean validateTest(Integer studentTestId){
        StudentTest studentTest = studentTestRepository.findById(studentTestId).orElse(null);
        int totalQuestions = studentTest.getQuiz().getQuestions().size();
        float note = studentTest.getScore() / totalQuestions;
        if (note <= (75/100))
        {
            studentTest.setAttemptNumber(studentTest.getAttemptNumber()+1);
            studentTestRepository.save(studentTest);
            return false ;
        }
        return true;
    }





//    @Override
//    public int calculateScore(Integer StudentTestId) {
//
//        StudentTest studentTest = studentTestRepository.findById(StudentTestId).orElse(null);
//        int totalQuestions = studentTest.getQuiz().getQuestions().size();
//        int correctAnswers = 0;
//
//        if (studentTest.getChosenAnswer() == null || studentTest.getQuiz() == null || studentTest.getQuiz().getQuestions() == null) {
//            throw new IllegalStateException("Cannot calculate score: missing data");
//        }
//
//       /* for (Question question : studentTest.getQuiz().getQuestions()) {
//            if (question.isCorrectAnswer(studentTest.getChosenAnswer())) {
//                correctAnswers++;
//            }
//        }*/
//        for (Question question : studentTest.getQuiz().getQuestions()){
//            if(studentTest.getQuiz().getQuestions().getAnswer().equals(studentTest.getChosenAnswer())){
//                correctAnswers++;
//            }
//        }
//
//
//       /*int correctAnswers = studentTest.getQuiz().getQuestions()
//               .stream()
//               .filter(question -> question.isCorrectAnswer(studentTest.getChosenAnswer()))
//               .collect(Collectors.toList())
//               .size();*/
//
//       studentTest.setScore((int) Math.round((double) correctAnswers / totalQuestions * 100));
//
//       if(studentTest.getScore() <= 75)
//       {
//            studentTest.setAttemptNumber(studentTest.getAttemptNumber() + 1);
//            studentTest.setAttemptTime(new Date());
//            studentTest.setScore(0);
//            studentTestRepository.save(studentTest);
//        }
//        return studentTest.getScore();
//    }

//    public boolean isCorrectAnswer(Answer chosenAnswer) {
//        for (Answer a : answers) {
//            if (a.getId().equals(chosenAnswer.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public int calculateScore(StudentTest studentTest) {
//        return studentTest.calculateScore();
//    }
//
//    @Override
//    public void incrementAttempt(StudentTest studentTest) {
//        studentTest.incrementAttempt();
//        studentTestRepository.save(studentTest);
//    }

//    public int calculateScore2(StudentTest studentTest) {
//        Quiz quiz = studentTest.getQuiz();
//        int totalScore = 0;
//
//        for (Question question : quiz.getQuestions()) {
//            List<Answer> correctAnswers = question.getAnswers()
//                    .stream()
//                    .filter(Answer::isCorrect)
//                    .collect(Collectors.toList());
//
//            List<Answer> chosenAnswers = studentTest.getUser().getRole() == UserRole.STUDENT
//                    ? Collections.singletonList(studentTest.getChosenAnswer())
//                    : studentTest.getChosenAnswers(question);
//
//            if (chosenAnswers.containsAll(correctAnswers) && correctAnswers.containsAll(chosenAnswers)) {
//                totalScore += question.getScore();
//            }
//        }
//
//        return totalScore;
//    }



}



