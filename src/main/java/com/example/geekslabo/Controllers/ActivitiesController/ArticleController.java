package com.example.geekslabo.Controllers.ActivitiesController;


import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.IServices.IServiceActivities.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Articles")
public class ArticleController {

    IArticleService serv;
    @GetMapping("/list-article")
    public List<Article> GetallArticle(){
        return serv.GetallArticle();
    }

    @PostMapping("/add-article")
    public Article AddArticle(@RequestBody Article article) {
       return serv.AddArticle(article);
    }




}
