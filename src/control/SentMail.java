package control;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Flags;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.JOptionPane;

import model.AccountInfo;
import model.MailInfo;

import java.util.Date;
import java.util.Properties;

/**
 * JavaMail �汾: 1.6.0
 * JDK �汾: JDK 1.7 ���ϣ����룩
 */
public class SentMail {

    // �����˵� ���� �� ���루�滻Ϊ�Լ�����������룩
    // PS: ĳЩ���������Ϊ���������䱾������İ�ȫ�ԣ��� SMTP �ͻ��������˶������루�е������Ϊ����Ȩ�롱��, 
    //     ���ڿ����˶������������, ����������������ʹ������������루��Ȩ�룩��
//    public static String myEmailAccount = "17577288575@163.com";
//    public static String myEmailPassword = "zj336600";
	static AccountInfo info=new AccountInfo();
	
	public static   String myEmailAccount =info.getAccount() ;
    public static   String myEmailPassword = info.getPassword();

    // ����������� SMTP ��������ַ, ����׼ȷ, ��ͬ�ʼ���������ַ��ͬ, һ��(ֻ��һ��, ���Ǿ���)��ʽΪ: smtp.xxx.com
    // ����163����� SMTP ��������ַΪ: smtp.163.com
    public static   String myEmailSMTPHost = info.getSmtp();

    static MailInfo mailinfo=new MailInfo();
    // �ռ������䣨�滻Ϊ�Լ�֪������Ч���䣩
    public static   String receiveMailAccount = mailinfo.getReceiveMan();
    

    public static void sentMail(String path) throws Exception {
//    	System.out.println(myEmailAccount);
//    	System.out.println(myEmailPassword);
//    	System.out.println(myEmailSMTPHost);
//    	System.out.println(receiveMailAccount);
    	
        // 1. ������������, ���������ʼ��������Ĳ�������
        Properties props = new Properties();                    // ��������
        props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤

        // PS: ĳЩ���������Ҫ�� SMTP ������Ҫʹ�� SSL ��ȫ��֤ (Ϊ����߰�ȫ��, ����֧��SSL����, Ҳ�����Լ�����),
        //     ����޷������ʼ�������, ��ϸ�鿴����̨��ӡ�� log, ����������� ������ʧ��, Ҫ�� SSL ��ȫ���ӡ� �ȴ���,
        //     ������ /* ... */ ֮���ע�ʹ���, ���� SSL ��ȫ���ӡ�
        /*
        // SMTP �������Ķ˿� (�� SSL ���ӵĶ˿�һ��Ĭ��Ϊ 25, ���Բ�����, ��������� SSL ����,
        //                  ��Ҫ��Ϊ��Ӧ����� SMTP �������Ķ˿�, ����ɲ鿴��Ӧ�������İ���,
        //                  QQ�����SMTP(SLL)�˿�Ϊ465��587, ������������ȥ�鿴)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. �������ô����Ự����, ���ں��ʼ�����������
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log

        // 3. ����һ���ʼ�
        MimeMessage message;
		
			CreateMail cm=new CreateMail();
			message = cm.createMimeMessage(session, myEmailAccount, receiveMailAccount,"","",mailinfo.getMailTopic(),mailinfo.getMailContent(),path);
			
			Transport transport = session.getTransport();

	        // 5. ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
	        // 
	        //    PS_01: �ɰܵ��жϹؼ��ڴ�һ��, ������ӷ�����ʧ��, �����ڿ���̨�����Ӧʧ��ԭ��� log,
	        //           ��ϸ�鿴ʧ��ԭ��, ��Щ����������᷵�ش������鿴�������͵�����, ���ݸ����Ĵ���
	        //           ���͵���Ӧ�ʼ��������İ�����վ�ϲ鿴����ʧ��ԭ��
	        //
	        //    PS_02: ����ʧ�ܵ�ԭ��ͨ��Ϊ���¼���, ��ϸ������:
	        //           (1) ����û�п��� SMTP ����;
	        //           (2) �����������, ����ĳЩ���俪���˶�������;
	        //           (3) ���������Ҫ�����Ҫʹ�� SSL ��ȫ����;
	        //           (4) �������Ƶ��������ԭ��, ���ʼ��������ܾ�����;
	        //           (5) ������ϼ��㶼ȷ������, ���ʼ���������վ���Ұ�����
	        //
	        //    PS_03: ��ϸ��log, ���濴log, ����log, ����ԭ����log��˵����
	        transport.connect(myEmailAccount, myEmailPassword);

	        // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ���ӵ������ռ���, ������, ������
	        transport.sendMessage(message, message.getAllRecipients());

	        // 7. �ر�����
	        transport.close();
		

        // 4. ���� Session ��ȡ�ʼ��������
        
    }

   
    

}