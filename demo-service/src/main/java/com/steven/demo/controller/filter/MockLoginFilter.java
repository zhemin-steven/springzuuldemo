package com.steven.demo.controller.filter;

import com.alibaba.fastjson.JSON;
import com.yunzhangfang.platform.common.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Order(1)
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class MockLoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockLoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        try {
            String companyId = UserUtils.getUserCompanyId(httpRequest);
            String userId = UserUtils.getUserId(httpRequest);
            String phone = UserUtils.getPhone(httpRequest);
            String userName = UserUtils.getUserName(httpRequest);
            String path = httpRequest.getRequestURI();
            Map<String, Object> requestInfo = new HashMap<>();
            requestInfo.put("PATH",path);
            requestInfo.put(UserUtils.COMPANY_ID, companyId);
            requestInfo.put(UserUtils.USER_ID, userId);
            requestInfo.put(UserUtils.PHONE, phone);
            requestInfo.put(UserUtils.USER_NAME, userName);
            LOGGER.info("the request info:" + JSON.toJSONString(requestInfo));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } catch (Exception e) {
            LOGGER.error("LoginFilter meet exception:" + e.getMessage(), e);
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}