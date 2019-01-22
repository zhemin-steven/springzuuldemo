package com.steven.demo.controller.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author zhemin
 * @date 2018/10/17 14:18
 * @description:
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null!= attributes){
            HttpServletRequest request = attributes.getRequest();
            if(null !=request){
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        template.header(name, values);

                    }
                    logger.info("feign interceptor header:{}",template);
                }
            }else{
                logger.error("request is null");
            }
        }else{
            logger.error("attributes is null");
        }

    }
}
