package cn.jc.servlet.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/31.
 */
public class WelcomeServlet extends HttpServlet {

    private static final String ENCODING_GB2312 = "gb2312";

    private String greeting;

    public void init(){
        greeting = getInitParameter("greeting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING_GB2312);
        String username = req.getParameter("username");
        String welcomeInfo = greeting + ", " + username;
        resp.setContentType("text/html");

        resp.setCharacterEncoding(ENCODING_GB2312);
        PrintWriter out = resp.getWriter();

        out.print("<html><head><title>");
        out.print("Welcome Page");
        out.print("</title></head>");
        out.print("<body>");
        out.print(welcomeInfo);
        out.print("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
