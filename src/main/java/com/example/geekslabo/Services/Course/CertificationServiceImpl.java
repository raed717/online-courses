package com.example.geekslabo.Services.Course;

import com.example.geekslabo.Entities.Certification;
import com.example.geekslabo.Entities.Course;
import com.example.geekslabo.IServices.ICoursesService.ICertificationService;
import com.example.geekslabo.Repositories.CourseRepo.CertificationRepository;
import com.example.geekslabo.Repositories.CourseRepo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CertificationServiceImpl implements ICertificationService {

    private final CertificationRepository certificationRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CertificationServiceImpl(CertificationRepository certificationRepository, CourseRepository courseRepository) {
        this.certificationRepository = certificationRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Certification createCertification(Certification certification) {
        return certificationRepository.save(certification);
    }

    @Override
    public Certification updateCertification(Integer id, Certification certificationDetails) throws NotFoundException {
        return null;
    }

    @Override
    public Certification getCertificationById(Integer id) {
        return certificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Certification not found with id " + id));
    }


    @Override
    public void deleteCertification(Integer id) {
        if (!certificationRepository.existsById(id)) {
            throw new NotFoundException("Certification not found with id " + id);
        }
        certificationRepository.deleteById(id);
    }

    @Override
    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    @Override
    public Certification addCourseToCertification(Integer certificationId, Integer courseId) {
        Certification certification = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new NotFoundException("Certification not found with id " + certificationId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with id " + courseId));

        certification.setCourse(course);
        return certificationRepository.save(certification);
    }
}

