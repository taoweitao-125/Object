package servlet;

import Dao.LoginDao;
import exception.AppException;
import moudle.Driver;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/24
 * @time 14:14
 */
@WebServlet("/login")
public class LoginServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Driver driver = LoginDao.query(username);
        if (driver == null) {
            throw new AppException("Log02","账号不存在");
        }
        if (!driver.getPassword().equals(password)) {
            throw new AppException("Log03","密码错误");
        }

        HttpSession session = req.getSession();
        session.setAttribute("driver",driver);
        return null;
    }
}
