package com.steven.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steven.demo.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhemin
 * @date 2019/1/22 15:25
 * @description:
 **/
@SpringBootApplication
public class MailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class);
    }

    @Component
    public static class StringToUserConverter implements Converter<String, MailVo>{
        @Autowired
        private ObjectMapper objectMapper;
        @Override
        public MailVo convert(String source){
            try {
                return objectMapper.readValue(source, MailVo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


