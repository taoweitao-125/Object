package filter;

import moudle.Driver;
import moudle.JSONResponse;
import util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;




@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //访问/jsp/... 如果未登录，跳转到/view/login.html;
        //访问/login,其他前端资源，不校验session (登录，注册，注销)
        //其他后端资源，校验session，未登录就返回401状态码，json字符串
        //                        登录通过就执行调用doFilter
        String path = req.getServletPath();
        if (path.startsWith("/js/") || path.startsWith("/static/")
                || path.startsWith("/view/") || path.equals("/login")) {
            chain.doFilter(request,response);
        }else {
            HttpSession session = req.getSession(false);
            if (session == null ) {
                //跳转或返回json
                unauthorized(req,resp);
            }else {
                Driver driver = (Driver) session.getAttribute("driver");
                if (driver == null) {
                    //跳转或返回json
                    unauthorized(req,resp);
                }else {
                    chain.doFilter(request,response);
                }
            }
        }
    }

    public static void unauthorized(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String schema = req.getScheme();//获取协议 HTTP
        String host = req.getServerName();//主机ip
        int port = req.getServerPort();//主机端口号
        String contextPath = req.getContextPath();//应用上下文路径
        String path = req.getServletPath();//Servlet路径
        resp.setCharacterEncoding("UTF-8");
        if (path.startsWith("/jsp/")) {//前段敏感资源，跳转登录页面(重定向)
            resp.setContentType("text/html");
            String basePath = schema + "://" +host+ ":" + port + contextPath;
            resp.sendRedirect(basePath +"/view/login.html");
        }else {//后端敏感资源：状态码401 ，返回json
            resp.setContentType("application/json");
            resp.setStatus(401);
            JSONResponse json = new JSONResponse();
            json.setCode("LOG000");
            json.setMessage("用户没有登录，不允许访问");
            PrintWriter pw = resp.getWriter();
            pw.println(JSONUtil.serialize(json));
            pw.flush();
            pw.close();

        }
    }

    @Override
    public void destroy() {

    }
}
