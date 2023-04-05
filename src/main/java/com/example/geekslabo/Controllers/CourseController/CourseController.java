package com.example.geekslabo.Controllers.CourseController;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.IServices.ICoursesService.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.http.ContentDisposition;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/Courses")
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> findAll() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable Integer id) {
        return courseService.findCourseById(id);
    }

    @GetMapping("/searchCourse/{nom}")
    public Course findCourse(@PathVariable String nom) {
        return courseService.findCourseByName(nom);
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Integer id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<Resource> downloadPdfFile(@PathVariable Integer id) throws IOException {
        byte[] pdfData = courseService.downloadPdfFile(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=course.pdf");
        ByteArrayResource resource = new ByteArrayResource(pdfData);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfData.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /*@GetMapping("/courses/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdfFile(@PathVariable Integer id) throws IOException {
        Course course = courseService.findCourseById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(course.getPdfFileName()).build());
        byte[] pdfBytes = course.getPdfFileData();
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }*/


    @PostMapping("/{id}/pdf")
    public String uploadPdfFile(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        courseService.uploadPdfFile(id, file);
        return "redirect:/courses/{id}";
    }

    @PostMapping("/addLessonToCourse/{id_course}/{id_lesson}")
    public Course addLessonToCourse(@PathVariable Integer id_course, @PathVariable Integer id_lesson)
    {
        Course course = courseService.findCourseById(id_course);

        courseService.addlessontocourse(id_course,id_lesson);

        return course;
    }

    @GetMapping("/course/{id}/qrcode")
    public String generateQRCodeForCourse(@PathVariable Integer id) {
        try {
            courseService.Qrgenerator(id);
            return "QR code generated successfully!";
        } catch ( IOException | com.google.zxing.WriterException e) {
            return "Error generating QR code: " + e.getMessage();
        }
    }




}