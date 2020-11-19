package com.dao_impl;

import com.dao.BaseDao;
import com.dao.CategoryDao;
import com.model.Category;
import com.model.MyConnection;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {
    MyConnection myConnection = new MyConnection();
    public Category getObject(ResultSet resultSet) throws SQLException {
        Category category = null;
        category = new Category(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getBoolean("deleted"));


        return category;
    }


//    public List<Category> getList(ResultSet resultSet) throws SQLException {
//        List<Category> categoryList = new ArrayList<Category>();
//        while (resultSet.next()){
//            Category category = this.getObject(resultSet);
//            if(category!=null){
//                categoryList.add(category);
//            }
//        }
//        return categoryList;
//    }

    public List<Category> findAll() throws SQLException {
        String sql = "select * from category";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return this.getList(preparedStatement.executeQuery());
    }

    public Category findById(int id) throws SQLException {
        Category category =null;
        String sql = "select * from category where id=?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
           category = getObject(resultSet);
        }
        return category;
    }

    public Category insert(Category category) throws SQLException {
        Category category1=null;
        String sql = "insert into category(name,deleted) values(?,?)";
        PreparedStatement preparedStatement = myConnection.preparedUpdate(sql);
        preparedStatement.setString(1,category.getName());
        preparedStatement.setBoolean(2,category.isDeleted());
        int rs = preparedStatement.executeUpdate();
        if(rs>0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = (int) resultSet.getLong(1);
                category1 = this.findById(id);
            }
        }
        return category1;
    }

    public boolean Update(Category category) throws SQLException {
        boolean result=false;
        String sql = "update category set name = ? where id=?";
        PreparedStatement preparedStatement = myConnection.preparedUpdate(sql);
        preparedStatement.setString(1,category.getName());
        preparedStatement.setInt(2,category.getId());
        int rs = preparedStatement.executeUpdate();
        if(rs>0) result =true;
        return result;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        String sql = "update category set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.preparedUpdate(sql);
        preparedStatement.setInt(1,id);
        int rs = preparedStatement.executeUpdate();
        if(rs>0){
            result = true;
        }
        return result;
    }
}
