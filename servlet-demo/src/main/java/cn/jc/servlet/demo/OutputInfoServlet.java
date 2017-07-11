package cn.jc.servlet.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017/5/31.
 */
public class OutputInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=gb2312");

        PrintWriter out = resp.getWriter();

        Enumeration<String> headNames = req.getHeaderNames();

        out.print("<html><head><title>");
        out.print("Welcome Page");
        out.print("</title></head>");
        out.print("<body>");
        out.print("<div><span>接收到的HTTP消息报头的信息</span>");

        while (headNames.hasMoreElements()){
            String name = headNames.nextElement();
            String value = req.getHeader(name);
            out.print("<p>name: " + name);
            out.print("<br>");
            out.print("value: " + value);
            out.print("</p>");
        }

        out.print("</div>");
        out.print("</body></html>");
        out.close();
    }
}
