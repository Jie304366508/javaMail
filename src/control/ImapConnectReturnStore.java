package control;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import model.AccountInfo;
import model.MailInfo;
import model.MySession;

public class ImapConnectReturnStore {
//	public  String host = "imap.163.com"; // 发件服务器  
//    public  String username = "17577288575@163.com";//邮箱账户  
//    public  String password = "zj336600";//密码  
	
	AccountInfo accountInfo=new AccountInfo();

	public  String host = AccountInfo.getImap(); // 发件服务器  
    public  String account = accountInfo.getAccount();//邮箱账户  
    public  String password = accountInfo.getPassword();//密码  
    
	public  Store GetImapConnectReturnStore() throws NoSuchProviderException, MessagingException {

    	
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
               
//            Properties prop = new Properties();  
        
        Session session1 = Session.getDefaultInstance(props, null); 
        
        MySession.setSession(session1);
        
        
        Store store;
		
		store = session1.getStore("imap");
		store.connect(host, account, password);
		return store;
	}  
}
