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
	public   String host = AccountInfo.getImap(); // ����������  
    public   String account = accountInfo.getAccount();//�����˻�  
    public   String password = accountInfo.getPassword();//����  
	
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
			Folder folder = store.getFolder(folderName);// �򿪲ݸ��� 
            folder.open(Folder.READ_WRITE);
                   
            Message[] messages = folder.getMessages(); 
			 
			
//			  Folder defaultFolder = store.getDefaultFolder();
//			  Folder[] allFolder = defaultFolder.list();
//			  for(int i=0;i<allFolder.length;i++) {
//			  	System.out.println(allFolder[i]);//�鿴�������е�Ĭ���ļ��У�INBOX���ݸ��䡢�ѷ��͡���ɾ���������ʼ��������ļ��У�
//			  } 
           
 	
			return messages;
			
		}	

}
