package com.example.geekslabo.Repositories.CourseRepo;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Quiz;
import com.example.geekslabo.Entities.StudentTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTestRepository extends JpaRepository<StudentTest, Integer> {
    List<StudentTest> findByUser(AppUser user);

    List<StudentTest> findByQuizAndUser(Quiz quiz, AppUser user);

    List<StudentTest> findByQuiz(Quiz quiz);
}
