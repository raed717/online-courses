package com.example.geekslabo.Entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class StudentTest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Quiz quiz;
    @ManyToOne
    private AppUser user;
    @ManyToMany
    private List<Answer> chosenAnswers;
    @Temporal(TemporalType.TIMESTAMP)
    private Date attemptTime;
    private int attemptNumber;
    private int score;

    public StudentTest() {
        this.attemptTime = new Date();
        this.attemptNumber = 0;
        this.score = 0;
    }

    public void addQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


}
