package com.zzx.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * 过滤器类
 */
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行了MyFilter");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
