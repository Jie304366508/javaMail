package control;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import test.ProgressBar;



public class DialogSet {
	 
	
	static JDialog dialog = new JDialog();// ������ǰ����ĶԻ���  
	
	
    
	public static void setDialogModel()  { 
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("photo/mail.png"));
		dialog.setModal(false);// ���öԻ���Ϊģ̬         
		dialog.setSize(300, 200);// ���öԻ����С          
		dialog.setLocationByPlatform(true);// ��ϵͳƽ̨���ô���λ��         
		dialog.setTitle("��ʾ");// �Ի������

		dialog.setLocationRelativeTo(null);
//		dialog.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 300, 200);
		dialog.getContentPane().add(panel);
		
		JLabel label = new JLabel("Loading...");
		label.setFont(new Font("��Բ", Font.PLAIN, 20));
		label.setBounds(0, 0, 50, 50);
		
		panel.add(label);
		dialog.setResizable(false);//������󻯴���
		dialog.setVisible(true);// ��ʾ�Ի���
		
	}
	
	
	
	public static void dispose() {
		dialog.dispose();
	}
	
}
