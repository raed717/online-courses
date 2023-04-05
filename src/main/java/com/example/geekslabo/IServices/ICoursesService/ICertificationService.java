package com.example.geekslabo.IServices.ICoursesService;

import com.example.geekslabo.Entities.Certification;
import org.webjars.NotFoundException;

import java.util.List;

public interface ICertificationService {
    List<Certification> getAllCertifications();
    Certification getCertificationById(Integer id) throws NotFoundException;
    Certification createCertification(Certification certification);
    Certification updateCertification(Integer id, Certification certificationDetails) throws NotFoundException;
    void deleteCertification(Integer id) throws NotFoundException;
    Certification addCourseToCertification(Integer certificationId, Integer courseId);
}

