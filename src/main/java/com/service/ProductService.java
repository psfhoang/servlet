package com.service;

import com.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends BaseService<Product>{
    List<Product> sortBy(String field, boolean isAsc) throws SQLException;
    List<Product> findByCategory(int id) throws SQLException;
    List<Product> search(String name,String startDate,String endDate,Boolean soldOut,int guarantee,
                         int category,int bought,int promotion) throws SQLException;

}
