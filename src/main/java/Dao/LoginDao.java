package Dao;

import exception.AppException;
import moudle.Driver;
import util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/24
 * @time 14:17
 */
public class LoginDao {
    public static Driver query(String username) {
        Connection c = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        try{
            c = DButil.getConnection();
            String sql = "select id, username, password, name, age, sex, id_card, car_num from driver" +
                    "    where username = ?";
            ps = c.prepareStatement(sql);
//            设置占位符的值 TODO

            ps.setString(1,username);

            rs = ps.executeQuery();
//            处理结果集
            Driver driver = null;
            while (rs.next()) {
                driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setUsername(username);
                driver.setPassword(rs.getString("password"));
                driver.setName(rs.getString("name"));
                driver.setAge(rs.getInt("age"));
                driver.setSex(rs.getString("sex"));
                driver.setIdCard(rs.getString("id_card"));
                driver.setCarNum(rs.getString("car_num"));
            }
            return driver;
        }catch (Exception e) {
            throw new AppException("LOG001","登录操作数据库执行错误",e);
        }finally {
            DButil.close(c,ps,rs);
        }
    }
}
