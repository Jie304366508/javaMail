package control;

import java.util.Vector;

import javax.mail.Message;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Refresh {
	
	//刷新表格
	public Message[] refresh(DefaultTableModel model,String folderName) throws Exception {
		ReceiveMail rm=new ReceiveMail();
		String[][] mailInfo;
		ImapConnectMail icm=new ImapConnectMail();
			//刷新表格
			model.setRowCount(0);
//		ImapConnectMail	 pcm=new ImapConnectMail();//刷新要用新的连接，不能用初始化连接得到的静态数据,不要new这个对象，否则会初始化static变量，这里只需刷新部分邮箱
			
			Message[] messages = icm.connect(folderName);
			mailInfo = rm.parseMessage(messages);
			for(int i=0;i<mailInfo.length;i++) {
//				System.out.println(mailInfo[i][0]);
				Vector v=new Vector();
				v.add(false);
				v.add((Object)mailInfo[i][0]);
				v.add((Object)mailInfo[i][1]);
				v.add((Object)mailInfo[i][2]);
				
				model.addRow(v);
			}
			
			return messages;
		
	}
}
