package com.jyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyan.entity.Article;
import com.jyan.mapper.ArticleMapper;
import com.jyan.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    ArticleMapper articleMapper;

    /**
     * 分页查询文章且按时间排序
     * @return
     */
//    public List<Article> articlesOrderByTime(){
//        Page<Article> articlePage=new Page<>(1,10);
//        QueryWrapper<Article> wrapper=new QueryWrapper<>();
//        wrapper.orderByDesc("createdTime");
//        articleMapper.selectPage(articlePage, wrapper);
//        List<Article> articles=articlePage.getRecords();
//        return articles;
//    }
    public Article selectById(int id){
        Article article = articleMapper.selectById(id);
        return article;
    }

    /**
     * 修改文章
     * @param id
     * @param article
     */
    public void updateA(int id,Article article){
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        articleMapper.update(article,wrapper);
    }
    public void deleteAById(int id){
        articleMapper.deleteById(id);
    }
    public int countAByU(Integer userid){
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",userid);
        Integer integer = articleMapper.selectCount(wrapper);
        return integer;
    }
}
