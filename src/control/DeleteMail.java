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
		          
		        // ����ռ���  
		        Folder folder = store.getFolder(folderName);  
		        /* Folder.READ_ONLY��ֻ��Ȩ�� 
		         * Folder.READ_WRITE���ɶ���д�������޸��ʼ���״̬�� 
		         */  
		        folder.open(Folder.READ_WRITE); //���ռ���  
		          

		          
		        // �õ��ռ����е������ʼ�,������  
		        Message[] messages = folder.getMessages();  
//		        parseMessage(messages);  
		         
		        //�õ��ռ����е������ʼ�����ɾ���ʼ�
		        deleteMessage(tableIds,messages); 
		        folder.close(true);  
		        store.close();  
			
			
	}
	private static void deleteMessage(int[] tableIds,Message ...messages) throws MessagingException  {  
   
            /**
             *   �ʼ�ɾ��      
             */
		for(int i=0;i<tableIds.length;i++) {
			int j=tableIds[i];
			Message message = messages[j];
			message.setFlag(Flags.Flag.DELETED, true);
		}
            

    } 
}
