package com.example.demo.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class MysqlJdbcExample1 {

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        String sql = "select * from girl";

        String url = "jdbc:mysql://127.0.0.1:3306/dbgirl?user=root&password=&useUnicode=true&characterEncoding=UTF-8";

        try {
            //加载驱动
            new com.mysql.jdbc.Driver();
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接
            connection = DriverManager.getConnection(url);

            //执行sql命令
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                log.info("id:{}, age:{}, cup_Size:{}, money:{}", resultSet.getString("id"),
                        resultSet.getString("age"), resultSet.getString("cup_Size"),
                        resultSet.getString("money"));
            }
        } catch (SQLException err) {
            log.error("sqlexception:{}", err);
        } catch (Exception err) {
            log.error("exception:{}", err);
        } finally {
            //释放sql连接
            if(connection != null) connection.close();
        }
    }
    
}
