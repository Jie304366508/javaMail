package test;

import java.util.Date;  
import java.util.Properties;  
  
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  
  
  
public class test_saveMail {  
      
      
      
    public static String host = "imap.163.com"; // ����������  
    public static String username = "17577288575@163.com";//�����˻�  
    public static String password = "zj123456";//����  
      
    public static void main(String[] args) {  
        sendMail("304366508@qq.com", "�ʼ�����", "<h1>�ع�������</h1>");  
    }  
      
      
    /** 
     * @param to �ռ��� 
     * @param title ���� 
     * @param content ���� 
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
//            	System.out.println(allFolder[i]);//�鿴�������е�Ĭ���ļ��У�INBOX���ݸ��䡢�ѷ��͡���ɾ���������ʼ��������ļ��У�
//            }
            
            
            Folder folder = store.getFolder("�ݸ���");// �򿪲ݸ���    
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
           System.out.println("����ɹ�");  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
}  