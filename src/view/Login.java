package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import control.DialogSet;
import control.InitializeMail;
import model.AccountInfo;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JProgressBar;


//完成一个电子邮件客户端软件，要求能够收取邮件、发送邮件、书写新邮件、具有图形化界面。
//提示：可选用基于JavaMail网络包实现。
public class Login extends JFrame {
	private JTextField account_txt;
	private JPasswordField password_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
//					UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();//加载皮肤
					BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
					UIManager.put("RootPane.setupButtonVisible", false);//去掉设置按钮
					Login frame = new Login();
					frame.setLocationRelativeTo(null);//设置窗口居中
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("photo/mail.png"));
		this.setTitle("mail");
		this.setResizable(false);//不可最大化窗口
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 507);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 663, 460);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u90AE\u7BB1\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setBounds(104, 161, 72, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(104, 237, 72, 18);
		panel.add(label_1);
		
		account_txt = new JTextField();
		account_txt.setColumns(10);
		account_txt.setBounds(183, 158, 301, 24);
		panel.add(account_txt);
		
		password_txt = new JPasswordField();
		password_txt.setBounds(181, 234, 303, 24);
		panel.add(password_txt);
		
		JButton button = new JButton("登录");
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				pcm.connect();
				AccountInfo info=new AccountInfo();
				String acount=account_txt.getText();
				String password=new String(password_txt.getPassword());
//				String smtp=smtp_txt.getText();
//				String pop3=pop3_txt.getText();
				if(acount.equals("")||password.equals("")) {
					JOptionPane.showConfirmDialog(null, "请输入完整信息！");
				}else {
					info.setAccount(acount);
					info.setPassword(password);
//					info.setSmtp(smtp);
//					info.setImap(pop3);


					DialogSet ds=new DialogSet();//提示窗口，但可以让程序继续运行
					ds.setDialogModel();
					try {

					new Main().setVisible(true);
					ds.dispose();
						
					} catch (Exception e1) {
						
						ds.dispose();
						JOptionPane.showConfirmDialog(null, "登录失败，输入的信息有误！");
						new Login().setVisible(true);
					}
					
					dispose();
				}
			}
		});
		
		//实现回车登录
		button.registerKeyboardAction(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	AccountInfo info=new AccountInfo();
				String acount=account_txt.getText();
				String password=new String(password_txt.getPassword());
//				String smtp=smtp_txt.getText();
//				String pop3=pop3_txt.getText();
				if(acount.equals("")||password.equals("")) {
					JOptionPane.showConfirmDialog(null, "请输入完整信息！");
				}else {
					info.setAccount(acount);
					info.setPassword(password);
//					info.setSmtp(smtp);
//					info.setImap(pop3);


					DialogSet ds=new DialogSet();//提示窗口，但可以让程序继续运行
					ds.setDialogModel();
					try {

					new Main().setVisible(true);
					ds.dispose();
						
					} catch (Exception e1) {
						
						ds.dispose();
						JOptionPane.showConfirmDialog(null, "登录失败，输入的信息有误！");
						new Login().setVisible(true);
					}
					
					dispose();
				}
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		
		button.setBounds(175, 313, 113, 27);
		panel.add(button);
		
		
		
		
		
		
		
		JButton button_1 = new JButton("重置");
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account_txt.setText("");
				password_txt.setText("");
//				smtp_txt.setText("");
			}
		});
		button_1.setBounds(371, 313, 113, 27);
		panel.add(button_1);
		
		JLabel label_2 = new JLabel("\u90AE\u7BB1\u767B\u5F55\uFF08\u7F51\u6613\uFF09");
		label_2.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_2.setBounds(214, 48, 243, 42);
		panel.add(label_2);
		
		
		
		this.setLocationRelativeTo(null);//设置窗口居中
	}
}
