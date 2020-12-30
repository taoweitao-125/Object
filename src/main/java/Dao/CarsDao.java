package Dao;

import exception.AppException;
import moudle.Cars;
import util.DButil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/22
 * @time 22:28
 */
public class CarsDao {
    public static List<Cars> queryByDriverId(int driverId){
        List<Cars> carsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DButil.getConnection();
            String sql = "select id, driver_id, num, color, type, capacity, message from cars where driver_id = ?";
            ps =c.prepareStatement(sql);
            ps.setInt(1,driverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cars car = new Cars();
                car.setId(rs.getInt("id"));
                car.setDriverId(rs.getInt("driver_id"));
                car.setNum(rs.getString("num"));
                car.setColor(rs.getString("color"));
                car.setType(rs.getString("type"));
                car.setCapacity(rs.getString("capacity"));
                car.setMessage(rs.getString("message"));
                carsList.add(car);
            }
            return carsList;
        }catch (Exception e) {
            throw new AppException("Cars01","查询车辆信息序列化失败",e);
        }finally {
            DButil.close(c,ps,rs);
        }

    }

    public static int delete (String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DButil.getConnection();
            StringBuilder sql = new StringBuilder("delete from cars where id in (");
            for (int i = 0; i < ids.length; i++) {
                if (i != 0) {
                    sql.append(",");
                }else {
                    sql.append("?");
                }
            }
            sql.append(")");
            ps = c.prepareStatement(sql.toString());

            for (int i = 0; i < ids.length; i++) {
                ps.setInt(i+1,Integer.parseInt(ids[i]));
            }
            return ps.executeUpdate();
        }catch (Exception e) {
            throw new AppException("Cars04","删除失败",e);
        }finally {
            DButil.close(c,ps);
        }
    }

    public static int insert(Cars cars) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DButil.getConnection();
            String sql = "insert into cars (driver_id,num,color,type,capacity,message) values (?,?,?,?,?,?)" ;
            ps = c.prepareStatement(sql);
            ps.setInt(1,cars.getId());
            ps.setString(2,cars.getNum());
            ps.setString(3,cars.getColor());
            ps.setString(4,cars.getType());
            ps.setString(5,cars.getCapacity());
            ps.setString(6,cars.getMessage());
            return ps.executeUpdate();
        }catch (Exception e) {
            throw new AppException("Cars05","插入车辆信息出错",e);
        }finally {
            DButil.close(c,ps);
        }
    }

    public static Cars query(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DButil.getConnection();
            String sql = "select driver_id, num, color, type, capacity, message from cars where id = ?";
            ps = c.prepareStatement(sql);
//            设置占位符，替换值
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Cars cars = null;

//            处理结果集
            while (rs.next()) {
                cars = new Cars();
                cars.setId(id);
                cars.setDriverId(rs.getInt("driver_id"));
                cars.setNum(rs.getString("num"));
                cars.setColor(rs.getString("color"));
                cars.setType(rs.getString("type"));
                cars.setCapacity(rs.getString("capacity"));
                cars.setMessage(rs.getString("message"));
            }
            return  cars;
        }catch (Exception e) {
            throw new AppException("ART006","信息查询出错",e);
        }finally {
            DButil.close(c,ps,rs);
        }
    }


    public static int update(Cars cars) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DButil.getConnection();
            String sql = "update cars set num=?, color=?, type=?, capacity=?, message=? , driver_id=? where id=?" ;
            ps = c.prepareStatement(sql);
            ps.setString(1,cars.getNum());
            ps.setString(2,cars.getColor());
            ps.setString(3,cars.getType());
            ps.setString(4,cars.getCapacity());
            ps.setString(5,cars.getMessage());
            ps.setInt(6,cars.getDriverId());
            ps.setInt(7,cars.getId());
            return ps.executeUpdate();
        }catch (Exception e) {
            throw new AppException("ART007","修改信息出错",e);
        }finally {
            DButil.close(c,ps);
        }
    }


}
