package com.jyan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyan.entity.Article;
import com.jyan.entity.User;
import com.jyan.mapper.ArticleMapper;
import com.jyan.service.IArticleService;
import com.jyan.service.impl.ArticleServiceImpl;
import com.jyan.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JYanCommunityApplicationTests {
    @Autowired
    private  UserServiceImpl userService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    IArticleService articleService;

    @Test
    void contextLoads() {
        User user=userService.selectUserByName("jiangyan");
        System.out.println(user);
    }
    @Test
    public void d(){
        int a=15;
        System.out.println(a+=++a);
        System.out.println();
        System.out.println(a+=a++);
    }
    @Test
    public void pageTest(){
        Page<Article> articlePage=new Page<>(1,10);
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("createdTime");
        articleMapper.selectPage(articlePage,wrapper);
        List<Article> articleList=articlePage.getRecords();
        for (Article article : articleList) {
            System.out.println(article);
        }
        System.out.println(articlePage.getTotal());
//        QueryWrapper<Article> wrapper=new QueryWrapper<>();
//        Page<Article> articlePage=new Page<>(2,10);
//        articleMapper.selectPage(articlePage,wrapper);
//        List<Article> records = articlePage.getRecords();
//        for (Article record : records) {
//            System.out.println(record);
//        }
//        System.out.println(articlePage.getTotal());
//        System.out.println(articlePage.getSize());
    }

}
