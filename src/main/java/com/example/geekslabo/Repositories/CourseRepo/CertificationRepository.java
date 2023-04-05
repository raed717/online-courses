package com.example.geekslabo.Repositories.CourseRepo;

import com.example.geekslabo.Entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    Optional<Certification> findByStudentIdAndCourseId(Integer studentId, Integer courseId);
}

