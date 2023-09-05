package com.devstack.pos.dao.custome;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends CrudDao<Product, Integer> {
    /* public boolean saveProduct(Product product) throws SQLException, ClassNotFoundException;
    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException;
    public boolean deleteProduct(int code) throws SQLException, ClassNotFoundException;
    public Product findProduct(int code) throws SQLException, ClassNotFoundException;

    public List<Product> findAllProducts() throws SQLException, ClassNotFoundException;*/

    /*in all dao interfaces are boiler plated we need to correct it*/

    /*========================*/
    public  int getLastProductId() throws SQLException, ClassNotFoundException;

    Product findProduct(Integer code) throws SQLException, ClassNotFoundException;
}
