package gui;

// =============
// Class Imports
// =============
   import javax.swing.JOptionPane;

public class Warning {
	
//  ==================
//  Method addressWarn
//  =======================================================================	
	public static void addressWarn(String to, String from, String subject){
		
		String warn = ""; // String for warning
		
		if(to.equals("")){ // If to missing
			
			warn = warn + "To";
			
		} // End if to missing
		if(from.equals("")){ // If from missing
			
			if(warn.length() == 2){// If other message components missing
				warn = warn + ", ";
			}// End if other message components missing
			
			warn = warn + "From";
		} // End if from missing
		if(subject.equals("")){ // If subject missing
			
			if(warn.length() >= 2){ // If other message components missing
				warn = warn + " and ";
			}// End if other message components missing
			
			warn = warn + "Subject";
		} // End if subject missing
		
		warn = warn + " needed to send the email!"; // Finish warning 
		JOptionPane.showMessageDialog(null, warn, "Message Missing Stuff... Yes Stuff", 
				JOptionPane.WARNING_MESSAGE); // Display warning 
		
	} // End addressWarn
//  ====================	

//  ==================
//  Method messageSent
//  =================================	
	public static void messageSent(){
		
		JOptionPane.showMessageDialog(null, "Message Sent", "Message Hopefully Sent..", 
				JOptionPane.INFORMATION_MESSAGE); // Display warning 
		
	} // End messageSent
//  ====================	

//  ==================
//  Method messageSent
//  ===================================	
	public static void messageFailed(String fail){
		
		JOptionPane.showMessageDialog(null, fail, "Message Failed... Your Fault Not Mine", 
				JOptionPane.WARNING_MESSAGE); // Display warning 
		
	} // End messageSent
//  ====================
	
//  ====================
//  Method addressFormat
//  =======================================================	
	public static void addressFormat(boolean t, boolean f){
		
		String msg = ""; // String for message
		
		if(t == false){ // If to formatted wrong
			msg = "To";
		}// End if to formatted wrong
		
		if(f == false){ // If from formatted wrong
			if(t == false){// If to formatted wrong
				msg = msg + " and ";
			}// End if to formatted wrong
			msg = msg + "From";
			
		}// End if from formatted wrong
		msg = msg + " Address Formatted Incorrectly";
		
		JOptionPane.showMessageDialog(null, msg, "Probably Want a Valid Email Address", 
				JOptionPane.WARNING_MESSAGE); // Display warning 
		
	} // End addressFormat
//  ======================
	
//  =================
//  Method portIpWarn
//  ========================================================================	
	public static void portIpWarn(String add, String toPort, String inPort){
		
		String warn = "Please enter"; // String for warning
		
		if(add.equals("")){ // If add missing
			
			warn = warn + " address";
			
		} // End if add missing
		if(toPort.equals("")){ // If toPort missing
			
			if(warn.length() >= 15){//  If ip missing
				warn = warn + ",";
			}// End if ip missing
			
			warn = warn + " out bound port";
		} // End if toPort missing
		if(inPort.equals("")){ // If in port missing
			
			if(warn.length() >= 21){ // If other message components missing
				warn = warn + " and";
			}// End if other message components missing
			
			warn = warn + " in bound port";
		} // End if in port missing
		warn = warn + "!";
		JOptionPane.showMessageDialog(null, warn, "Probably Want Stuff Set Up Right", 
				JOptionPane.WARNING_MESSAGE); // Display warning 
		
	} // End addressWarn
//  ====================
	
//  =====================
//  Method defaultAddPort
//  ====================================	
	public static void defaultAddPort(){
		
		JOptionPane.showMessageDialog(null, "Defaulting to port 9992 inbound, outbound port 9993" +
		" and address 127.0.0.1", "I Guess I Will Set Stuff For You", 
		         JOptionPane.INFORMATION_MESSAGE); // Display warning 
		
	} // End defaultAddPort
//  =======================
	
} // End class Warning
// ===================