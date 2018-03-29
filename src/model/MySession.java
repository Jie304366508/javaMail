package model;

import javax.mail.Session;
//保存imap连接时的session对象（）
public class MySession {
	private static Session session;

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		MySession.session = session;
	}
}
