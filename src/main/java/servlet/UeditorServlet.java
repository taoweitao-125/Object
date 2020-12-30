package servlet;



import util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @author xiaogege
 * @描述： 上传图片
 * @date 2020/12/5
 * @time 9:28
 */
@WebServlet("/ueditor")
public class UeditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       URL url =  servlet.UeditorServlet.class.getClassLoader().
               getResource("config.json");//绝对路径
        String path = URLDecoder.decode(url.getPath(),"UTF-8");
        MyActionEnter enter = new MyActionEnter(req,path);
        String exec = enter.exec();
        PrintWriter pw = resp.getWriter();
        pw.write(exec);
        pw.flush();
        pw.close();



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
