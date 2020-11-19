package com.filter;


import com.model.MyConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AllFilter", urlPatterns = "/*")
public class AllFilter implements Filter {
    MyConnection myConnection = new MyConnection();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(req, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
