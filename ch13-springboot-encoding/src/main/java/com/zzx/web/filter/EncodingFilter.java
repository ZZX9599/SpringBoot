package com.zzx.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=utf-8");
        System.out.println("执行了字符过滤器");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
