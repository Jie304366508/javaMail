package control;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;

import model.AccountInfo;
import test.AllTest;

public class DeleteMail {
	public void delete(int[] tableIds,String folderName) throws MessagingException {

		ImapConnectReturnStore icrs=new ImapConnectReturnStore();
		Store store=icrs.GetImapConnectReturnStore();
		          
		        // 获得收件箱  
		        Folder folder = store.getFolder(folderName);  
		        /* Folder.READ_ONLY：只读权限 
		         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
		         */  
		        folder.open(Folder.READ_WRITE); //打开收件箱  
		          

		          
		        // 得到收件箱中的所有邮件,并解析  
		        Message[] messages = folder.getMessages();  
//		        parseMessage(messages);  
		         
		        //得到收件箱中的所有邮件并且删除邮件
		        deleteMessage(tableIds,messages); 
		        folder.close(true);  
		        store.close();  
			
			
	}
	private static void deleteMessage(int[] tableIds,Message ...messages) throws MessagingException  {  
   
            /**
             *   邮件删除      
             */
		for(int i=0;i<tableIds.length;i++) {
			int j=tableIds[i];
			Message message = messages[j];
			message.setFlag(Flags.Flag.DELETED, true);
		}
            

    } 
}
