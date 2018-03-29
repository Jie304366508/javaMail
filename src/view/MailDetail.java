package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DialogSet;
import control.ImapConnectMail;
import control.ReceiveMail;
import control.SelectFilePath;
import model.TableId;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;

public class MailDetail extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailDetail frame = new MailDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MailDetail() {
		
	}
	public MailDetail(Message message) {
		view(message);
	}

	private void view(Message message) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("photo/mail.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("����");
		label.setBounds(83, 39, 72, 18);
		label.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(label);
		
		JTextPane topic_txt = new JTextPane();
		topic_txt.setEditable(false);
		topic_txt.setBounds(169, 33, 464, 24);
		contentPane.add(topic_txt);
		
		JLabel label_1 = new JLabel("������");
		label_1.setBounds(83, 106, 72, 18);
		label_1.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(label_1);
		
		JTextPane sentMan_txt = new JTextPane();
		sentMan_txt.setEditable(false);
		sentMan_txt.setBounds(169, 106, 464, 24);
		contentPane.add(sentMan_txt);
		
		JLabel label_2 = new JLabel("����");
		label_2.setBounds(83, 234, 72, 18);
		label_2.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(169, 231, 464, 258);
		contentPane.add(scrollPane);
		
		JTextArea content_txt = new JTextArea();
		content_txt.setEditable(false);
		content_txt.setLineWrap(true);
		scrollPane.setViewportView(content_txt);
		

		
		JLabel label_3 = new JLabel("����ʱ��");
		label_3.setBounds(83, 181, 83, 18);
		label_3.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(label_3);
		
		JTextPane sentTime_txt = new JTextPane();
		sentTime_txt.setEditable(false);
		sentTime_txt.setBounds(169, 181, 464, 24);
		contentPane.add(sentTime_txt);
		
		JLabel label_4 = new JLabel("�Ƿ��������");
		label_4.setBounds(69, 517, 120, 18);
		label_4.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(label_4);
		
		
		JRadioButton radioButton = new JRadioButton("��");
		radioButton.setSelected(false);
		radioButton.setEnabled(false);
		radioButton.setBounds(199, 513, 52, 27);
		radioButton.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("��");
		radioButton_1.setEnabled(false);
		radioButton_1.setBounds(257, 513, 52, 27);
		radioButton_1.setFont(new Font("����", Font.PLAIN, 17));
		contentPane.add(radioButton_1);
		
		MimeMessage msg = (MimeMessage) message;
		
        StringBuffer content = new StringBuffer(30);   
        
        
		try {
			ReceiveMail.getMailTextContent(msg, content);
			topic_txt.setText(ReceiveMail.getSubject(msg));
			sentMan_txt.setText(ReceiveMail.getFrom(msg));
			sentTime_txt.setText(ReceiveMail.getSentDate(msg, null));
			content_txt.setText(content.toString().split("<div>")[0]);
			boolean isContainerAttachment = ReceiveMail.isContainAttachment(msg);//�ж��Ƿ��и���
			if(isContainerAttachment) {
				radioButton.setSelected(true);
			}else {
				radioButton_1.setSelected(true);
			}
			    

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "��ȡ�ʼ�����ʧ�ܣ������»�ȡ!");
		}
		
		
		JButton button = new JButton("���ظ���");
		button.setFont(new Font("����", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSet ds=new DialogSet();
				  if (radioButton.isSelected()) { //���渽��  
					  try {
						 SelectFilePath sfp=new SelectFilePath();
						 String path=sfp.selectPath();
						 if(path==null) {
							 
						 }else {
							 ds.setDialogModel();
								ReceiveMail.saveAttachment(msg, path);
								ds.dispose();
								JOptionPane.showConfirmDialog(null, "����ɹ���");
						 }
						
					} catch (Exception e1) {
						JOptionPane.showConfirmDialog(null, "����ʧ�ܣ������ԣ�");
					}   
			  }else {
				  JOptionPane.showConfirmDialog(null, "���ʼ�û�и�����");
			  } 
			}
		});
		button.setBounds(365, 513, 113, 27);
		contentPane.add(button);
		
		
		JButton button_1 = new JButton("�˳�");
		button_1.setFont(new Font("����", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(523, 513, 113, 27);
		contentPane.add(button_1);
		//��ȡ�ʼ���Ϣ
//		ReceiveMail rm=new ReceiveMail();
//		try {
//			String[][] info=rm.parseMessage(messages);
//			
//			//�õ�tableѡ�е��к�
//			TableId gt=new TableId();
//			int id = gt.getId();
//			
//			//��ʾ�ʼ���Ϣ
//			topic_txt.setText(info[id][0]);
//			sentMan_txt.setText(info[id][1]);
//			sentTime_txt.setText(info[id][2]);
//			content_txt.setText(info[id][3]);
//			
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		System.out.println("����: " + getSubject(msg));  
//      System.out.println("������: " + getFrom(msg));  
//      System.out.println("�ռ��ˣ�" + getReceiveAddress(msg, null));  
//      System.out.println("����ʱ�䣺" + getSentDate(msg, null));  
		
		
		
		
		
		
		this.setLocationRelativeTo(null);//���ھ���
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);//������Ͻǹرմ��ڰ�ť�����ò��˳�����
	}

//	public MailDetail(Object object) {
//		// TODO Auto-generated constructor stub
//	}
}
