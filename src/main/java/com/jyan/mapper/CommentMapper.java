package com.jyan.mapper;

import com.jyan.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Repository
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    void deleteAC(int id);

}
