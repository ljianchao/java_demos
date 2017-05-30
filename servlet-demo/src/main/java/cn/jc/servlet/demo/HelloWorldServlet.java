package cn.jc.servlet.demo;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/30.
 */
public class HelloWorldServlet implements Servlet{

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println("hello world");
        out.close();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
