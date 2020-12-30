package servlet;

import Dao.CarsDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/24
 * @time 13:52
 */
@WebServlet("/carDelete")
public class CarsDeleteServlet extends AbstractBaseServlet {
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String ids = req.getParameter("ids");
        int num = CarsDao.delete(ids.split(","));
        return null;
    }

}
