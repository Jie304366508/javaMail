package model;

import javax.mail.Session;
//����imap����ʱ��session���󣨣�
public class MySession {
	private static Session session;

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		MySession.session = session;
	}
}
