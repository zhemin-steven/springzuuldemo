package com.steven.demo.controller;

import com.yunzhangfang.platform.common.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by user on 2019/01/09.
 */

@RestController
@Slf4j
public class HelloWorldController {

    @GetMapping("/hello/{name}")
    public String index(@PathVariable("name") String name, HttpServletRequest request){
        return "Hello " + name+ " from port："+ request.getServerPort();
    }

}
