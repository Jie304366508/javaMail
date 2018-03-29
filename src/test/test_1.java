package test;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import control.CreateMail;
import model.AccountInfo;
import model.MailInfo;

public class test_1 {
	public static String host = "imap.163.com";
	public static String account = "17577288575@163.com";
	public static String password = "zj336600";
	public static void main(String[] args) throws MessagingException {
//		test();
//		
//		AccountInfo accountInfo=new AccountInfo();
//        MailInfo mailInfo=new MailInfo();
        	
        Properties props = System.getProperties();  
        props.put("mail.transport.protocol", "smtp");  
        props.put("mail.imap.host", host);  
        props.put("mail.imap.port", "143");  
//        props.setProperty("mail.transport.protocol", "smtp");  
        props.setProperty("mail.imap.auth", "true"); 
        
        

        
        Session session = Session.getInstance(props, new Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(account, password);  
            }  
        });  
 
            
            Session session1 = Session.getDefaultInstance(props, null);  
            Store store;
			
				store = session1.getStore("imap");
				store.connect(host, account, password);  
				
			  Folder defaultFolder = store.getDefaultFolder();
			  Folder[] allFolder = defaultFolder.list();
			  for(int i=0;i<allFolder.length;i++) {
			  	System.out.println(allFolder[i]);//查看服务器中的默认文件夹（INBOX、草稿箱、已发送、已删除、垃圾邮件、病毒文件夹）
			  } 
	            Folder folder = store.getFolder("草稿箱");// 打开草稿箱 
	            folder.open(Folder.READ_WRITE);
	            
//	            CreateMail cm=new CreateMail();
	            
	           // MimeMessage message = cm.createMimeMessage(session, accountInfo.getAccount(), mailInfo.getReceiveMan(),"","",mailInfo.getMailTopic(),mailInfo.getMailContent(),mailInfo.getPath());
	           Message[] messages = folder.getMessages(); 
//	            message.setFlag(Flags.Flag.DRAFT, true);  
//	            MimeMessage draftMessages[] = {message};  
//	            System.out.println(mmessage.getSubject());  
//	            folder.appendMessages(draftMessages);
	           System.out.println( messages[0].getSubject());
	          
 }




	private static void test() throws NoSuchProviderException, MessagingException {
		AccountInfo info=new AccountInfo();
//     	String duankou = "110";  				// 端口号
 		String host = "imap.163.com";   // 服务器地址"pop3.163.com"
 		String account="17577288575@163.com";
 		String paswword="zj123456";

 		 Properties props = System.getProperties();  
         props.put("mail.transport.protocol", "smtp");  
         props.put("mail.imap.host", host);  
         props.put("mail.imap.port", "143");  
//         props.setProperty("mail.transport.protocol", "smtp");  
         props.setProperty("mail.imap.auth", "true");  
         Session session = Session.getInstance(props, new Authenticator() {  
             protected PasswordAuthentication getPasswordAuthentication() {  
                 return new PasswordAuthentication(account, paswword);  
             }  
         });  
                
               
             Session session1 = Session.getDefaultInstance(props, null);  
             Store store = session1.getStore("imap");  
             store.connect(host, account, paswword);
             


			
			Folder defaultFolder = store.getDefaultFolder();
		      Folder[] allFolder = defaultFolder.list();
		      for(int i=0;i<allFolder.length;i++) {
		      	System.out.println(allFolder[i]);//查看服务器中的默认文件夹（INBOX、草稿箱、已发送、已删除、垃圾邮件、病毒文件夹）
		      } 
			// 获得收件箱  
	        Folder folder = store.getFolder("垃圾邮件");  
	       
	        folder.open(Folder.READ_WRITE); //打开收件箱  
	        
	        Message[] messages = folder.getMessages(); 
	      
//	        String[][] mailInfo;
//	        mailInfo=new String[messages.length][4];
	        for (int i = 0, count = messages.length; i < count; i++) {  
	            MimeMessage msg = (MimeMessage) messages[i];  
//	            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");  
	            try {
					System.out.println("主题: " + getSubject(msg));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
//	            System.out.println("发件人: " + getFrom(msg));  
//	            System.out.println("收件人：" + getReceiveAddress(msg, null));  
//	            System.out.println("发送时间：" + getSentDate(msg, null));  
//	            System.out.println("是否已读：" + isSeen(msg));  
//	            System.out.println("邮件优先级：" + getPriority(msg));  
//	            System.out.println("是否需要回执：" + isReplySign(msg));  
//	            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb"); 
	            
//	            boolean isContainerAttachment = isContainAttachment(msg);  
//	            System.out.println("是否包含附件：" + isContainerAttachment);  
//	            if (isContainerAttachment) {  
//	                saveAttachment(msg, "f:\\mailTest\\"+msg.getSubject() + "_"+i+"_"); //保存附件  
//	            }   
//	            StringBuffer content = new StringBuffer(30);  
//	            getMailTextContent(msg, content); 
	            // (content.length() > 100 ? content.substring(0,100) + "..." : 
//	            System.out.println("邮件正文：" +content);  
//	            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");  
//	            System.out.println();  
//	            mailInfo[i][0]=getSubject(msg);
//	            mailInfo[i][1]=getFrom(msg);
//	            mailInfo[i][2]=getSentDate(msg, null);
//	            mailInfo[i][3]=content.toString().split("<div>")[0];
	        }
	}
	       
	        

	
	 public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {  
	        return MimeUtility.decodeText(msg.getSubject());  
	    } 
	

}
