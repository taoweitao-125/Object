package servlet;

import Dao.CarsDao;
import exception.AppException;
import moudle.Cars;
import moudle.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/24
 * @time 14:07
 */
@WebServlet("/carList")
public class CarsListServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession(false);
        if (session == null) {
            throw new AppException("Cars02","用户未登录");
        }
        Driver driver = (Driver)session.getAttribute("driver");
        if (driver == null) {
            throw new AppException("Cars03","用户未登录");
        }
        List<Cars> cars = CarsDao.queryByDriverId(driver.getId());
        return cars;
    }
}
