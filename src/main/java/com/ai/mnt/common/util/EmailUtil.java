package com.ai.mnt.common.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.ai.mnt.model.stat.EmailAttachment;
import com.ai.mnt.model.stat.EmailInfo;

public class EmailUtil {

    private static Logger logger = Logger.getLogger(EmailUtil.class);
    
    public static void sendEmail(EmailInfo mailInfo) throws Exception {
        logger.info("===========开始发送邮件===============");
        Transport tSport = null;
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", mailInfo.getHost());
            prop.setProperty("mail.transport.protocol", mailInfo.getProtocol());
            if(mailInfo.isSmtpAuth()) {
                prop.setProperty("mail.smtp.auth", "true");
            }
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            tSport = session.getTransport();
            tSport.connect(mailInfo.getHost(), mailInfo.getUsername(), mailInfo.getPassword());
    
            MimeMessage message = new MimeMessage(session);
            //发件人
            message.setFrom(new InternetAddress(mailInfo.getFromAddr()));
            //收件人
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress("matrix62102@sina.com"));
            int toCount = mailInfo.getToAddrs().size();
            if(toCount == 0) {
                throw new Exception("收件人不能不为空！");
            }
            Address[] toAddrs = new Address[toCount];
            for(int i=0; i<toCount; i++) {
                toAddrs[i] = new InternetAddress(mailInfo.getToAddrs().get(i));
            }
            message.addRecipients(Message.RecipientType.TO, toAddrs);
            
            //抄送人
            int cCount = mailInfo.getCCs().size();
            if(cCount > 0) {
                Address[] toCCs = new Address[cCount];
                for(int i=0; i<cCount; i++) {
                    toCCs[i] = new InternetAddress(mailInfo.getCCs().get(i));
                }
                message.addRecipients(Message.RecipientType.CC, toCCs);
            }
            
            //邮件标题
            message.setSubject(mailInfo.getSubject());
            
            MimeMultipart mmp = new MimeMultipart();
            mmp.setSubType(mailInfo.getSubType());
            //正文
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
            mmp.addBodyPart(text);
            
            //附件
            List<EmailAttachment> emailAttachs = mailInfo.getEmailAttachs();
            for(EmailAttachment emailAttachment : emailAttachs) {
                MimeBodyPart attach = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource(emailAttachment.getAttachPath()));
                attach.setDataHandler(dh);
                attach.setFileName(dh.getName());
                attach.setDescription(dh.getName());
                mmp.addBodyPart(attach);
            }
            
            message.setContent(mmp);
            message.saveChanges();
            tSport.sendMessage(message, message.getAllRecipients());
            //message.writeTo(new FileOutputStream("textMail.eml"));
            logger.info("===========邮件发送成功===============");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("===========邮件发送失败===============");
        }finally {
            if(tSport != null) {
                tSport.close();
            }
        }
    }
    
}
