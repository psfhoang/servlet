package com.model;

import com.dao.CategoryDao;
import com.dao_impl.CategoryDaoImpl;
import com.service.CategoryService;
import com.service.ProductService;
import com.service_impl.CategoryServiceImpl;
import com.service_impl.ProductServiceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyConnection myConnection = new MyConnection();
        try {
            myConnection.connectDB();
            CategoryService categoryService = new CategoryServiceImpl();
            ProductService productService = new ProductServiceImpl();
            Category category1 = new Category();
            category1.setName("hoang");
            Category category =categoryService.insert(category1);
            System.out.println(category);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
