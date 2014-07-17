package gui;

// =============
// Class imports
// ===============
   import java.awt.*;
   import java.awt.event.*;
   import java.io.File;
   import javax.swing.*;
   import Mail.SendMail;
   import gui.Warning;

public class RightFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextArea editorPane; // Object for editor pane
	static JFileChooser c;       // Object for file chooser
	public static JLabel fName;  // Label to display attached file
	public static File f;        // File object for attachment
	
//  ================	
//  Method getLayout
//  ============================================	
	static public JPanel getView(){
	
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		c = new JFileChooser(); // Create new instance of fileChooser
		f = null;               // Set file object f to null
		fName = new JLabel(" ");
        pane.add(createAddressData(), BorderLayout.NORTH); // Add address info	
		pane.add(mbcb(), BorderLayout.CENTER);       // Add body of the message
		pane.add(sendPane(), BorderLayout.SOUTH);          // End send panel
		
		return pane; // Return right pane
		
	} // End getLayout
//  ==================	
	
//  ===========
//  Method mbcb
//  =============================	
	private static JPanel mbcb(){
		
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.add(createBody(), BorderLayout.NORTH); // Add address info	
		pane.add(messageBody(), BorderLayout.CENTER);          // End send panel
		
		return pane;
		
	} // End mbcb
//  =============	
	
//	===============
//  Method sendPane
//	=================================
	private static JPanel sendPane(){
		
		JPanel lo = new JPanel(null); // Create new instance of JPanel
	    lo.setBackground(new Color(50,205,50)); // Set background of lower left pane
	    lo.setPreferredSize(new Dimension(500, 50)); // Set Panel size
		
		final JButton send = new JButton("Send"); // Create new button
		
		send.addActionListener(new ActionListener() { // Create actionlistener for button
			  public void actionPerformed(ActionEvent e) { 

			      sendMessage(); // Method to send message  
			  } 
		} ); // End actionlistener for button
		
		lo.add(send); // Add button to lower panel
		
        Insets insets = lo.getInsets(); // Create insets
		Dimension size = send.getPreferredSize(); // Get size of button
		send.setBounds(200 + insets.left, 5 + insets.top, size.width, size.height); // Set position

		return lo; // Return lower panel
		
	} // End sendPane
//  =================	
	
//  ====================
//	Method getAttachment
//  ====================================	
	private static void getAttachment(){
		  
		String fname; // String for file name
		String path;  // String for file path
		JComboBox jb = new JComboBox(); // Create a new instance of JComboBox
		FileDialog fd= new FileDialog(new JFrame(),(String)jb.getSelectedItem()); // Create a new instance of FileDialog
		
		fd.setVisible(true); // Display FileDialog

		path = fd.getDirectory(); // Get path to file
		fname = fd.getFile();     // Get file name
		 
		if(fd.getFile() != null){ // If file selected
			f = new File(path+fname); // Set f for new instance of file
			fName.setText(fname);
		} // End if file selected
		 
	}// End getAttachment
//  =====================	
	
//  ==================	
//  Method sendMessage 	
//	==================================
	private static void sendMessage(){
         
		SendMail s = new SendMail(); // Create new instance of sendMail
		
		String to = MailInterface.to.getText();          // String for to address
		String from = MailInterface.from.getText();      // String for from address
		String subject = MailInterface.subject.getText();// String for subject

		 if(to.equals("") || from.equals("") || subject.equals("")){ // If don't send message
			
			Warning.addressWarn(to, from, subject); // Call message address warning
			
		 } // End if don't send message
		 else{// Else message okay to send
			 
		    s.sendMessage(to, from, editorPane.getText(), subject, f);// Send the message
			 
		 } // End else message okay to send
		 
	} // End sendMessage
//  ====================
	
//  =================
//  Method createBody	
//	===================================
    private static JPanel createBody(){
		
		JPanel back = new JPanel();             // Create new instance of JPanel
		JPanel fill = new JPanel();
		Font font = new Font("Verdana", Font.BOLD, 12);
		JLabel m = new JLabel("Message to Send: ");           // Create new JLabel for To
		m.setFont(font);
		back.setLayout(new BorderLayout());     // Set layout style BorderLayout
		back.setPreferredSize(new Dimension(450, 200));     // Set size of JPanel
		editorPane = new JTextArea();                       // Create new JTextArea
		JScrollPane textField = new JScrollPane(editorPane);// Create new JScrollPane
		back.add(m, BorderLayout.NORTH); // Add and place west filler JPanel
		editorPane.setLineWrap(true);                   // Enable line wrap
		editorPane.setWrapStyleWord(true);              // Enable word wrap
		editorPane.getScrollableTracksViewportWidth();  // Force width of scrollable to match width of viewable
        
        textField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   // Always vertical scroll
        textField.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// Never horizontal scroll
                                             //400 X400
        textField.setPreferredSize(new Dimension(200, 140)); // Set size of scroll area
        textField.setMinimumSize(new Dimension(200, 140));   // Set the min size of scroll area

        back.add(textField, BorderLayout.CENTER);  // Add and center textField 
      
        fill.setBackground(new Color(50,205,50)); // Set background color of filler space
        back.add(fill, BorderLayout.WEST); // Add and place west filler JPanel
        back.setBackground(new Color(50,205,50));
        
		return back;
	} // End createBody
