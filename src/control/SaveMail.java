package control;

import java.util.Date;  
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import model.AccountInfo;
import model.MailInfo;
import model.MySession;  
  
  
public class SaveMail {  
      
      
	AccountInfo accountInfo=new AccountInfo();
    MailInfo mailInfo=new MailInfo(); 
//    public  String host = "imap.163.com"; // 发件服务器  
    public  String account = accountInfo.getAccount();//邮箱账户  
//    public  String password = "zj336600";//密码  
    public  String receiveMan = mailInfo.getReceiveMan();
    public  String topic = mailInfo.getMailTopic();
    public  String mainContent = mailInfo.getMailContent();
    public  String path =mailInfo.getPath();
    
    
      
//    public static void main(String[] args) {  
//        try {
//			save()  ;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }  
      
      
    /** 
     * @param to 收件人 
     * @param title 主题 
     * @param content 内容 
     * @return 
     * @throws Exception 
     */  
    public   void save()  { 
    	ImapConnectReturnStore icrs=new ImapConnectReturnStore();
    	Store store;
		try {
			store = icrs.GetImapConnectReturnStore();
			Folder folder = store.getFolder("草稿箱");// 打开草稿箱 
	        
	        CreateMail cm=new CreateMail();
	        MySession ms=new MySession();
	        
	        MimeMessage message = cm.createMimeMessage(MySession.getSession(), account, receiveMan,"","",topic,mainContent,path);
	        
//		            MimeMessage message = new MimeMessage(session);  
//		            message.setFrom(new InternetAddress(username));  
//		            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//		            message.setSubject(title);  
//		            Multipart mainPart = new MimeMultipart();  
//		            BodyPart html = new MimeBodyPart();  
//		            html.setContent(content, "text/html; charset=utf-8");  
//		            mainPart.addBodyPart(html);  
//		            message.setContent(mainPart);  
//		            message.setSentDate(new Date());  
//		            message.saveChanges();  
	        message.setFlag(Flags.Flag.DRAFT, true);  
	        MimeMessage draftMessages[] = {message};  
//		            System.out.println(mmessage.getSubject());  
	        folder.appendMessages(draftMessages);  
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//imap连接 
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
          
//	            Folder defaultFolder = store.getDefaultFolder();
//	            Folder[] allFolder = defaultFolder.list();
//	            for(int i=0;i<allFolder.length;i++) {
//	            	System.out.println(allFolder[i]);//查看服务器中的默认文件夹（INBOX草稿箱、已发送、已删除、垃圾邮件、病毒文件夹）
//	            }
	            
	
            
//          Transport.send(message);  
 //           System.out.println("保存成功");  
          
    }

	
}  