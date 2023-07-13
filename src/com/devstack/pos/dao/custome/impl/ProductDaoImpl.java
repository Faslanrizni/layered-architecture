package com.devstack.pos.dao.custome.impl;

import com.devstack.pos.dao.CrudUtil;
import com.devstack.pos.dao.custome.ProductDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
/*    @Override
    public boolean saveProduct(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product VALUES(?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,product.getCode());
        preparedStatement.setString(2,product.getDescription());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE product SET description= ? WHERE code=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, product.getDescription());
        preparedStatement.setInt(2,product.getCode());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean deleteProduct(int code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE product WHERE code=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, code);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public Product findProduct(int code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * product  WHERE code=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new Product(resultSet.getInt(1), resultSet.getString(2));
        }
        return null;
    }

    @Override
    public List<Product> findAllProducts() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM product ";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<Product> productList = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            productList.add(new Product(resultSet.getInt(1), resultSet.getString(2)));
        }
        return productList;
    }*/
/* above code is commented after implementing CrudDao,to prevent boiler plate */
    @Override
    public int getLastProductId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT code FROM product ORDER BY code DESC";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);

        if (resultSet.next()){
            return resultSet.getInt(1)+1;
        }
        return 1;
    }

    @Override
    public Product findProduct(Integer code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO product VALUES(?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,product.getCode());
        preparedStatement.setString(2,product.getDescription());
        return preparedStatement.executeUpdate()>0;*/
        return CrudUtil.execute("INSERT INTO product VALUES(?,?)",product.getCode(),product.getDescription());
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
      /*  String sql = "UPDATE product SET description= ? WHERE code=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, product.getDescription());
        preparedStatement.setInt(2,product.getCode());
        return preparedStatement.executeUpdate()>0;*/
        return CrudUtil.execute("UPDATE product SET description= ? WHERE code=?",product.getDescription(),product.getCode());

    }

    @Override
    public boolean delete(Integer code) throws SQLException, ClassNotFoundException {
      /*  String sql = "DELETE product WHERE code=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, code);
        return preparedStatement.executeUpdate()>0;*/
        return CrudUtil.execute("DELETE product WHERE code=?");
    }

    @Override
    public Product find(Integer code) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * product  WHERE code=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,code);*/
        ResultSet resultSet = CrudUtil.execute("SELECT * product  WHERE code=?",code);
        if (resultSet.next()){
            return new Product(resultSet.getInt(1), resultSet.getString(2));
        }
        return null;
    }


    @Override
    public List<Product> findAll() throws SQLException, ClassNotFoundException {
/*
        String sql = "SELECT * FROM product ";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/

        List<Product> productList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product ");
        while (resultSet.next()){
            productList.add(new Product(resultSet.getInt(1), resultSet.getString(2)));
        }
        return productList;
    }
}
