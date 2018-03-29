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
//    public  String host = "imap.163.com"; // ����������  
    public  String account = accountInfo.getAccount();//�����˻�  
//    public  String password = "zj336600";//����  
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
     * @param to �ռ��� 
     * @param title ���� 
     * @param content ���� 
     * @return 
     * @throws Exception 
     */  
    public   void save()  { 
    	ImapConnectReturnStore icrs=new ImapConnectReturnStore();
    	Store store;
		try {
			store = icrs.GetImapConnectReturnStore();
			Folder folder = store.getFolder("�ݸ���");// �򿪲ݸ��� 
	        
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
		}//imap���� 
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
          
//	            Folder defaultFolder = store.getDefaultFolder();
//	            Folder[] allFolder = defaultFolder.list();
//	            for(int i=0;i<allFolder.length;i++) {
//	            	System.out.println(allFolder[i]);//�鿴�������е�Ĭ���ļ��У�INBOX�ݸ��䡢�ѷ��͡���ɾ���������ʼ��������ļ��У�
//	            }
	            
	
            
//          Transport.send(message);  
 //           System.out.println("����ɹ�");  
          
    }

	
}  