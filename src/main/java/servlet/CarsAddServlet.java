package servlet;

import Dao.CarsDao;
import moudle.Cars;
import moudle.Driver;
import util.JSONUtil;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

/**
 * @author xiaogege
 * @描述： 增加汽车信息
 * @date 2020/12/22
 * @time 22:05
 */
@WebServlet("/carAdd")
public class CarsAddServlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception{
//      通过session获取司机id
        HttpSession session =req.getSession(false);
        Driver driver = (Driver) session.getAttribute("driver");
        InputStream is = req.getInputStream();

        Cars cars = JSONUtil.deserialize(is, Cars.class);
        cars.setDriverId(driver.getId());
        int name = CarsDao.insert(cars);
        return null;
    }
}
