package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.CategoryMapper;
import com.zyrj.usermanagement.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ccm
 * @date 15:11
 */
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> findCategory() {
        return categoryMapper.findCategory();
    }

}
