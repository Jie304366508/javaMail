package control;

import java.util.Vector;

import javax.mail.Message;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Refresh {
	
	//ˢ�±��
	public Message[] refresh(DefaultTableModel model,String folderName) throws Exception {
		ReceiveMail rm=new ReceiveMail();
		String[][] mailInfo;
		ImapConnectMail icm=new ImapConnectMail();
			//ˢ�±��
			model.setRowCount(0);
//		ImapConnectMail	 pcm=new ImapConnectMail();//ˢ��Ҫ���µ����ӣ������ó�ʼ�����ӵõ��ľ�̬����,��Ҫnew������󣬷�����ʼ��static����������ֻ��ˢ�²�������
			
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
