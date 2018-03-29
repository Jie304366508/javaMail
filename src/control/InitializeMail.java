package control;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class InitializeMail extends Thread{

	public static Object[] messageArray=new Object[6];//创建数组，存放message
	public   Message[] messagesTemp ;//病毒文件夹
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
//  	System.out.println(allFolder[i]);//查看服务器中的默认文件夹（INBOX、草稿箱、已发送、已删除、垃圾邮件、病毒文件夹）
//  } 

		

//多线程连接邮箱

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
//			JOptionPane.showConfirmDialog(null, folderName+"连接超时");
//		}
//		
//	}
//}
	
	
	

