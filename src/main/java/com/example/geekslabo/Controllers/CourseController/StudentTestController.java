package com.example.geekslabo.Controllers.CourseController;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Quiz;
import com.example.geekslabo.Entities.StudentTest;
import com.example.geekslabo.IServices.ICoursesService.IQuizService;
import com.example.geekslabo.IServices.ICoursesService.IStudentTestService;
import com.example.geekslabo.IServices.IServiceAppUser.IAppUserService;
import com.example.geekslabo.IServices.IServiceAppUser.IServiceCRUDAppUser;
import com.example.geekslabo.Repositories.CourseRepo.QuizRepository;
import com.example.geekslabo.Repositories.CourseRepo.StudentTestRepository;
import com.example.geekslabo.Repositories.UserRepo.AppUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-tests")
public class StudentTestController {


    private final IStudentTestService studentTestService;
    private final StudentTestRepository studentTestRepository;
    private final IQuizService QuizService;
    private final QuizRepository quizRepository;


    public StudentTestController(IStudentTestService studentTestService, StudentTestRepository studentTestRepository, IQuizService iQuizService, QuizRepository quizRepository) {
        this.studentTestService = studentTestService;
        this.studentTestRepository = studentTestRepository;
        this.QuizService = iQuizService;
        this.quizRepository = quizRepository;
    }
    @GetMapping
    public List<StudentTest> findAll() {
        return studentTestService.getAll();
    }

    @GetMapping("/{id}")
    public StudentTest findById(@PathVariable Integer id) {
        return studentTestService.getById(id);
    }

//    @GetMapping("findbyquizanduser/{idquiz}/{iduser}")
//    public StudentTest findbyquizanduser(@PathVariable Integer idquiz , @PathVariable Integer iduser) {
//        Quiz quiz = quizRepository.findById(idquiz).orElse(null);
//
//        return (StudentTest) studentTestService.getByQuizAndUser(quiz, user);
//    }

    @PostMapping
    public StudentTest create(@RequestBody StudentTest studentTest) {
        return studentTestService.save(studentTest);
    }

    @PutMapping("/{id}")
    public StudentTest update(@PathVariable Integer id, @RequestBody StudentTest studentTest) {
        return studentTestService.updateTestStudent(id, studentTest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentTestService.deleteStudentTest(id);
    }

    @PostMapping("addQuizToStudentTest/{QuizId}/{StudentTestId}")
    public StudentTest addQuizToStudentTest(@PathVariable Integer QuizId, @PathVariable Integer StudentTestId){
        studentTestService.addQuizToStudentTest(QuizId,StudentTestId);
        return studentTestService.getById(StudentTestId);
    }

    @PostMapping("addChosenAnswerToStudentTest/{ChosenAnswerId}/{StudentTestId}/{id_question}")
    public StudentTest addChosenAnswerToStudentTest(@PathVariable Integer ChosenAnswerId, @PathVariable Integer StudentTestId, @PathVariable Integer id_question){
        studentTestService.addChosenAnswer(ChosenAnswerId,StudentTestId,id_question);
        return studentTestService.getById(StudentTestId);
    }


    @PostMapping("/{id}")
    public boolean validatetest(@PathVariable Integer id) {

        return studentTestService.validateTest(id);
    }




}
