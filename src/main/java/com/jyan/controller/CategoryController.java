package com.jyan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyan.entity.Article;
import com.jyan.entity.Category;
import com.jyan.service.IArticleService;
import com.jyan.service.ICategoryService;
import com.jyan.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IArticleService articleService;
    @GetMapping("/article/category/{bid}/{page}/{limit}")
    public String articlePage(@PathVariable int bid,
                              @PathVariable int page,
                              @PathVariable int limit,
                              Model model){
        if(page<=1){
            page=1;
        }
        Page<Article> articlePage=new Page<>(page,limit);
        articleService.page(articlePage,new QueryWrapper<Article>().eq("categoryId",bid).orderByDesc("createdTime"));
        List<Article> articleList=articlePage.getRecords();
        model.addAttribute("articles",articleList);
        model.addAttribute("articlesPage",articlePage);
        Category category=categoryService.getById(bid);
        model.addAttribute("TCategoryName",category.getCategoryname());
        List<Category> categories = categoryServiceImpl.ListCategory();
        model.addAttribute("categories",categories);
        return "article/list";
    }

}

