package test;

public class GetFileName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getFileName();
	}
	
	public static void getFileName() {
		String fileName="c:\\ÄãºÃ\\ÄÈÄÈ¡£jpg";
		String[] str=fileName.split("\\\\");
		for(int i=0;i<str.length;i++) {
			System.out.println(str[i]);
		}
	
		
	}
	

}
