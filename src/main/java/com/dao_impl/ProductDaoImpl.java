package com.dao_impl;

import com.dao.ProductDao;
import com.model.MyConnection;
import com.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    private MyConnection myConnection = new MyConnection();

    public Product getObject(ResultSet resultSet) throws SQLException {
        Product product = null;
        product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getDouble("price"), resultSet.getDate("create_time"),
                resultSet.getBoolean("deleted"), resultSet.getString("image"),
                resultSet.getString("introduction"),
                resultSet.getString("specification"),
                resultSet.getBoolean("sold_out"), resultSet.getInt("guarantee"),
                resultSet.getInt("category_id"), resultSet.getInt("bought"),
                resultSet.getInt("promotion"));
        return product;
    }

    public List<Product> findAll() throws SQLException {
        return null;
    }

    public Product findById(int id) throws SQLException {
        return null;
    }

    public Product insert(Product product) throws SQLException {
        return null;
    }

    public boolean Update(Product product) throws SQLException {
        return false;
    }

    public boolean update(Product product) throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        return false;
    }

    public List<Product> sortBy(String field, boolean isASC) throws SQLException {
        String sql = "select * from product where deleted = false order by " + field + (isASC ? " ASC" : " DESC");
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }


    public List<Product> findByCategory(int idCategory) throws SQLException {

        String sql = "select distinct p.* from product as p, category as c where c.deleted = false and c.id = ? and p.category_id = c.id";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, idCategory);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    public List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int categoryId, int bought, int promotion) throws SQLException {
        String sql = "select p.* from product as p, category as c where p.deleted = false and " +
                "p.name like ? and " +
                "p.create_time >= ? and " +
                "p.create_time <= ? and " +
                "(? is null or p.sold_out = ?) and " +
                "(? = -1 or p.guarantee >= ?) and " +
                "(? = -1 or p.bought >= ?) and " +
                "(? = -1 or p.promotion >= ?) and " +
                "(? > 0 or (c.deleted = false and c.id = ? and p.category_id = c.id))";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1,"%" +name+"%");
        preparedStatement.setString(2, startDate == null ? "0000-01-01" : startDate);
        preparedStatement.setString(3, endDate == null ? "9999-12-31" : endDate);
        if(soldOut == null) {
            preparedStatement.setString(4, null);
            preparedStatement.setBoolean(5, true);
        } else {
            preparedStatement.setString(4, "");
            preparedStatement.setBoolean(5, soldOut);
        }
        preparedStatement.setInt(6, guarantee);
        preparedStatement.setInt(7, guarantee);
        preparedStatement.setInt(8, bought);
        preparedStatement.setInt(9, bought);
        preparedStatement.setInt(10,  promotion);
        preparedStatement.setInt(11, promotion);
        preparedStatement.setInt(12, categoryId);
        preparedStatement.setInt(13, categoryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }
}
