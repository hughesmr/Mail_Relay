package gui;

/*
 * This class builds the left side of the JFrame. Un-comment out code
 * if you want the app to be universal for Linux and Windows
*/

// =============
// Class imports
// =====================
   import javax.swing.*;

// import javax.swing.border.EtchedBorder;
// import javax.swing.event.*;
// import com.explodingpixels.macwidgets.IAppWidgetFactory;
   import com.explodingpixels.macwidgets.SourceList;
   import com.explodingpixels.macwidgets.SourceListCategory; 
   import com.explodingpixels.macwidgets.SourceListDarkColorScheme;
   import com.explodingpixels.macwidgets.SourceListItem;
   import com.explodingpixels.macwidgets.SourceListModel;
   import com.explodingpixels.macwidgets.SourceListSelectionListener;
   import java.awt.*;
// import javax.swing.event.ListSelectionListener;
   import Mail.InBoundMail;

public class LeftFrame extends JFrame{
	

	private static final long serialVersionUID = 1L; // Variable to hold serialVersion
//  private static JList list2;                      // Object for JList (Universal)
	private static int l = 0;                        // Int to increment when a new message is added to model
	private static SourceList list1;                 // Object for sourceList 

//  ==============	
//  Method getView
//  ===============================	
	public static JPanel getView(){
		
		JPanel list = new JPanel(); // Create a new JPanel 
		list.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK)); // Add border in between frames
		return createLayout(list); // Return left panel
		
	} // End getView
//  ================
	
//	===================
//  Method createLayout
//  ================================================	
	private static JPanel createLayout(JPanel list){
		 
//      JLabel l = new JLabel("Messages");              // Create new  instance of JLabel for messages (Universal)
//	    JScrollPane ne = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // Create new instance of JScrollPane  (Universal)
//		JPanel top = new JPanel();                      // Create new instance of JPanel       (Universal)
  
		list.setPreferredSize(new Dimension(300, 600)); // Set JPanel size
		list.setLayout(new BorderLayout());             // Set border layout
	
//      l.setHorizontalAlignment(SwingConstants.CENTER); // Center label in pane (Universal)
//		top.setPreferredSize(new Dimension(300, 40));    // Set size of top pane (Universal)
//		top.setLayout(new BorderLayout());               // Set layout of top pane (Universal)
//		top.add(l, BorderLayout.CENTER);                 // Add top pane to parent pane (Universal)
//      list2 = populate();                              // Populate received message list (Universal)
		
		list1 = populate();                               // Populate received message list
		list1.addSourceListSelectionListener(listener);   // Add a listener to message list for notification when it changes
//      list2.addListSelectionListener(listener);          // Add a listener to message list for notification when it changes (Universal)
//      ne.getViewport().add(list2, BorderLayout.CENTER);  // Add list to JScroll pane view port (Universal)	
//  	list1.addSourceListSelectionListener(listener);    // Add a listener to message list for notification when it changes (Universal)
//  	ne.getViewport().add(list1.getComponent(), BorderLayout.CENTER); // Add list to scroll pane (Universal)
//		JScrollBar sb = ne.getVerticalScrollBar(); // Get vertical scroll bar (Universal)
//      list.add(sb, BorderLayout.WEST);           // Add scroll bar to panel (Universal)
//      list.add(ne, BorderLayout.CENTER);         // Add scroll pane to panel (Universal)
		
		list.add(list1.getComponent(), BorderLayout.CENTER); // Add SourceList to JPanel

		return list; // Return new pane with scroll view and label
		
	} // createLayout
//  =================
	
//	===========================
//  Method populate (Universal)
//  ==================================
 /*   private static JList populate(){
		
		final DefaultListModel model = new DefaultListModel(); // Create new instance of DefaultListModel
		final JList list = new JList(model); // Create new instance of JList for message list

		final Thread updater = new Thread(){ // Create new thread to check for updates to message list

		    @Override
		    public void run() { // Run method for thread

		        while(true) { // While program still running
		        	
		        	if(InBoundMail.list.size() > l){ // If new entry in list
		        		
		        		if(l == 0){ // If first entry in list
		        			model.addElement((l+1) + ": Create your own message"); // Add list element
		        		} // End if first entry in list
		        		else{ // Else not first entry
		        		    model.addElement((l+1) + ": " + InBoundMail.list.get(l).getSubject()); // Add list element
		        		} // End else not first entry
		        	     l++; // Increment message number
		        	}// End if new entry in list
		            
		            try { // Try to sleep
		                Thread.sleep(900);
		            } catch (InterruptedException e) { // Catch sleep error
		                throw new RuntimeException(e);
		            } // End catch sleep error
		        } // While program still running
		    } // End method run
		}; // End new thread for list changes
		updater.start(); // Start new thread
		return list;	 // Return message list

	} // End method populate
//  ======================== */

