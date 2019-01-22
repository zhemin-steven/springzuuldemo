package com.steven.demo.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 102;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        String userId = "test";
        String userName = "测试";
        try {

            /** add some ZuulRequestHeader */
            ctx.addZuulRequestHeader("USER_ID", userId);
//            ctx.addZuulRequestHeader("USER_NAME", userName);
            ctx.addZuulRequestHeader("USER_NAME", URLEncoder.encode(userName, "UTF-8"));

            Map<String, Object> requestInfo = new HashMap<>();
            requestInfo.put("USER_ID", userId);
            requestInfo.put("USER_NAME", userName);
            log.info("the request header info :" + JSON.toJSONString(requestInfo));


        } catch (Exception e) {
            log.error("parse toekn error", e);
            try {
                //过滤该请求，不往下级服务去转发请求，到此结束
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("{\"message\":\"token信息异常!\",\"code\":\"1001\"}");
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                return null;

            } catch (Exception e1) {
                log.error("try catch a exception e1", e1);
            }
        }
        return null;
    }
}
