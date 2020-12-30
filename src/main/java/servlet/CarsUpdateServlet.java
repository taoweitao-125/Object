package servlet;

import Dao.CarsDao;
import moudle.Cars;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/24
 * @time 14:00
 */
@WebServlet("/carUpdate")
public class CarsUpdateServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        InputStream is = req.getInputStream();
        Cars cars = JSONUtil.deserialize(is,Cars.class);
        int num = CarsDao.update(cars);
        return null;
    }
}
