package Mail;

// =============
// Class imports
// =================
   import java.io.*;
   import java.net.*;
   import java.util.ArrayList;

import com.sun.mail.util.ASCIIUtility;

public class InBoundMail extends Thread{
	
	public static ArrayList<MessageInfo> list = new ArrayList<MessageInfo>(); // Create new instance of arrayList for messages
	private ServerSocket serverSocket;           // Object for server socket
	public static int port;                      // Variable for port
//  ==========
//  Method run
//  ================================	
	@SuppressWarnings("deprecation")
	public void run(){
   
    	Socket server = null;       // Object for server
    	DataOutputStream out = null;// Object for output stream
    	DataInputStream in = null;  // Object for input stream
    	
    	MessageInfo def = new MessageInfo(); // Create instance of Message info for default message
    	def.setFrom("");    // Set default from
    	def.setReply("");   // Set default Reply-To
    	def.setSubject(""); // Set default subject
    	list.add(def);      // Add MessageInfo to list
    
    	try { // Try to bind to socket
			serverSocket = new ServerSocket(port); // Start server on port specified
		} catch (IOException e) { // Catch socket bind error
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // End catch socket bind error
    	
    	while(true){ // While listening to incoming mail

		    try { // Try to accept incoming connection 
			    server = serverSocket.accept();
		    } catch (IOException e1) {
			// TODO Auto-generated catch block
			    e1.printStackTrace();
		    } // Listen for connection and accept
    	
    	    try { // Try to create stream objects
			in = new DataInputStream(server.getInputStream());   // Get inputStream object
			out = new DataOutputStream(server.getOutputStream());// Get outputStream object
		    } catch (IOException e) { // Catch stream object error
			    // TODO Auto-generated catch block
			    e.printStackTrace();
		    } // End catch stream object error
    	
    	    try { // Try io read from socket
    		
    		    String from; // String for from address
    		    
    		    /*Not a lot of error checking below, add later */
		        out.write(ASCIIUtility.getBytes("220 OK\n"));
		        in.readLine();
		        out.write(ASCIIUtility.getBytes("250\n"));
			    in.readLine();
		        out.write(ASCIIUtility.getBytes("250\n"));
		        
		        from = in.readLine(); // Get from Address
		    
		        from = from.replaceAll("RCPT TO:", "");
		        from = from.replaceAll("<", "");
		        from = from.replaceAll(">", "");
		    
		        out.write(ASCIIUtility.getBytes("250\n"));
		        in.readLine();
		    
		        out.write(ASCIIUtility.getBytes("354 Ok Send data ending with <CRLF>.<CRLF>\n"));
		        
		        MessageInfo m = new MessageInfo(); // Create new instance of message info
		        
		        m.setFrom(from); // Set from address
		        messageBody(in, out, m); // Call message body to parse out reply-to
		  
		        in.readLine();
		        out.write(ASCIIUtility.getBytes("221 localhost  \n"));
		        
		        list.add(m); // Add message to list
		    
		    } catch (IOException e) {// Catch read exception
			    // TODO Auto-generated catch block
			    e.printStackTrace();
	   	    }// End catch read exception
    
            try { // Try to close socket
				server.close();
			} catch (IOException e) { // Catch socket close exception
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// End catch socket close exception
    	}      
    } // End method run
//  ===================	
	
//  ==================
//  Method messageBody
//  ===================================================================	
	@SuppressWarnings("deprecation")
	private void messageBody(DataInputStream in, DataOutputStream out, MessageInfo m){
		
		String cmd = ""; // String for incoming data
		String body = "";
		int i = 0;
		
	    try { // Try reading from socket
			cmd = in.readLine(); // Read first line
			
		    while(cmd.equals(".") == false){ // While message body to read
		    	cmd = in.readLine(); // Read lines
		    	
		    	
		    	if(i == 1){ // If 1 grab message body
                
                    body = body + cmd + " \n";
                } // End if 1 grab message body
		    	
		    	if(cmd.length() > 6){ // If string length > 6
		    	    if (cmd.substring(0,7).compareTo("Subject") == 0){ // If string subject
		    		    String subject = cmd.replaceAll("Subject: ", ""); // Remove "Subject"
		    		    subject = "RE: " + subject;
		    		    m.setSubject(subject); // Set subject
		    		    
		    	    } // End if string subject
                } // End if string length > 6
                if(cmd.length() > 7){ // If string length > 7
		    	     if(cmd.substring(0,8).compareTo("Reply-To") == 0){ // If string might be reply to
		    	    	String reply = cmd.replaceAll("Reply-To: ", ""); // Remove "Reply-To"
		    	    	
		    	    	m.setReply(reply); // Set reply to address 
		    	    	i = 1;
		    	    } // End if string might be reply to
		    	} // End if string length > 7
                
                
		    	
		    } // End while message body to read
		    m.setBody(body);
		    out.write(ASCIIUtility.getBytes("250\n")); // Send 250 OK for message body
		   
		} catch (IOException e) { // Catch read exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// End catch read exception
	    
	} // End messageBody
//  ====================	

}// End class inBoundMail
// ======================
