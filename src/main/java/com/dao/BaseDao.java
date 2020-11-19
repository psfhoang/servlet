package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T>{
    T getObject(ResultSet resultSet) throws SQLException;
    List<T> getList(ResultSet resultSet) throws SQLException;
    public List<T> findAll() throws SQLException;
    public  T findById(int id) throws SQLException;
    public T insert(T t) throws SQLException;
    public boolean Update(T t) throws SQLException;
    public boolean delete(int id) throws SQLException;
}
