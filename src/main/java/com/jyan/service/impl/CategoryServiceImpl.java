package com.jyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jyan.entity.Category;
import com.jyan.mapper.CategoryMapper;
import com.jyan.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询所有分类标签
     * @return
     */
    public List<Category> ListCategory(){
        List<Category> categories = categoryMapper.selectList(null);
        return categories;
    }

}
