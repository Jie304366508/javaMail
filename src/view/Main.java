package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

import control.ImapConnectMail;
import control.InitializeMail;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Font;

public class Main extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
					UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
					
					Main frame = new Main();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws MessagingException 
	 * @throws Exception 
	 */
	public Main() throws MessagingException   {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("photo/mail.png"));
		
		long start = System.currentTimeMillis();
		//���̳߳�ʼ����6������ͬʱִ�У�
		String folderName = null;
		ArrayList<Thread> list=new ArrayList<Thread>();
		for(int i=0;i<6;i++) {
			switch(i) {
				case 0:folderName="INBOX";break;
				case 1:folderName="�ݸ���";break;
				case 2:folderName="�ѷ���";break;
				case 3:folderName="��ɾ��";break;
				case 4:folderName="�����ʼ�";break;
				case 5:folderName="�����ļ���";break;
			}
			
			InitializeMail im=new InitializeMail(folderName, i);
			
			im.start();
			list.add(im);
			
		}
		for(Thread thread:list) {
			try {
				thread.join();//����������
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
		}
		long end=System.currentTimeMillis();
		
			
			
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 689);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1032, 51);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 48, 1032, 606);
		getContentPane().add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		

		CardLayout card=(CardLayout) panel_1.getLayout();
		
		
		JPanel sentBox=new SentBox();
		sentBox.setBounds(0, 48, 931, 541);
		panel_1.add(sentBox,"sentBox");
		
		long start1 = System.currentTimeMillis();
		String folderName1=null;
		ArrayList<Thread> list1=new ArrayList<Thread>();
		for(int j=0;j<6;j++) {
			switch(j) {
			case 0:folderName1="INBOX";break;
			case 1:folderName1="�ݸ���";break;
			case 2:folderName1="�ѷ���";break;
			case 3:folderName1="��ɾ��";break;
			case 4:folderName1="�����ʼ�";break;
			case 5:folderName1="�����ļ���";break;
			}
			MyThread mt=new MyThread(panel_1,folderName1,j);
			mt.start();
			list1.add(mt);
		}
		for(Thread thread1:list1) {
			try {
				thread1.join();
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
		}
		long end1 = System.currentTimeMillis();
		System.out.println((end-start)/1000+"s");
		System.out.println((end1-start1)/1000+"s");
		
//		new MyThread(panel_1,"INBOX",0).start();//����д�����̲�������
//		new MyThread(panel_1,"�ݸ���",1).start();
//		new MyThread(panel_1,"�ѷ���",2).start();
//		new MyThread(panel_1,"��ɾ��",3).start();
//		new MyThread(panel_1,"�����ʼ�",4).start();
//		new MyThread(panel_1,"�����ļ���",5).start();
			
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		menuBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(menuBar, "name_527479471016034");
		
		JMenuItem menuItem_6 = new JMenuItem("���ʼ�");
		menuItem_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_6.setBackground(Color.LIGHT_GRAY);
		menuItem_6.setSelected(true);
		menuBar.add(menuItem_6);
		
		
		JMenuItem menuItem_7 = new JMenuItem("�ռ���");
		menuItem_7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_7.setBackground(Color.LIGHT_GRAY);
		menuBar.add(menuItem_7);
		menuItem_7.setSelected(true);
		
		JMenuItem menuItem_8 = new JMenuItem("�ݸ���");
		menuItem_8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					card.show(panel_1, "driftBox");
				
			}
		});
		menuBar.add(menuItem_8);
		
		JMenuItem menuItem_9 = new JMenuItem("�ѷ���");
		menuItem_9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel_1, "hadSentMail");
			}
		});
		menuBar.add(menuItem_9);
		
		JMenuItem menuItem_10 = new JMenuItem("��ɾ��");
		menuItem_10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel_1, "hadDeleteMail");
				
			}
		});
		menuBar.add(menuItem_10);
		
		JMenuItem menuItem_11 = new JMenuItem("�˳�ϵͳ");
		menuItem_11.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_11.setSelected(true);
		
		menuItem_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "ȷ���˳�ϵͳ?");
				if(a==0) {
					dispose();
					
				}
			}
		});
		
		JMenuItem menuItem = new JMenuItem("�����ʼ�");
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				menuItem.setOpaque(true);
//				menuItem.setBackground(Color.CYAN);
				card.show(panel_1, "garbageMail");
			}
		});
		menuBar.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("�����ļ���");
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		menuItem_1.setSelected(true);
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel_1, "virusFolder");
			}
		});
		menuBar.add(menuItem_1);
		menuBar.add(menuItem_11);
		menuItem_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel_1, "receiveBox");
			}
		});
		menuItem_6.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				card.show(panel_1, "sentBox");
				
			}
		});
		
//		menuItem_6.setHorizontalAlignment(SwingConstants.RIGHT);

		this.setResizable(false);//�������		
		this.setLocationRelativeTo(null);//���ھ���
		
	}
}

//���̼߳������
class MyThread extends Thread{
	
	private JPanel panel;
	private JPanel panelBox;
	private String folderName;
	private int tag;
	
	public  MyThread(JPanel panel,String folderName,int tag) {
		this.panel=panel;
		this.folderName=folderName;
		this.tag=tag;
	}
	
	public void run() {
		System.out.println(tag+"��ʼ");
		
		panelBox=new GenerateBox((Message[])InitializeMail.messageArray[tag],folderName,tag);
		String name="";

		switch(tag) {
			case 0:name="receiveBox";break;
			case 1:name="driftBox";break;
			case 2:name="hadSentMail";break;
			case 3:name="hadDeleteMail";break;
			case 4:name="garbageMail";break;
			case 5:name="virusFolder";break;
		}
		System.out.println(tag+"����");
		
		
		panelBox.setBounds(0, 48, 931, 541);
		panel.add(panelBox,name);

	}
}

