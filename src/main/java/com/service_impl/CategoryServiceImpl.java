package com.service_impl;

import com.dao.CategoryDao;
import com.dao_impl.CategoryDaoImpl;
import com.model.Category;
import com.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    public List<Category> findAll() throws SQLException {
        return categoryDao.findAll();
    }

    public Category findById(int id) throws SQLException {

        return id > 0 ? categoryDao.findById(id) : null;
    }

    public Category insert(Category category) throws SQLException {
        return categoryDao.insert(category);
    }

    public boolean Update(Category category) throws SQLException {
        return category.getId()>0?categoryDao.Update(category):false;
    }

    public boolean delete(int id) throws SQLException {
        return id>0?categoryDao.delete(id):false;
    }
}
