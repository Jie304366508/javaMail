package control;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class CreateMail {
	 /**
     * ����һ��ֻ�����ı��ļ��ʼ�
     *
     * @param session �ͷ����������ĻỰ
     * @param sendMail ����������
     * @param receiveMail �ռ�������
     * @return
     * @throws Exception
     */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String sentMan,String receiveMan, String mailTopic,String mailContent,String path) throws Exception {
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);

        // 2. From: �����ˣ��ǳ��й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸��ǳƣ�
        message.setFrom(new InternetAddress(sendMail, sentMan, "UTF-8"));

        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMan, "UTF-8"));

        // 4. Subject: �ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
        message.setSubject(mailTopic, "UTF-8");  
        
        //�ж��Ƿ��и���
      if(path==null) {
    	// 5. Content: �ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
    	  message.setContent(mailContent, "text/html;charset=UTF-8");
      }else {
    	//��Ӹ�������
          //�ʼ����ݲ���1---�ı�����
          MimeBodyPart body0 = new MimeBodyPart(); //�ʼ��е����ֲ���
          body0.setContent(mailContent,"text/html;charset=utf-8");
          
          //�ʼ����ݲ���2---����1
          MimeBodyPart body1 = new MimeBodyPart(); //����1
          body1.setDataHandler( new DataHandler( new FileDataSource(path)) );//./������Ŀ��Ŀ¼��
          String[] filePath=path.split("\\\\");//
          String fileName=filePath[filePath.length-1];
          body1.setFileName(MimeUtility.encodeText(fileName) );//���ĸ��������������
          
          //��������ʼ�����2�Ĵ���ʵ����Ӷ������

          //�������2������װ��һ�����õ�message��
          MimeMultipart mm = new MimeMultipart();
          mm.addBodyPart(body0);
          mm.addBodyPart(body1);
          message.setContent(mm);
      }
       
      // 6. ���÷���ʱ��
        message.setSentDate(new Date());
//        message.setFlag(Flags.Flag.DRAFT, true);
        // 7. ��������
        message.saveChanges();

        return message;
    }
}
