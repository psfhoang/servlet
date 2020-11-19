package com.service_impl;

import com.dao.ProductDao;
import com.dao_impl.ProductDaoImpl;
import com.model.Product;
import com.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    public List<Product> sortBy(String field, boolean isAsc) throws SQLException {
        String [] listField = {"create_time","price","guarantee","bouth","promotion","name"};
        boolean check = false;
        for(String s : listField){
            if(s.equals(field)){
                check=true;
                break;
            }
        }
        return check ? productDao.sortBy(field, isAsc) : null;
    }

    public List<Product> findByCategory(int id) throws SQLException {
        return null;
    }

    public List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int category, int bought, int promotion) throws SQLException {
        return null;
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

    public boolean delete(int id) throws SQLException {
        return false;
    }
}
