package com.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T> {
    public List<T> findAll() throws SQLException;
    public  T findById(int id) throws SQLException;
    public T insert(T t) throws SQLException;
    public boolean Update(T t) throws SQLException;
    public boolean delete(int id) throws SQLException;

}
