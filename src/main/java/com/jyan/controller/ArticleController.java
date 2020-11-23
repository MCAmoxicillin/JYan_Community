package com.jyan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyan.entity.Article;
import com.jyan.entity.Category;
import com.jyan.entity.Comment;
import com.jyan.mapper.ArticleMapper;
import com.jyan.result.ArticleForm;
import com.jyan.service.impl.ArticleServiceImpl;
import com.jyan.service.impl.CategoryServiceImpl;
import com.jyan.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    CommentServiceImpl commentService;
    @GetMapping("/article")
    public String article(Model model){
        Page<Article> articlePage=new Page<>(1,10);
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createdTime");
        articleMapper.selectPage(articlePage, wrapper);
        List<Article> articles=articlePage.getRecords();
        model.addAttribute("articles",articles);
        model.addAttribute("articlesPage",articlePage);
        List<Category> categories = categoryService.ListCategory();
        model.addAttribute("categories",categories);
        return "article/list";
    }
    @GetMapping("/article/{current}/{size}")
    public String tarticle(@PathVariable int current,
                           @PathVariable int size,Model model){
        Page<Article> articlePage=new Page<>(current,size);
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createdTime");
        articleMapper.selectPage(articlePage, wrapper);
        List<Article> articles=articlePage.getRecords();
        model.addAttribute("articles",articles);
        model.addAttribute("articlesPage",articlePage);
        List<Category> categories = categoryService.ListCategory();
        model.addAttribute("categories",categories);
        return "article/list";

    }
    @GetMapping("/article/write")
    public String write(Model model){
        List<Category> categories = categoryService.ListCategory();
        model.addAttribute("categoryList",categories);
        return "article/write";
    }
    @PostMapping("/article/write")
    public String saveArticle(ArticleForm articleForm){
        Article article=new Article();
        article.setCategoryid(articleForm.getCategoryId());
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        article.setUserid(articleForm.getUserId());
        article.setUsernetname(articleForm.getUserNetname());
        article.setUserimg(articleForm.getUserImg());
        article.setView(0);
        articleService.save(article);
        return "redirect:/article";
    }

    @GetMapping("/article/read/{id}")
    public String read(@PathVariable int id,
                       Model model){
        Article article = articleService.selectById(id);
        List<Comment> comments = commentService.SelectComByAid(id);
        article.setView(article.getView()+1);
        articleService.updateA(id,article);
        model.addAttribute("article",article);
        model.addAttribute("commentList",comments);
        return "article/read";
    }
    @GetMapping("/article/editor/{uid}/{aid}")
    public String editor(@PathVariable int uid,
                         @PathVariable int aid,
                         Model model){
        Article article = articleService.selectById(aid);
        List<Category> categories = categoryService.ListCategory();
        model.addAttribute("article",article);
        model.addAttribute("categories",categories);
        return "article/editor";
    }
    @PostMapping("/article/editor")
    public String doEditor(Article article){
        Article narticle=articleService.selectById(article.getId());
        narticle.setCategoryid(article.getCategoryid());
        narticle.setTitle(article.getTitle());
        narticle.setContent(article.getContent());
        articleService.updateA(article.getId(),narticle);
        return "redirect:/article/read/"+article.getId();
    }
    @GetMapping("/article/delete/{uid}/{aid}")
    public String delete(@PathVariable int uid,
                         @PathVariable int aid){
        articleService.deleteAById(aid);
        commentService.deleteAC(aid);
        return "redirect:/article";
    }
}

