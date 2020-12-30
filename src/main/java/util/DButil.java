package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author xiaogege
 * @描述： 数据库链接工具类
 * @date 2020/12/14
 * @time 20:05
 */
public class DButil {
//
    private static final DataSource dataSource;
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setDatabaseName("carsdata");
        mysqlDataSource.setCharacterEncoding("utf8");
        dataSource = mysqlDataSource;

    }

    public static Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new AppException("DB01","数据库连接异常",e);
        }
    }

    public static void close(Connection c, Statement s) {
        close(c,s,null);
    }

    public static void close(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null)
                r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (s != null)
                s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (c != null)
                c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
