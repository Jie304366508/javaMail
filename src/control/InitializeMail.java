package control;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class InitializeMail extends Thread{

	public static Object[] messageArray=new Object[6];//�������飬���message
	public   Message[] messagesTemp ;//�����ļ���
	private int i;
	
	public InitializeMail(String folderName,int i) throws MessagingException {
		ImapConnectMail icm=new ImapConnectMail();
		this.i=i;
		this.messagesTemp=icm.connect(folderName);
		
	}
		
	public void run() {
			InitializeMail.messageArray[i]= messagesTemp;	
	}



}
//  Folder defaultFolder = store.getDefaultFolder();
//  Folder[] allFolder = defaultFolder.list();
//  for(int i=0;i<allFolder.length;i++) {
//  	System.out.println(allFolder[i]);//�鿴�������е�Ĭ���ļ��У�INBOX���ݸ��䡢�ѷ��͡���ɾ���������ʼ��������ļ��У�
//  } 

		

//���߳���������

//class MyThread1 implements Runnable{
//	private String folderName;
//	private int i;
//	public MyThread1(String folderName,int i) {
//		this.folderName=folderName;
//		this.i=i;
//	}
//		
//	
//	public void run() {
//		try {
//			InitializeMail.messageArray[i]= ImapConnectMail.connect(folderName);
//		} catch (MessagingException e) {
//			JOptionPane.showConfirmDialog(null, folderName+"���ӳ�ʱ");
//		}
//		
//	}
//}
	
	
	

