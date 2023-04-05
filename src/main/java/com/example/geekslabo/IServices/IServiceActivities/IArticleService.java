package com.example.geekslabo.IServices.IServiceActivities;


import com.example.geekslabo.Entities.Article;

import java.util.List;

public interface IArticleService {


    List<Article> GetallArticle();
    public Article AddArticle(Article article);
    public Article findArticlebyId(Integer id) ;
    public Article updateArticle(Integer id, Article article);
    void delete(Integer id);
}
