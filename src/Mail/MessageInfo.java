package Mail;

public class MessageInfo {

     private String from;    // String to hold from address
     private String subject; // String to hold subject
     private String reply;   // String to hold reply to address
     private String body;   // String to hold reply to body


//   ==============
//   Method setFrom
//   =================================
     public void setFrom(String from){
    	 
    	 this.from = from;
     }// End method setFrom
//   ======================

//   =================
//   Method setSubject
//   =======================================      
     public void setSubject(String subject){
	 
	     this.subject = subject;
     }// End method setSbuject
//   =========================

//   ===============
//   Method setReply
//   ===================================     
     public void setReply(String reply){
    	 
	     this.reply = reply;
     }// End method setReply
//   =======================

//   ==============
//   Method setFrom
//   =================================
     public void setBody(String body){
    	 
    	 this.body = body;
     }// End method setFrom
//   ======================
     
//   ==============
//   Method getFrom
//   ========================     
     public String getFrom(){
    	 
    	 return from;
     } // End method getFrom
//   =======================

//   =================
//   Method getSubject
//   ========================= 
     public String getSubject(){
	 
	      return subject;
     }// End method getSubject
//   =========================
     
//   ===============
//   Method getReply
//   =========================     
     public String getReply(){
    	 
	      return reply;
    }// End method getReply
//  =======================

//   ===============
//   Method getReply
//   =========================     
     public String getBody(){
    	 
	      return body;
    }// End method getReply
//  =======================
     
} // End class MessageInfo
