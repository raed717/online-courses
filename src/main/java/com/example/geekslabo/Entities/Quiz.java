package com.example.geekslabo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.*;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private Course course;
    @JsonIgnoreProperties("quiz")
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    public void addQuestion(Question question) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(question);
        question.setQuiz(this);
    }

    public static ByteArrayInputStream export(Quiz quiz) {
           Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD, new BaseColor(250,0,0));
           Font questionFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
           Font logoFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add logo
            URL url = new URL("http://www.chucharlesnicolle.tn/wp-content/uploads/2020/06/logo-charles2.png");
            Image logo = Image.getInstance(url);
            logo.setAlignment(Element.ALIGN_LEFT);
            logo.scaleAbsolute(100, 100);
            document.add(logo);

            // Add title
            Paragraph quizTitle = new Paragraph(quiz.getName(), titleFont);
            quizTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(quizTitle);

            // Add questions
            List<Question> questions = quiz.getQuestions();
            int i=0;
            for (Question question : questions) {
                i++;
                Paragraph seperator = new Paragraph("------------------------", questionFont);
                Paragraph questionText = new Paragraph(question.getDescription(), questionFont);
                questionText.setIndentationLeft(50);
                document.add(questionText);
                document.add(seperator);
            }

            // Add logo text
            Paragraph logoText = new Paragraph("Powered by Geeks_Lab \n Good Luck", logoFont);
            logoText.setAlignment(Element.ALIGN_CENTER);
            document.add(logoText);

            // Add frame
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle frame = new Rectangle(36, 36, 559, 806);
            frame.setBorder(Rectangle.BOX);
            frame.setBorderWidth(1);
            canvas.rectangle(frame);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}