package test;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

public class SentFujian { 
	public static void main(String[] args) throws Exception {
		send2();
	}
 
	public static  void send2() throws Exception{
        //��smtp����������һ������
        Properties p = new Properties();
        // ����debug���ԣ��Ա��ڿ���̨�鿴
        p.setProperty("mail.debug", "true");
        p.setProperty("mail.host", "smtp.sina.com");//ָ���ʼ���������Ĭ�϶˿� 25
        p.setProperty("mail.smtp.auth", "true");//Ҫ����ָ���û�������ķ�ʽȥ��֤
        // �����ʼ�Э������
        p.setProperty("mail.transport.protocol", "smtp");

        // ����SSL���ܣ������ʧ��
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        p.put("mail.smtp.ssl.enable", "true");
        p.put("mail.smtp.ssl.socketFactory", sf);

        // ����session
        Session session = Session.getInstance(p);

        // ͨ��session�õ�transport����
        Transport ts = session.getTransport();

        // �����ʼ����������������ͣ��ʺţ���Ȩ��������루����ȫ��
        ts.connect("smtp.163.com", "175772885752@163.com", "zj336600");
        // ������ַ�����Ȩ�룬������qq����

        //����һ��Message����(����һ���ʼ�),��session�д���
        MimeMessage msg = new MimeMessage(session);
        //�ʼ���Ϣ��װ
        //1������
        msg.setFrom( new InternetAddress("175772885752@163.com") );
        //2�ռ���
        msg.setRecipient(RecipientType.TO, new InternetAddress("304366508@qq.com") );
        //3�ʼ�����:���⡢����
        msg.setSubject("��������Java�������ʼ�--��������....");

        //��Ӹ�������
        //�ʼ����ݲ���1---�ı�����
        MimeBodyPart body0 = new MimeBodyPart(); //�ʼ��е����ֲ���
        body0.setContent("��������<font color='red'>ͼƬ</font>....","text/html;charset=utf-8");

        //�ʼ����ݲ���2---����1
        MimeBodyPart body1 = new MimeBodyPart(); //����1
        body1.setDataHandler( new DataHandler( new FileDataSource("./photo/123.jpg")) );//./������Ŀ��Ŀ¼��

        body1.setFileName( MimeUtility.encodeText("����1.jpg") );//���ĸ��������������

        //�ʼ����ݲ���3---����2
        MimeBodyPart body2 = new MimeBodyPart(); //����2
        body2.setDataHandler( new DataHandler( new FileDataSource("./photo/123.jpg")) );
        body2.setFileName("2.jpg");

        //�������3������װ��һ�����õ�msg��
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(body0);
        mm.addBodyPart(body1);
        mm.addBodyPart(body2);
        msg.setContent(mm);

        // �����ʼ�
        ts.sendMessage(msg,msg.getAllRecipients());
        ts.close();
    }
 
} 
