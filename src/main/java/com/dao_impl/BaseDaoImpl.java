package com.dao_impl;

import com.dao.BaseDao;
import com.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    public List<T> getList(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<T>();
        while (resultSet.next()){
            T t = this.getObject(resultSet);
            if(t!=null){
                list.add(t);
            }
        }
        return list;
    }
}
