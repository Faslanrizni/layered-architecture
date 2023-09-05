package com.devstack.pos.dao.custome.impl;

import com.devstack.pos.dao.CrudUtil;
import com.devstack.pos.dao.custome.UserDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.entity.User;
import com.devstack.pos.util.PasswordManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
   /* @Override
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user VALUES (?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, PasswordManager.encryptPassword(user.getPassword()));

        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, PasswordManager.encryptPassword(user.getPassword()));
        preparedStatement.setString(2, user.getEmail());

        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean deleteUser(String email) throws SQLException, ClassNotFoundException {
        String sql = "DELETE  FROM user  WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public User findUser(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM user WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
    /* applying singleton to prevent creating more than one connection */
    /*
        preparedStatement.setString(1,email);

        ResultSet set = preparedStatement.executeQuery();
        if (set.next() ){
            return  new User(
                    set.getString(1),
                    set.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM user";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
    /* applying singleton to prevent creating more than ine connection *//*
        List<User>userList = new ArrayList<>();
        ResultSet set = preparedStatement.executeQuery();
        while (set.next() ){
           userList.add(new User(
                   set.getString(1),
                   set.getString(2)
                   )

           );
        }
        return userList;
    }*/

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO user VALUES (?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, PasswordManager.encryptPassword(user.getPassword()));

        return preparedStatement.executeUpdate()>0;*/
        return CrudUtil.execute("INSERT INTO user VALUES (?,?)",user.getEmail(),PasswordManager.encryptPassword(user.getPassword()));
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
       /* String sql = "UPDATE user SET password=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, PasswordManager.encryptPassword(user.getPassword()));
        preparedStatement.setString(2, user.getEmail());

        return preparedStatement.executeUpdate()>0;*/
        return CrudUtil.execute("UPDATE user SET password=? WHERE email=?",PasswordManager.encryptPassword(user.getPassword()),user.getEmail());
    }
    @Override
    public boolean delete(String email) throws SQLException, ClassNotFoundException {
        /*        String sql = "DELETE  FROM user  WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);
        return preparedStatement.executeUpdate()>0;*/
        return CrudUtil.execute("DELETE  FROM user  WHERE email=?",email);
    }
    @Override
    public User find(String email) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT  * FROM user WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);*//* applying singleton to prevent creating more than ine connection *//*
        preparedStatement.setString(1,email);
        ResultSet set = preparedStatement.executeQuery();*/
        ResultSet set = CrudUtil.execute("SELECT  * FROM user WHERE email=?",email);
        if (set.next() ){
            return  new User(
                    set.getString(1),
                    set.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT  * FROM user";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);*//* applying singleton to prevent creating more than ine connection */
        List<User>userList = new ArrayList<>();
        ResultSet set = CrudUtil.execute("SELECT  * FROM user");
        while (set.next() ){
            userList.add(new User(
                            set.getString(1),
                            set.getString(2)
                    )
            );
        }
        return userList;
    }
}
