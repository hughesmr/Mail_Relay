package Mail;

// Class imports
// ===================
   import gui.RightFrame;
import gui.Warning;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Transport;

public class SendMail {
	
	public static String port;
	public static String address;

//  ==================
//  Method sendMessage
//  =============================================================================
	public void sendMessage(String to, String from, String body, String subject, File f){

	    Properties props = new Properties();     // Create new instance of property
	    props.put("mail.smtp.host", address);    // Set send IP
	    props.put("mail.smtp.port", port);       // Set send port
	    props.put("mail.smtp.auth", "false");    // Set auth to false


        /* Use below to connect to secure mail server such as gmail
         *
	     *props.put("mail.smtp.host", " smtp.gmail.com");  // Set IP
	     *props.put("mail.smtp.port", "465");              // Set port
	     *props.put("mail.smtp.auth", "true");
	     *props.put("mail.smtp.starttls.enable", "true");
	     *
	     *final String u = "";
	     *final String pw = "";
	     *
	     *props.put("mail.smtp.username", u);
	     *props.put("mail.smtp.password", pw);
		 *
	     *Session session = Session.getInstance(props,
	     *	  new javax.mail.Authenticator() {
	     *			protected PasswordAuthentication getPasswordAuthentication() {
	     *				return new PasswordAuthentication(u, pw);
	     *	        }
	  	 *     });
	     */
        Session session = Session.getInstance(props, null); // Create new session for message
        session.setDebug(false);                            // Enable Debugging

        try { // Try to send message

        	Address addFrom = new InternetAddress(from);      // Set address from
        	Address addTo = new InternetAddress(to);          // Set address to
            MimeMessage msg = new MimeMessage(session);       // Create new mime message

            msg.setFrom(addFrom);                             // Add who the mime message is from
            msg.setRecipient(Message.RecipientType.TO, addTo);// Add recipient to mime message

          
           // Following code used to set reply-to if needed
           
            msg.setReplyTo(new javax.mail.Address[]{
           
           		    new javax.mail.internet.InternetAddress(to)
           		});
           

            msg.setSubject(subject);     // Add subject to message
            msg.setSentDate(new Date()); // Add send date to message
            
            if(f == null){ // If attachment
                msg.setText(body);           // Add body to message
                Transport.send(msg);        // Try to send message
            
            }// End if attachment 
            else{ // Else there is an attachment
            	 
            	Multipart multipart = new MimeMultipart();    // Create new instance of MimeMultipart
            	BodyPart messageBodyPart = new MimeBodyPart();// Create new instance of BodyPart
            	messageBodyPart.setText(body);                // Add body to messageBody part
            	multipart.addBodyPart(messageBodyPart);       // Add body to message
            	messageBodyPart = new MimeBodyPart();         // Create new instance of MimeBodyPart
                DataSource source = new FileDataSource(f);    // Create new instance of DataSource
                messageBodyPart.setDataHandler(new DataHandler(source)); // Add data to BodyPart
                messageBodyPart.setFileName(f.getName());     // Set fileName of file
                multipart.addBodyPart(messageBodyPart);       // Add file to message
                msg.setContent(multipart);                    // Add content to message
                
                Transport.send(msg); // Send message
                
            } // End else there is an attachment
            Warning.messageSent();
            RightFrame.f = null;
            RightFrame.fName.setText("");
            
        } catch (MessagingException mex) {  // Catch message send exception
            Warning.messageFailed("send failed, exception: " + mex);
        } // End catch message send exception
	
	} // End method sendMessage
//  ===========================

} // End class sendMail
// ====================