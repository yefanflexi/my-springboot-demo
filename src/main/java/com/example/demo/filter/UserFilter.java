package com.example.demo.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 * @author yefan
 * @date
 */
@WebFilter(filterName = "UserFilter",urlPatterns = "/*")
public class UserFilter implements Filter {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("---------->>> UserFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("---------->>> UserFilter doFilter");
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

        logger.info("---------->>> UserFilter destroy");

    }
}
