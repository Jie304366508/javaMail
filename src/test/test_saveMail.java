package test;

import java.util.Date;  
import java.util.Properties;  
  
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  
  
  
public class test_saveMail {  
      
      
      
    public static String host = "imap.163.com"; // 发件服务器  
    public static String username = "17577288575@163.com";//邮箱账户  
    public static String password = "zj123456";//密码  
      
    public static void main(String[] args) {  
        sendMail("304366508@qq.com", "邮件主题", "<h1>呜哈哈哈哈</h1>");  
    }  
      
      
    /** 
     * @param to 收件人 
     * @param title 主题 
     * @param content 内容 
     */  
    public static void sendMail(String to, String title, String content) {  
        Properties props = System.getProperties();  
        props.put("mail.transport.protocol", "smtp");  
        props.put("mail.imap.host", host);  
        props.put("mail.imap.port", "143");  
//        props.setProperty("mail.transport.protocol", "smtp");  
        props.setProperty("mail.imap.auth", "true");  
        Session session = Session.getInstance(props, new Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(username, password);  
            }  
        });  
        try {         
            Properties prop = new Properties();  
            Session session1 = Session.getDefaultInstance(prop, null);  
            Store store = session1.getStore("imap");  
            store.connect(host, username, password);  
           
//            Folder defaultFolder = store.getDefaultFolder();
//            Folder[] allFolder = defaultFolder.list();
//            for(int i=0;i<allFolder.length;i++) {
//            	System.out.println(allFolder[i]);//查看服务器中的默认文件夹（INBOX、草稿箱、已发送、已删除、垃圾邮件、病毒文件夹）
//            }
            
            
            Folder folder = store.getFolder("草稿箱");// 打开草稿箱    
            MimeMessage mmessage = new MimeMessage(session);  
            mmessage.setFrom(new InternetAddress(username));  
            mmessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));  
            mmessage.setSubject(title);  
            Multipart mainPart = new MimeMultipart();  
            BodyPart html = new MimeBodyPart();  
            html.setContent(content, "text/html; charset=utf-8");  
            mainPart.addBodyPart(html);  
            mmessage.setContent(mainPart);  
            mmessage.setSentDate(new Date());  
            mmessage.saveChanges();  
            mmessage.setFlag(Flags.Flag.DRAFT, true);  
            MimeMessage draftMessages[] = {mmessage};  
//            System.out.println(mmessage.getSubject());  
            folder.appendMessages(draftMessages);  
//          Transport.send(mmessage);  
           System.out.println("保存成功");  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
}  