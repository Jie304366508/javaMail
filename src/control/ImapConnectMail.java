package control;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;

import model.AccountInfo;

public class ImapConnectMail {
	
	
	
//	public static String host = "imap.163.com";
//	public static String account = "17577288575@163.com";
//	public static String password = "zj336600";
	
	 AccountInfo accountInfo=new AccountInfo();
	public   String host = AccountInfo.getImap(); // 发件服务器  
    public   String account = accountInfo.getAccount();//邮箱账户  
    public   String password = accountInfo.getPassword();//密码  
	
	public  Message[] connect(String folderName) throws MessagingException  {
			
			System.out.println(folderName);
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
			Folder folder = store.getFolder(folderName);// 打开草稿箱 
            folder.open(Folder.READ_WRITE);
                   
            Message[] messages = folder.getMessages(); 
			 
			
//			  Folder defaultFolder = store.getDefaultFolder();
//			  Folder[] allFolder = defaultFolder.list();
//			  for(int i=0;i<allFolder.length;i++) {
//			  	System.out.println(allFolder[i]);//查看服务器中的默认文件夹（INBOX、草稿箱、已发送、已删除、垃圾邮件、病毒文件夹）
//			  } 
           
 	
			return messages;
			
		}	

}
