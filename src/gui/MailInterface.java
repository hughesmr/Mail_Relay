package gui;

// =============
// Class Imports
// ==================
   import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class MailInterface extends JFrame{

	private static final long serialVersionUID = 1L; // SerialVersionUID version
	private static final int height = 600;           // Set default height of JFrame
	private static final int width  = 800;           // Set default width of JFrame
	//public static JLabel to, from, subject;          // Create static values for to address, from address and subject

	public static JTextField to, from, subject;      // Create static objects for to address, from address and subject
	public static JTextArea mPane; // Object for editor pane
		
//  =========================
//	Constructor MailInterface
//	=========================
	public MailInterface(){
		
		super("Mail Relay"); // Call super class constructor set window title
		
	
		
		initializeLabels();                // Initialize the labels
		setSize(width, height);            // Set size of JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Set close operation
		setLayout(new BorderLayout());                 // Set Layout of JFrame
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				LeftFrame.getView(), RightFrame.getView()); // Create new instance of split pane
		
		splitPane.setOneTouchExpandable(false);        // Remove arrows from splitPane
		splitPane.setDividerLocation(250);             // Set initial divide location
		flattenSplitPane(splitPane);                   // Give split pane flat look
		splitPane.setBackground(new Color(50,205,50)); // Set color of split
		add(splitPane); // Add split pane to JFrame

	} // End MailInterface Constructor
//  ==================================	
	
//  =======================
//  Method flattenSplitPane 
//  =============================================================	
	public static void flattenSplitPane(JSplitPane jSplitPane) {
        jSplitPane.setUI(new BasicSplitPaneUI() { // Set UI type of splitpane
            public BasicSplitPaneDivider createDefaultDivider() { 
                return new BasicSplitPaneDivider(this) {
                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void setBorder(Border b) {
                    } // End setBorder
                }; // End BasicSplitPaneDivider
            } // End createDefaultDivider
        }); // End setUI 
        jSplitPane.setBorder(null); // Set border to null, do not want one
    } // End flattenSplitPane
//  =========================	
	
//  =======================
//  Method initializeLabels
//  ================================
    private void initializeLabels(){
    	
    	to = new JTextField("", 20);
    	from = new JTextField("", 20);    // Set defualt value for from
    	subject = new JTextField("", 20); // Set defualt subject
    	mPane = new JTextArea();
    	mPane.setEditable(false);
    	mPane.setEnabled(false);
    	mPane.setBackground(new Color(220,220,220));
    	
    } // End initializeLabels
//  =========================

}// End MailInterface
// ==================