package com.tuke.dao;

import com.tuke.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class UserDao {

    /**
     * 获取用户信息
     * @param con
     * @param userName
     * @return
     * @throws Exception
     */
    public User getByUserName(Connection con, String userName)throws Exception{
        User resultUser=null;
        String sql ="select * from t_user where userName=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, userName);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            resultUser = new User();
            resultUser.setId(resultSet.getInt("id"));
            resultUser.setUserName(resultSet.getString("userName"));
            resultUser.setPassword(resultSet.getString("password"));
        }
        return resultUser;
    }

    /**
     * 获取权限
     * @param con
     * @param userName
     * @return
     */
    public Set<String> getPermissions(Connection con, String userName) throws Exception{
        Set<String> permiss = new HashSet<String>();
        String sql = "select * from t_user u,t_role r , t_permission p where  u.roleId=r.id and p.roleId=r.id and u.userName=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, userName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            permiss.add(resultSet.getString("permissionName"));
        }
        return permiss;
    }

    /**
     * 查询角色
     * @param con
     * @param userName
     * @return
     */
    public Set<String> getRoles(Connection con, String userName) throws Exception{
        Set<String> roles = new HashSet<String>();
        String sql = "select * from t_user u , t_role r where u.roleId=r.id and u.userName=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            roles.add(resultSet.getString("roleName"));
        }
        return roles;
    }
}
