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
	 
	
	static JDialog dialog = new JDialog();// 创建当前窗体的对话框  
	
	
    
	public static void setDialogModel()  { 
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("photo/mail.png"));
		dialog.setModal(false);// 设置对话框为模态         
		dialog.setSize(300, 200);// 设置对话框大小          
		dialog.setLocationByPlatform(true);// 由系统平台布置窗体位置         
		dialog.setTitle("提示");// 对话框标题

		dialog.setLocationRelativeTo(null);
//		dialog.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 300, 200);
		dialog.getContentPane().add(panel);
		
		JLabel label = new JLabel("Loading...");
		label.setFont(new Font("幼圆", Font.PLAIN, 20));
		label.setBounds(0, 0, 50, 50);
		
		panel.add(label);
		dialog.setResizable(false);//不可最大化窗口
		dialog.setVisible(true);// 显示对话框
		
	}
	
	
	
	public static void dispose() {
		dialog.dispose();
	}
	
}
