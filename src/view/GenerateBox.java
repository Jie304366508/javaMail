package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.Checkbox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import control.DialogSet;
import control.DeleteMail;
import control.ImapConnectMail;
import control.InitializeMail;
//import control.Pop3ConnectMail;
import control.ReceiveMail;
import control.Refresh;
import model.TableId;
import test.AllTest;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenu;

public class GenerateBox extends JPanel {
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws MessagingException 
	 * @throws Exception 
	 */
	public GenerateBox()    {
	}
	public GenerateBox(Message[] messages ,String folderName,int tag)    {
		

//		System.out.println(folderName);
		
		setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setBounds(14, 13, 950, 2);
		add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 23, 950, 520);
		add(scrollPane);

		DefaultTableModel model=new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"\u9009\u62E9", "\u4E3B\u9898", "\u53D1\u4EF6\u4EBA", "\u65F6\u95F4"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					true, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			//������ݵ������
			ReceiveMail rm=new ReceiveMail();
			String[][] mailInfo;
			try {
				mailInfo = rm.parseMessage(messages);
				for(int i=0;i<mailInfo.length;i++) {
//					System.out.println(mailInfo[i][0]);
					Vector v=new Vector();
					v.add(false);
					v.add((Object)mailInfo[i][0]);
					v.add((Object)mailInfo[i][1]);
					v.add((Object)mailInfo[i][2]);
					
					model.addRow(v);
				}
			} catch (MessagingException e3) {
				
				e3.printStackTrace();
			} catch (IOException e3) {
				
				e3.printStackTrace();
			}
		
		table = new JTable();
		table.setRowMargin(3);
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setShowVerticalLines(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		
			
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem menuItem = new JMenuItem("\u5220\u9664");
		menuItem.setIcon(new ImageIcon("E:\\\u56FE\u7247\\Sign_In_32px_1081202_easyicon.net.png"));
		popupMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5168\u9009");
		popupMenu.add(menuItem_1);
		table.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{popupMenu, menuItem, menuItem_1}));
		
		
		
		table_1 = new JTable();
		table_1.setRowMargin(3);
		table_1.setRowHeight(30);
		table_1.setFillsViewportHeight(true);
		table_1.setShowVerticalLines(false);
		
		
		

		table_1.setModel(model);
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(300);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(3).setResizable(false);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		table_1.addMouseListener(new MouseAdapter(){ 
		      public void mouseClicked(MouseEvent e){ 
		    	  if(e.getClickCount() == 2) { //ʵ��˫���¼�

		    		  TableId id=new TableId();
		    		  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //�����λ�� 
		    		  id.setId(row);
		    		  
		    		  ImapConnectMail icm=new ImapConnectMail();
		    		  try {
						new MailDetail(icm.connect(folderName)[row]).setVisible(true);
					} catch (MessagingException e1) {
						JOptionPane.showConfirmDialog(null, "����ʧ�ܣ�������");
					}
		    	  } else return; 
		     } 
		});
		
		table_1.getTableHeader().setReorderingAllowed(false);//���ñ�ͷ�����϶�
		table_1.setColumnSelectionAllowed(false);//���ñ��һ��ѡ��һ�е�Ԫ��
		

		
		scrollPane.setViewportView(table_1);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(table_1, popupMenu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("ȫѡ");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ʵ�ֱ��ȫѡ
				
				for(int i=0;i<table_1.getRowCount();i++) {
					table_1.getModel().setValueAt(true, i, 0);
//					System.out.println();
				}
				
//				table_1.updateUI();
			}
		});
		
				popupMenu_1.add(menuItem_2);
				
				JMenuItem menuItem_3 = new JMenuItem("ɾ��");
				menuItem_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//ɾ��ѡ��
						int flag=0;
						DeleteMail dm=new DeleteMail();
						int count=table_1.getRowCount();
						//�õ�ѡ�е�����
						for(int i=0;i<count;i++) {					
							if((boolean) table_1.getModel().getValueAt(i, 0)) {	
								flag++;
							}					
						}
						

						if(flag!=0) {		
							int [] tableIds;
							tableIds=new int [flag];
							//��Ҫɾ�����кŴ�ŵ�������
							int j=0;
							for(int i=0;i<count;i++) {
								if((boolean) table_1.getModel().getValueAt(i, 0)) {	
										tableIds[j]=i;
										j++;
									}								
								}					
							
							//ɾ��ѡ���ʼ�
							try {
								DialogSet.setDialogModel();
								dm.delete(tableIds,folderName);
								Refresh r=new Refresh();//ˢ�±��
								r.refresh(model,folderName);//ˢ�������Ӧ�ļ���
								DialogSet.dispose();
								JOptionPane.showConfirmDialog(null, "ɾ���ɹ���");
								
							} catch (MessagingException e2) {
								JOptionPane.showConfirmDialog(null, "ɾ��ʧ�ܣ������²���");
							} catch (Exception e1) {
								JOptionPane.showConfirmDialog(null, "�Զ�ˢ��ʧ�ܣ����ֶ�ˢ��");
							}
	
						}else {
							JOptionPane.showConfirmDialog(null, "û��ѡ�У�������ѡ��");
						}
							
					}


				});
				popupMenu_1.add(menuItem_3);
				
				JMenuItem menuItem_4 = new JMenuItem("ˢ��");
				menuItem_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Refresh r=new Refresh();
						try {
							DialogSet ds=new DialogSet();
							ds.setDialogModel();
							Message[] newMessages = r.refresh(model,folderName);//ˢ�º�õ����µ�messages
							InitializeMail.messageArray[tag]=newMessages;//ˢ�º���¶�Ӧ�����ļ��е�messages
							ds.dispose();
							JOptionPane.showConfirmDialog(null, "ˢ�³ɹ���");
						} catch (Exception e1) {
							
							JOptionPane.showConfirmDialog(null, "ˢ�³�ʱ�������ԣ�");
						
						}
						
						
					}


				});
				popupMenu_1.add(menuItem_4);
		
		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			
			
		});
	}
}