//  ===================
    
//  ==================
//  Method messageBody	
//	====================================    
    private static JPanel messageBody(){
    	
    	JPanel body = new JPanel();             // Create new instance of JPanel
		JPanel fill = new JPanel();
		Font font = new Font("Verdana", Font.BOLD, 12);
		JLabel m = new JLabel("Message: ");           // Create new JLabel for To
		
		m.setFont(font);
		
		body.setLayout(new BorderLayout());     // Set layout style BorderLayout
		body.setPreferredSize(new Dimension(450, 200));     // Set size of JPanel
		                
		JScrollPane textField = new JScrollPane(MailInterface.mPane);// Create new JScrollPane
		body.add(m, BorderLayout.NORTH); // Add and place west filler JPanel
		MailInterface.mPane.setLineWrap(true);                   // Enable line wrap
		MailInterface.mPane.setWrapStyleWord(true);              // Enable word wrap
		MailInterface.mPane.getScrollableTracksViewportWidth();  // Force width of scrollable to match width of viewable
        
        textField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   // Always vertical scroll
        textField.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// Never horizontal scroll
                                             
        textField.setPreferredSize(new Dimension(200, 140)); // Set size of scroll area
        textField.setMinimumSize(new Dimension(200, 140));   // Set the min size of scroll area

        body.add(textField, BorderLayout.CENTER);  // Add and center textField 
      
        fill.setBackground(new Color(50,205,50)); // Set background color of filler space
       
        body.add(fill, BorderLayout.WEST); // Add and place west filler JPanel
        body.setBackground(new Color(50,205,50));
         
		return body;
    	
    }// End messageBody
//  ===================	
    
//  ========================	
//  Method createAddressData	
//  ==========================================	
	private static JPanel createAddressData(){
		
		JPanel t = new JPanel(null);              // Create new JPanel
		JLabel to = new JLabel("To: ");           // Create new JLabel for To
		JLabel from = new JLabel("From: ");       // Create new JLabel for From
		JLabel subject = new JLabel("Subject: "); // Create new JLabel for Subject
		final JButton att = new JButton("Attach"); // Create new button
		
		
		Font font = new Font("Verdana", Font.BOLD, 12);
		Font font1 = new Font("Verdana", Font.PLAIN, 12);
		to.setFont(font);
		from.setFont(font);
		subject.setFont(font);
		fName.setFont(font1);
		t.setPreferredSize(new Dimension(500, 120)); // Set size of JPanel
		t.setBackground(new Color(50,205,50));
		att.addActionListener(new ActionListener() { // Create actionlistener for button
			  public void actionPerformed(ActionEvent e) { 

				   getAttachment();  
			  } 
		} ); // End actionlistener for button
		
		t.add(to);                   // Add to label
		t.add(MailInterface.to);     // Get static to from MailInterface
		t.add(from);                 // Add from label
		t.add(MailInterface.from);   // Get static from from MailInterface
		t.add(subject);              // Add subject label
		t.add(MailInterface.subject);// Get static subject from MailInterface
		t.add(att);                  // Add attachment button
		t.add(fName);                // Add fileName to panel
		
		Insets insets = t.getInsets();// Create new insets
		
		Dimension size = to.getPreferredSize(); // Get size of label
		to.setBounds(25 + insets.left, 10 + insets.top, size.width, size.height); // Position to label
		size = MailInterface.to.getPreferredSize(); // Get size of address
		MailInterface.to.setBounds(80 + insets.left, 5 + insets.top, size.width + 120, size.height); // Position to address
		
		size = from.getPreferredSize(); // Get size of label
		from.setBounds(25 + insets.left, 35 + insets.top, size.width, size.height); // Position from label
		size = MailInterface.from.getPreferredSize(); // Get size of address 
		MailInterface.from.setBounds(80 + insets.left, 30 + insets.top, size.width + 120, size.height); // Position from address
		
		size = subject.getPreferredSize(); // Get size of label
		subject.setBounds(25 + insets.left, 60 + insets.top, size.width, size.height); // Position subject label
		size = MailInterface.subject.getPreferredSize(); // Get size of subject
		MailInterface.subject.setBounds(80 + insets.left, 55 + insets.top, size.width + 120, size.height); // Position subject address
		
		size = att.getPreferredSize(); // Get size of attach button
		att.setBounds(19 + insets.left, 82 + insets.top, size.width, size.height); // Set position of attach button
		size = fName.getPreferredSize(); // Get size of file name
		fName.setBounds(110 + insets.left, 87 + insets.top, size.width + 500, size.height); // Set position of file name
	
		return t; // return address panel
		
	} // End createAddressData
//  ==========================	

} // End class right frame
// =======================
