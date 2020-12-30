package servlet;


import Dao.CarsDao;
import moudle.Cars;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/24
 * @time 13:57
 */
@WebServlet("/carDetail")
public class CarsDetailServlet extends AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        Cars cars = CarsDao.query(Integer.parseInt(id));
        return cars;
    }
}
