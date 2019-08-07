package com.zyrj.usermanagement.domain;

import java.io.Serializable;

/**
 * @auther ccm
 * @date 10:03
 */
public class Category implements Serializable {
    private Integer categoryId;
    private String categoryType;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryType='" + categoryType + '\'' +
                '}';
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
