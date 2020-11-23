package com.jyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jyan.entity.Comment;
import com.jyan.mapper.CommentMapper;
import com.jyan.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.service.ApiListing;

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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 根据文章id查找评论
     * @param id
     * @return
     */
    public List<Comment> SelectComByAid(int id){
        QueryWrapper<Comment> wrapper=new QueryWrapper<>();
        wrapper.eq("articleId",id).orderByDesc("createdTime");
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments;
    }

    /**
     * 保存评论
     * @param comment
     */
    public void saveCom(Comment comment){
        commentMapper.insert(comment);
    }

    /**
     * 删除评论
     * @param id
     */
    public void deleteAC(int id){
      commentMapper.deleteAC(id);
    }
    public Integer countCByUser(Integer uid){
        QueryWrapper<Comment> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",uid);
        Integer integer = commentMapper.selectCount(wrapper);
        return integer;
    }
    public void deleteCById(int id){
        commentMapper.deleteById(id);
    }


}
