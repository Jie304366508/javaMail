package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import control.DialogSet;
import control.SaveMail;
import control.SelectFilePath;
import control.SentMail;
import model.AccountInfo;
import model.MailInfo;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SentBox extends JPanel {
	private JTextField receive_man;
	private JTextField topic;

	/**
	 * Create the panel.
	 */
	public SentBox() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(176, 187, 722, 274);
		add(scrollPane);
		
		JTextArea mail_txt = new JTextArea();
		mail_txt.setLineWrap(true);
		scrollPane.setViewportView(mail_txt);
		
		
		JButton button = new JButton("\u6DFB\u52A0\u9644\u4EF6");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加文件选择器JFileChooser
				MailInfo mi=new MailInfo();
				
				SelectFilePath sfp=new SelectFilePath();
				mi.setPath(sfp.selectFile());
				
				
			}


		});
		button.setBounds(176, 147, 113, 27);
		add(button);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				MailInfo mailInfo=new MailInfo();
				
				if(receive_man.getText().equals("")||topic.getText().equals("")||mail_txt.getText().equals("")) {
					JOptionPane.showConfirmDialog(null, "请输入完整信息后再发送！");
				}else {
					mailInfo.setReceiveMan(receive_man.getText());
					mailInfo.setMailTopic(topic.getText());
					mailInfo.setMailContent(mail_txt.getText());
					DialogSet ds=new DialogSet();
					ds.setDialogModel();
					
					SentMail ms =new SentMail();
					MailInfo mi=new MailInfo();
					
					try {
						ms.sentMail(mi.getPath());
						ds.dispose();
						JOptionPane.showConfirmDialog(null, "发送成功！");
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "发送失败！请重试");
					}
					

			        
				}
				
			}
			
		});
		
		
		
		btnNewButton.setBounds(176, 481, 113, 27);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u4FDD\u5B58\u8349\u7A3F");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MailInfo mailInfo=new MailInfo();
				
				if(receive_man.getText().equals("")||topic.getText().equals("")||mail_txt.getText().equals("")) {
					JOptionPane.showConfirmDialog(null, "请输入完整信息后再保存！");
				}else {
					mailInfo.setReceiveMan(receive_man.getText());
					mailInfo.setMailTopic(topic.getText());
					mailInfo.setMailContent(mail_txt.getText());
					
					DialogSet ds=new DialogSet();
					ds.setDialogModel();
					
					SaveMail sm=new SaveMail();
//					try {
						sm.save();
						ds.dispose();
						JOptionPane.showConfirmDialog(null, "保存成功！");
//					} catch (Exception e1) {
//						JOptionPane.showConfirmDialog(null, "保存失败！请重试");
//					}
					

			        
				}
				
			}
		});
		btnNewButton_1.setBounds(331, 481, 113, 27);
		add(btnNewButton_1);
		
		JLabel label = new JLabel("\u6536\u4EF6\u4EBA\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setBounds(78, 36, 72, 18);
		add(label);
		
		receive_man = new JTextField();
		receive_man.setBounds(176, 33, 722, 24);
		add(receive_man);
		receive_man.setColumns(10);
		
		JLabel label_1 = new JLabel("\u4E3B\u9898\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(78, 91, 72, 18);
		add(label_1);
		
		topic = new JTextField();
		topic.setBounds(178, 88, 720, 24);
		add(topic);
		topic.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6B63\u6587\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(78, 178, 72, 18);
		add(label_2);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			//清空按钮
			public void actionPerformed(ActionEvent e) {
				receive_man.setText("");
				topic.setText("");
				mail_txt.setText("");
				
				MailInfo mailInfo=new MailInfo();
				mailInfo.setReceiveMan("");
				mailInfo.setMailTopic("");
				mailInfo.setPath("");
				mailInfo.setMailContent("");
				
			}
		});
		button_1.setBounds(485, 481, 113, 27);
		add(button_1);
		
		
		

	}
}
