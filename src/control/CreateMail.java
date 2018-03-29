package control;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class CreateMail {
	 /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String sentMan,String receiveMan, String mailTopic,String mailContent,String path) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, sentMan, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMan, "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(mailTopic, "UTF-8");  
        
        //判断是否有附件
      if(path==null) {
    	// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
    	  message.setContent(mailContent, "text/html;charset=UTF-8");
      }else {
    	//添加附件部分
          //邮件内容部分1---文本内容
          MimeBodyPart body0 = new MimeBodyPart(); //邮件中的文字部分
          body0.setContent(mailContent,"text/html;charset=utf-8");
          
          //邮件内容部分2---附件1
          MimeBodyPart body1 = new MimeBodyPart(); //附件1
          body1.setDataHandler( new DataHandler( new FileDataSource(path)) );//./代表项目根目录下
          String[] filePath=path.split("\\\\");//
          String fileName=filePath[filePath.length-1];
          body1.setFileName(MimeUtility.encodeText(fileName) );//中文附件名，解决乱码
          
          //可以添加邮件部分2的代码实现添加多个附件

          //把上面的2部分组装在一起，设置到message中
          MimeMultipart mm = new MimeMultipart();
          mm.addBodyPart(body0);
          mm.addBodyPart(body1);
          message.setContent(mm);
      }
       
      // 6. 设置发件时间
        message.setSentDate(new Date());
//        message.setFlag(Flags.Flag.DRAFT, true);
        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
