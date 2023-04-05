package com.example.geekslabo.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Course's name cant be blank")
    private String name;
    private String description;
    private String pdfFileName;
    private String pdfFileContentType;
    @Lob
    @JsonIgnore
    private byte[] pdfFileData;


    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private  List<Lesson> Lessons;
    public void addLesson(Lesson lesson) {
        if (Lessons == null) {
            Lessons = new ArrayList<>();
        }
        Lessons.add(lesson);
        lesson.setCourse(this);
    }
}

