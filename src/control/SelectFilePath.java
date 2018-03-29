package control;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SelectFilePath {
	public String selectFile() {
		//添加文件选择器JFileChooser
		String path = null;
		JFileChooser jfc=new JFileChooser();                        
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
	    int value=jfc.showDialog(new JLabel(), "选择");  
	    File file=jfc.getSelectedFile();
	    
	    
//	    int value=jfc.showSaveDialog(null);
	    if(value==jfc.APPROVE_OPTION) {//判断是否点击的是取消按钮
	    	
	    	if(file.isDirectory()){ //如果是路径
		    	//	  System.out.println("文件夹:"+file.getAbsolutePath()); 
		    	JOptionPane.showConfirmDialog(null, "文件没选中,请重新选择");
		    	
		    }
		    if(file.isFile()){  
		        path = file.getAbsolutePath();  
		       
		    }
	    }else {//点击的是取消按钮
	    	
	    }
	    
	    
//	  System.out.println(path+jfc.getSelectedFile().getName());这是文件完整的绝对路径
	    
	    return path;
	}
	public String selectPath() {
		
		String path = null;
		JFileChooser jfc=new JFileChooser();                        
	    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	    int value=jfc.showDialog(new JLabel(), "选择");  
	    File file=jfc.getSelectedFile();
	    if(value==jfc.APPROVE_OPTION) {//判断是否点击的是取消按钮
	    	path = file.getAbsolutePath();  	
	    }else {//点击的是取消按钮
	    	
	    }
	    return path;
	}
}
