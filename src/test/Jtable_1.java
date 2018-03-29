package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Jtable_1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jtable_1 frame = new Jtable_1();
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
	public Jtable_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Vector vData = new Vector();
		Vector vName = new Vector();
		vName.add("column1");
		vName.add("column2");
		Vector vRow = new Vector();
		vRow.add("cell 0 0");
		vRow.add("cell 0 1");
		vData.add(vRow.clone());
		vData.add(vRow.clone());
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		JTable jTable1 = new JTable();
		jTable1.setModel(model);


		 /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/  

//		JScrollPane scroll = new JScrollPane(jTable1);
//		scroll.setSize(300, 200);
//		scroll.setLocation(650, 300);
//		JFrame.add(scroll);


		//添加：（原理：重新set一下model即可，改的是new() model时候的数据而已，即vData）


		Vector vRow1 = new Vector();
		vRow1.add("cell 2 0");
		vRow1.add("cell 2 1");
		vData.add(vRow1);
		model = new DefaultTableModel(vData, vName);
		jTable1.setModel(model);
	}

}
