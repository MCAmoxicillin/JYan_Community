package com.jyan.controller;


import com.jyan.entity.Comment;
import com.jyan.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Controller
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;

    /**
     * 文章评论功能
     * @param id
     * @param comment
     * @return
     */
    @PostMapping("/article/comment/{id}")
    public String comment(@PathVariable int id, Comment comment){
        comment.setArticleid(id);
        commentService.saveCom(comment);
        return "redirect:/article/read/"+id;
    }

}

