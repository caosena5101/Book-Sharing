package com.dyenigma.backend.service;

/**
 * backend/com.dyenigma.backend.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/27 10:13
 */
public interface EmailService {
    /**
     * 发送纯文本的简单邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @return void
     * @author dingdongliang
     * @date 2018/4/27 10:13
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html格式的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @return void
     * @author dingdongliang
     * @date 2018/4/27 10:13
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件地址
     * @return void
     * @author dingdongliang
     * @date 2018/4/27 10:13
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath 静态资源路径和文件名
     * @param rscId   静态资源id
     * @return void
     * @author dingdongliang
     * @date 2018/4/27 10:14
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
