package cn.jc.servlet.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/1.
 */
public class LoginServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=gb2312");

        PrintWriter out = resp.getWriter();

        out.println("<form method= post action=portal>");

        out.println("请输入用户名: ");
        out.println("<input type=text name=username>");
        out.println("密码：");
        out.println("<input type=password name=password>");
        out.println("<input type=submit value=登录>");
        out.println("</form>");

        // 不可以使用out.close()关闭输出流对象，因为一旦关闭，响应将被提交
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
