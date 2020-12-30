package servlet;

import exception.AppException;
import moudle.JSONResponse;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiaogege
 * @描述： 基类模板
 *        序列化JSON字符串
 * @date 2020/12/22
 * @time 21:38
 */
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        JSONResponse json = new JSONResponse();
        try {
            Object data = process(req,resp);
            json.setSuccess(true);
            json.setData(data);
        }catch (Exception e) {
            e.printStackTrace();
            String code = "unknown";
            String message = "未知错误";
            if (e instanceof AppException) {
                code = ((AppException) e).getCode();
                message = e.getMessage();
            }
            json.setCode(code);
            json.setMessage(message);
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(json));
        pw.flush();
        pw.close();
    }

    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
