package com.example.geekslabo.Services.Activity;


import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.IServices.IServiceActivities.IArticleService;
import com.example.geekslabo.Repositories.ActivityRepo.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@AllArgsConstructor

public class ArticleService implements IArticleService {

    ArticleRepository rep;
    @Override
    public List<Article> GetallArticle() {
        return rep.findAll() ;
    }

    @Override
    public Article AddArticle(Article article) {
        return  rep.save(article);
    }

    @Override
    public void delete(Integer id) {
        rep.deleteById(id);
    }

    @Override
    public Article findArticlebyId(Integer id) {
        return rep.findById(id).orElse(null);
    }

    @Override

    public Article updateArticle(Integer id, Article article) {

        Article oldArticle = rep.findById(id).orElse(null);
        if (oldArticle == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article not found with id " + id);
        }
        oldArticle.setDescription(oldArticle.getDescription());
        return rep.save(oldArticle);
    }

}
