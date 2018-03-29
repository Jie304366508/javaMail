package model;

public  class  AccountInfo {
	private static String account;
	private static String password;
	private  static String smtp="smtp.163.com";
	private static String imap="imap.163.com";
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public static String getImap() {
		return imap;
	}
	public static void setImap(String pop3) {
		AccountInfo.imap = pop3;
	}
	
	
	
	
	
	
}