//	===============
//  Method populate 
//  =====================================
    private static SourceList populate(){
    
    final SourceListModel model = new SourceListModel(); // Create new instance of SourceListModel
    final SourceListCategory sec = new SourceListCategory("Messages"); // Create new instance of SourceListCategory
    SourceList sourceList = new SourceList(model); // Create new instance of SourceList
    
    sourceList.setColorScheme(new SourceListDarkColorScheme()); // Select Dark color scheme
    model.addCategory(sec); // Add category to sourcelist
  
    final Thread updater = new Thread(){ // Create new thread to check for updates to message list

	    @Override
	    public void run() { // Run method for thread
	    	
	        while(true) { // While program still running
	        	
	        	if(InBoundMail.list.size() > l){ // If new entry in list
	        		
	        		if(l == 0){ // If first entry in list
	        			SourceListItem n = new SourceListItem("Create your own message");
	        			n.setIcon(getIcon(l));
	        			model.addItemToCategory(n, sec);
	        			
	        			//model.addElement((l+1) + ": Create your own message"); // Add list element
	        		} // End if first entry in list
	        		else{ // Else not first entry
	        		   // model.addElement((l+1) + ": " + InBoundMail.list.get(l).getSubject()); // Add list element
	        			SourceListItem n = new SourceListItem(InBoundMail.list.get(l).getSubject());
	        			n.setIcon(getIcon(l));
	        			model.addItemToCategory(n, sec);
	        		} // End else not first entry
	        	     l++; // Increment message number
	        	}// End if new entry in list
	            
	            try { // Try to sleep
	                Thread.sleep(900);
	            } catch (InterruptedException e) { // Catch sleep error
	                throw new RuntimeException(e);
	            } // End catch sleep error
	        } // While program still running
	    } // End method run
	}; // End new thread for list changes
	updater.start(); // Start new thread
  
    return sourceList;
    
    }// End method populate
//  ========================
    
//  =====================
//  Method listener (Mac)
//  ========================================================================================
	private static SourceListSelectionListener listener = new SourceListSelectionListener(){   // Create a new instance of ListSelectionListener
		
		@Override
		public void sourceListItemSelected(SourceListItem arg0) {
      
			 int i = 0;  			// Variable to increment to find index
			 int found = 0;         // Variable to hold found value for index 0
        	 SourceListItem index;  // Object to hold index of selection

             index = list1.getSelectedItem(); // Get index of select value
             
             while(index.getText().equals(InBoundMail.list.get(i).getSubject()) // While index not found
            		 != true && i < InBoundMail.list.size() && found == 0){
            	 if(i == 0){ // If index 0
            		 if(index.getText().equals("Create your own message") != true){ // If index 0 does not match
            			 i++; // Increment for next index
            		 } // End if index 0 does not match
            		 else{ // Else index 0 matches
            			 found = 1; // Index found if initial 
            		 } // End else index matches
            	 } // End if index 0
            	 else{ // Else index not 0 
            	     i++; // Increment for next index
            	 } // End else index not 0
             } // End while index not found
             
             MailInterface.to.setText(InBoundMail.list.get(i).getReply());       // Get reply-to at index
             MailInterface.from.setText(InBoundMail.list.get(i).getFrom());      // Get from at index
             MailInterface.subject.setText(InBoundMail.list.get(i).getSubject());// Get subject at index
             MailInterface.mPane.setText(InBoundMail.list.get(i).getBody());// Get subject at body
             
             if(i == 0){
            	 MailInterface.mPane.setEnabled(false);
            	 MailInterface.mPane.setBackground(new Color(220,220,220));
             }
             else{
            	 MailInterface.mPane.setEnabled(true);
            	 MailInterface.mPane.setBackground(new Color(255,255,255));
             }
            
		}
    };	// End method listener
    
//  ============== 
//  Method getIcon
//  =====================================    
    private static Icon getIcon(int num){
 	   
    	
 	   ImageIcon icon1 = new ImageIcon(LeftFrame.class.getResource("mail1.png")); // Create new icon object
 	   ImageIcon icon2 = new ImageIcon(LeftFrame.class.getResource("mail2.png"));
 	   ImageIcon icon3 = new ImageIcon(LeftFrame.class.getResource("mail3.png"));
 	   ImageIcon icon4 = new ImageIcon(LeftFrame.class.getResource("mail4.png"));
 	   ImageIcon icon5 = new ImageIcon(LeftFrame.class.getResource("mail5.png"));
 	   
 	   num = num % 5; // Get selection mod 5 to select icon color
 	   
 	   if(num == 0){ // If num = 0
 		  return icon1;
 	   }// End if num = 0
 	   else if(num == 1){// Else if num = 1
 		  return icon2;
 	   }// End else if num = 1
 	   else if(num == 2){// Else if num = 2
 		  return icon3;
 	   }// End else if num = 2
 	   else if(num == 3){// Else if num = 3
 		  return icon4;
 	   }// End else if num = 3
 	   else{// Else num = 4
 		   return icon5;
 	   } // End else num = 4
 	   
    } // End getIcon
//  ================
	
} // End class LeftFrame
// =====================
  
//  ===========================
//  Method listener (Universal)
//  ============================================================================
/*	private static ListSelectionListener listener = new ListSelectionListener(){   // Create a new instance of ListSelectionListener
		
        @Override
        public void valueChanged(ListSelectionEvent evt){

        	 int index;  // Variable to hold index of selection

             index = list2.getSelectedIndex(); // Get index of select value
             MailInterface.to.setText(InBoundMail.list.get(index).getReply()); // Get reply-to at index
             MailInterface.from.setText(InBoundMail.list.get(index).getFrom());// Get from at index
             MailInterface.subject.setText(InBoundMail.list.get(index).getSubject());// Get subject at index
            
        } // End value changed
    };	// End method listener
	
} // End class LeftFrame */
// =====================
