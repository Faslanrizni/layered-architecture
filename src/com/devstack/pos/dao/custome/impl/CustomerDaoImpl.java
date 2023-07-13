package com.devstack.pos.dao.custome.impl;

import com.devstack.pos.dao.CrudUtil;
import com.devstack.pos.dao.custome.CustomerDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
/*    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getEmail());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getContact());
        preparedStatement.setDouble(4, customer.getSalary());


        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name=?, contact=?, salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getContact());
        preparedStatement.setDouble(3, customer.getSalary());
        preparedStatement.setString(4,customer.getEmail());

        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);

        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public Customer findCustomer(String email) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name=?,contact=?,salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);


        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()){
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return customerList;
    }*/
/* above code is commented after implementing CrudDao,to prevent boiler plate */
    @Override
    public List<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        searchText= "%"+searchText+"%";
    /*    String sql = "SELECT * FROM customer WHERE  email LIKE ? || name LIKE? ";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,searchText);
        preparedStatement.setString(2,searchText);*/
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE  email LIKE ? || name LIKE?",searchText,searchText)/* preparedStatement.executeQuery()*/;

        List<Customer> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return dtos;
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
/*        String sql = "INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getEmail());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getContact());
        preparedStatement.setDouble(4, customer.getSalary());*/
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?)", /*preparedStatement.executeUpdate()>0*/
                customer.getEmail(), customer.getName(),customer.getContact(),customer.getSalary());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
/*
        String sql = "UPDATE customer SET name=?, contact=?, salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getContact());
        preparedStatement.setDouble(3, customer.getSalary());
        preparedStatement.setString(4,customer.getEmail());*/
        return CrudUtil.execute("UPDATE INTO customer VALUES (?,?,?,?)", /*preparedStatement.executeUpdate()>0*/
                 customer.getName(),customer.getContact(),customer.getSalary(),customer.getEmail());
    }

    @Override
    public boolean delete(String email) throws SQLException, ClassNotFoundException {
      /*  String sql = ;*//*"DELETE FROM customer WHERE email=?"*//*
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);*/

        return /*preparedStatement.executeUpdate()>0;*/ CrudUtil.execute("DELETE FROM customer WHERE email=?");
    }

    @Override
    public Customer find(String email ) throws SQLException, ClassNotFoundException {
/*        String sql = "SELECT * FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);*/
        ResultSet resultSet = /*preparedStatement.executeQuery()*/ CrudUtil.execute("SELECT * FROM customer WHERE email=?",email);
        if (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
        ResultSet resultSet = /*preparedStatement.executeQuery()*/ CrudUtil.execute("SELECT * FROM customer");

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()){
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return customerList;
    }
}
