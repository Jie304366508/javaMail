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
        //跟smtp服务器建立一个连接
        Properties p = new Properties();
        // 开启debug调试，以便在控制台查看
        p.setProperty("mail.debug", "true");
        p.setProperty("mail.host", "smtp.sina.com");//指定邮件服务器，默认端口 25
        p.setProperty("mail.smtp.auth", "true");//要采用指定用户名密码的方式去认证
        // 发送邮件协议名称
        p.setProperty("mail.transport.protocol", "smtp");

        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        p.put("mail.smtp.ssl.enable", "true");
        p.put("mail.smtp.ssl.socketFactory", sf);

        // 创建session
        Session session = Session.getInstance(p);

        // 通过session得到transport对象
        Transport ts = session.getTransport();

        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        ts.connect("smtp.163.com", "175772885752@163.com", "zj336600");
        // 后面的字符是授权码，不能用qq密码

        //声明一个Message对象(代表一封邮件),从session中创建
        MimeMessage msg = new MimeMessage(session);
        //邮件信息封装
        //1发件人
        msg.setFrom( new InternetAddress("175772885752@163.com") );
        //2收件人
        msg.setRecipient(RecipientType.TO, new InternetAddress("304366508@qq.com") );
        //3邮件内容:主题、内容
        msg.setSubject("这是我用Java发来的邮件--带附件的....");

        //添加附件部分
        //邮件内容部分1---文本内容
        MimeBodyPart body0 = new MimeBodyPart(); //邮件中的文字部分
        body0.setContent("这是两张<font color='red'>图片</font>....","text/html;charset=utf-8");

        //邮件内容部分2---附件1
        MimeBodyPart body1 = new MimeBodyPart(); //附件1
        body1.setDataHandler( new DataHandler( new FileDataSource("./photo/123.jpg")) );//./代表项目根目录下

        body1.setFileName( MimeUtility.encodeText("中文1.jpg") );//中文附件名，解决乱码

        //邮件内容部分3---附件2
        MimeBodyPart body2 = new MimeBodyPart(); //附件2
        body2.setDataHandler( new DataHandler( new FileDataSource("./photo/123.jpg")) );
        body2.setFileName("2.jpg");

        //把上面的3部分组装在一起，设置到msg中
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(body0);
        mm.addBodyPart(body1);
        mm.addBodyPart(body2);
        msg.setContent(mm);

        // 发送邮件
        ts.sendMessage(msg,msg.getAllRecipients());
        ts.close();
    }
 
} 
