package com.wulei.DAO;

import com.wulei.Entity.Category;

import java.util.List;

public interface CategoryDao {

    //查询所有的Category列表
    public List<Category> getCategoryList();

    //通过categoryId查询对应的Category对象
    public Category getCategoryById(int categoryId);

    //通过categoryName查询对应的Category对象
    public Category getCategoryByName(String categoryName);

}
