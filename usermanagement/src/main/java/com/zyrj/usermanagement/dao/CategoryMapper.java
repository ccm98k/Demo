package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther ccm
 * @date 10:30
 */
@Mapper
@Repository
public interface CategoryMapper {


    List<Category> findCategory();
}
