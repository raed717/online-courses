package com.example.geekslabo.Controllers.CourseController;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.IServices.ICoursesService.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Answers")
public class AnswerController {
    private final IAnswerService answerService;
    private final IQuestionService questionService;

    public AnswerController(IAnswerService answerService, IQuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping
    public List<Answer> findAll() {
        return answerService.findAllAnswers();
    }

    @GetMapping("/{id}")
    public Answer findById(@PathVariable Integer id) {
        return answerService.findAnswerById(id);
    }

    @PostMapping
    public Answer create(@Valid @RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @PutMapping("/{id}")
    public Answer update(@PathVariable Integer id, @RequestBody Answer answer) {
        return answerService.updateAnswer(id, answer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        answerService.deleteAnswer(id);
    }


}
