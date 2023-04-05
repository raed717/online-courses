package com.example.geekslabo.Controllers.CourseController;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.IServices.ICoursesService.*;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Quizzes")
public class QuizController {

    private final IQuizService quizService;

    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> findAll() {
        return quizService.findAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz findById(@PathVariable Integer id) {
        return quizService.findQuizById(id);
    }

    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @PutMapping("/{id}")
    public Quiz update(@PathVariable Integer id, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
    }


    @PostMapping("addQuestionToQuiz/{quizId}/{questionId}")
    public Quiz addQuestionToQuiz( @PathVariable Integer quizId, @PathVariable Integer questionId){
        quizService.addQuestionToQuiz(quizId,questionId);
        return quizService.findQuizById(quizId);
    }


    @GetMapping(value = "/export/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportQuizToPdf(@PathVariable Integer id) throws IOException {

        Quiz quiz = quizService.findQuizById(id);


        ByteArrayInputStream bis = quiz.export(quiz);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=quiz.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}