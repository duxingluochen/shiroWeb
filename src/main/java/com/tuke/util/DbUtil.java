package com.tuke.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    /**
     * 获取数据库连接
     *
     */
    public Connection getCon() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection root = DriverManager.getConnection("jdbc:mysql://192.168.1.9:3306/db_shiro", "root", "123456");
        return root;
    }

    /**
     * 关闭连接
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try{
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
