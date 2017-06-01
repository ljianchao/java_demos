package cn.jc.servlet.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/1.
 */
public class PortalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=gb2312");

        PrintWriter out = resp.getWriter();

        out.println("<html><head><title>");
        out.println("登录页面");
        out.println("</title></head><body>");

        String name = req.getParameter("username");
        String password = req.getParameter("password");

        if ("zhangsan".equals(name) && "1234".equals(password)){
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/welcome");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login2");
            rd.include(req, resp);
        }

        out.print("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
