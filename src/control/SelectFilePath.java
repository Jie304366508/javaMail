package control;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SelectFilePath {
	public String selectFile() {
		//����ļ�ѡ����JFileChooser
		String path = null;
		JFileChooser jfc=new JFileChooser();                        
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
	    int value=jfc.showDialog(new JLabel(), "ѡ��");  
	    File file=jfc.getSelectedFile();
	    
	    
//	    int value=jfc.showSaveDialog(null);
	    if(value==jfc.APPROVE_OPTION) {//�ж��Ƿ�������ȡ����ť
	    	
	    	if(file.isDirectory()){ //�����·��
		    	//	  System.out.println("�ļ���:"+file.getAbsolutePath()); 
		    	JOptionPane.showConfirmDialog(null, "�ļ�ûѡ��,������ѡ��");
		    	
		    }
		    if(file.isFile()){  
		        path = file.getAbsolutePath();  
		       
		    }
	    }else {//�������ȡ����ť
	    	
	    }
	    
	    
//	  System.out.println(path+jfc.getSelectedFile().getName());�����ļ������ľ���·��
	    
	    return path;
	}
	public String selectPath() {
		
		String path = null;
		JFileChooser jfc=new JFileChooser();                        
	    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	    int value=jfc.showDialog(new JLabel(), "ѡ��");  
	    File file=jfc.getSelectedFile();
	    if(value==jfc.APPROVE_OPTION) {//�ж��Ƿ�������ȡ����ť
	    	path = file.getAbsolutePath();  	
	    }else {//�������ȡ����ť
	    	
	    }
	    return path;
	}
}
