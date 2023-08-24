package com.devstack.pos.dao;

import com.devstack.pos.dao.custome.CustomerDao;
import com.devstack.pos.dao.custome.ProductDao;
import com.devstack.pos.dao.custome.UserDao;
import com.devstack.pos.dao.custome.impl.CustomerDaoImpl;
import com.devstack.pos.dao.custome.impl.ProductDaoImpl;
import com.devstack.pos.dao.custome.impl.UserDaoImpl;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.entity.Product;
import com.devstack.pos.entity.User;
import com.devstack.pos.enums.DaoType;
import com.devstack.pos.util.PasswordManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCode {

    CustomerDao customerDao = /*(CustomerDao)*/DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    ProductDao productDao = /*(ProductDao)*/ DaoFactory.getInstance().getDao(DaoType.PRODUCT);
    UserDao userDao = /*(UserDao)*/DaoFactory.getInstance().getDao(DaoType.USER);

    /*we  have apply generics for factory method and it automatically have applied for above 3 code snipts
    * and now we dont need to cast thats why we commented the casting part
    * we called this type infarance  */


    /*-----------user management------------*/
    public  boolean createUser(String email,String password) throws ClassNotFoundException, SQLException {
        return userDao.save(
                new User(email,password)
        );
/*
        String sql = "INSERT INTO user VALUES (?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2, PasswordManager.encryptPassword(password));

        return preparedStatement.executeUpdate()>0;*/

    }

    public  UserDto findUser(String email) throws ClassNotFoundException, SQLException {
        User user = userDao.find(email);
        if (user != null){
            return  new UserDto(
                    user.getEmail(),
                    user.getPassword()
            );
        }
        return null;

      /* String sql = "SELECT  * FROM user WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);*//* applying singleton to prevent creating more than ine connection *//*
        preparedStatement.setString(1,email);
*/

    }

    /*-----------user management------------*/

    /* to maintaince the single the single responsibility we add the user managment   */
    /* ---------customer management -------------*/
    public  boolean createCustomer(String email,String name,String contact,double salary) throws ClassNotFoundException, SQLException {
        return customerDao.save(
                new Customer(email,name,contact,salary)
        );
     /*   String sql = "INSERT INTO customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, contact);
        preparedStatement.setDouble(4, salary);


        return preparedStatement.executeUpdate()>0;*/
    }
    public  boolean updateCustomer(String email,String name,String contact,double salary) throws ClassNotFoundException, SQLException {
        return customerDao.update(
                new Customer(email,name,contact,salary)
        );

/*        String sql = "UPDATE customer SET name=?, contact=?, salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, contact);
        preparedStatement.setDouble(3, salary);
        preparedStatement.setString(4,email);

        return preparedStatement.executeUpdate()>0;*/
    }
    public  CustomerDto findCustomer(String email) throws SQLException, ClassNotFoundException {
  /*      String sql = "UPDATE customer SET name=?,contact=?,salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);


        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }*/
        Customer customer = customerDao.find(email);
        if (customer != null){
            return new CustomerDto(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            );
        }
        return null;
    }
    public  boolean  deleteCustomer(String email) throws ClassNotFoundException, SQLException {
        return customerDao.delete(email);
  /*     String sql = "DELETE FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);

        return preparedStatement.executeUpdate()>0;*/
    }
    public  List<CustomerDto> findAllCustomer() throws ClassNotFoundException, SQLException {
/*
      String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
*/

        List<Customer> customerList = customerDao.findAll();
        List <CustomerDto> dtos = new ArrayList<>();

        for (Customer c:customerDao.findAll()
             ) {
            dtos.add(new CustomerDto(
                    c.getEmail(),
                    c.getName(),
                    c.getContact(),
                    c.getSalary()
            ));
        }
        /*while (resultSet.next()){
            dtos.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }*/
        return dtos;
    }
    public  List<CustomerDto> searchCustomer(String searchText) throws ClassNotFoundException, SQLException {

        searchText= "%"+searchText+"%";
/*    String sql = "SELECT * FROM customer WHERE  email LIKE ? || name LIKE? ";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
       preparedStatement.setString(1,searchText);
       preparedStatement.setString(2,searchText);
        ResultSet resultSet = preparedStatement.executeQuery();*/

        List<CustomerDto> dtos = new ArrayList<>();
        for (Customer c:customerDao.searchCustomer(searchText)
        ) {
            dtos.add(new CustomerDto(
                    c.getEmail(),
                    c.getName(),
                    c.getContact(),
                    c.getSalary()
            ));
        }
        /*while (resultSet.next()){
            dtos.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }*/
        return dtos;
    }
    /* ---------customer management -------------*/

    /* ---------Product  management -------------*/

    public  int getLastProductId() throws SQLException, ClassNotFoundException {
       /*String sql = "SELECT code FROM product ORDER BY code DESC";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);

        if (resultSet.next()){
            return resultSet.getInt(1)+1;
        }*/
        return productDao.getLastProductId();
    }
    public boolean saveProduct(int code,String description) throws SQLException, ClassNotFoundException {

/*       String sql = "INSERT INTO product VALUES(?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,code);
        preparedStatement.setString(2,description);
        return preparedStatement.executeUpdate()>0;*/
        return productDao.save(
                new Product(code,description)
        );



    }


    /*public static List<ProductDto> searchProduct(String searchText) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * Product WHERE code LIKE ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,searchText);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<ProductDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(new ProductDto(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
        return dtos;
    }*/

/*    public static boolean updateProduct(int code, String description) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE product set code = ?,description= ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        return preparedStatement.executeUpdate()>0;

        preparedStatement.setString(2,description);
    }*/

    /* ---------Product management -------------*/
}
/* now we are adapt to cohesion and single responsibility*/






