package model;

public class MailInfo {
	private static String receiveMan;
	private static String mailTopic;
	private static String mailContent;
	private static String path;

	public String getReceiveMan() {
		return receiveMan;
	}
	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}
	public String getMailTopic() {
		return mailTopic;
	}
	public void setMailTopic(String mailTopic) {
		this.mailTopic = mailTopic;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public  String getPath() {
		return path;
	}
	
	public  void setPath(String path) {
		MailInfo.path = path;
	}

}
