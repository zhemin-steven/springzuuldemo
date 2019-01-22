package com.steven.demo.service;

import com.steven.demo.vo.MailVo;

/**
 * @author zhemin
 * @date 2019/1/22 17:00
 * @description:
 **/
public interface MailService {

    void sendMail(MailVo mailVo);

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
