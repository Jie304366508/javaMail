package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;

public class ReadMail {

	public static void main(String[] args) throws MessagingException, IOException {
		readMail();

	}

	private static void readMail() throws MessagingException, IOException {
		String host = "pop.163.com";
		String username = "17577288575@163.com";
		String password = "zj336600";
  
		// Create empty properties
		Properties props = new Properties();
  
		// Get session
		Session session = Session.getDefaultInstance(props, null);
  
		// Get the store
		Store store = session.getStore("pop3");
		store.connect(host, username, password);

		  
		// Get folder
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
  
		
		BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

		  
		// Get directory
		Message message[] = folder.getMessages();

		for (int i=0, n=message.length; i<n; i++) {

		   System.out.println(i + ": " + message[i].getFrom()[0]

		        + "/t" + message[i].getSubject());

		   System.out.println("Do you want to read message? " +

		        "[YES to read/QUIT to end]");

		   String line = reader.readLine();

		   if ("YES".equals(line)) {

//		      message[i].writeTo(System.out);
//			   System.out.println(message[].);

		   } else if ("QUIT".equals(line)) {

		      break;

		   }
		}
		 //Get directory
//		Message message[] = folder.getMessages();
//
//		for (int i=0, n=message.length; i<n; i++) {
//
//		   System.out.println(i + ": " + mimeDecodeString(message[i].getFrom()[0].toString()) + "/t" + message[i].getSubject());
//		}
//  
		// Close connection
		folder.close(false);
		store.close();
	
	}
	
	
	//解决用户名乱码问题
	public static String mimeDecodeString(String res) {

	    if(res != null) {

	        res = res.trim();

	        try {

	            if (res.startsWith("=?GB") || res.startsWith("=?gb")

	                    || res.startsWith("=?UTF") || res.startsWith("=?utf")) {

	                res = MimeUtility.decodeText(res);

	            }

	        } catch (Exception e) {

	            //LOGGER.error("Decode string error. Origin string is: " + res, e);

	        }

	        return res;

	    }

	    return null;
	}
	
	
}
