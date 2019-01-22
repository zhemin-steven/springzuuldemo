package com.steven.demo.controller;

import com.steven.demo.service.MailService;
import com.steven.demo.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhemin
 * @date 2019/1/22 15:35
 * @description:
 **/
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件 + 附件
     */
    @PostMapping(value = "/mail/send")
    public MailVo sendMail(@RequestParam("mail") MailVo mailVo,@RequestParam("file") MultipartFile[] files) {
        if(!StringUtils.hasText(mailVo.getFrom())){
            mailVo.setTo("sunzhemin@yunzhangfang.com");
            mailVo.setSubject("Test");
            mailVo.setText("Test");
        }
        mailVo.setMultipartFiles(files);
        mailService.sendMail(mailVo);
        return mailVo;
    }

    /**
     * 发送邮件
     */
    @PostMapping(value = "/mail/send/body")
    public MailVo sendMail(@RequestBody MailVo mailVo) {
        if(!StringUtils.hasText(mailVo.getFrom())){
            mailVo.setTo("sunzhemin@yunzhangfang.com");
            mailVo.setSubject("Test");
            mailVo.setText("Test");
        }
        mailService.sendMail(mailVo);
        return mailVo;
    }
}
