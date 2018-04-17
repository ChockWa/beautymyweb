package com.beauty.myweb.common.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "CORSFilter")
public class CORSFilter implements Filter {

    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
//        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        servletResponse.setHeader("Access-Control-Max-Age", "3600");
//        servletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        servletResponse.setHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(request, response);
        System.out.println("CORSFilter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}

