package ipInfo.copy;

// =============
// Class imports
// =============
   import gui.Warning;
   import java.awt.GridLayout;
   import javax.swing.JLabel;
   import javax.swing.JOptionPane;
   import javax.swing.JPanel;
   import javax.swing.JTextField;
   import Mail.InBoundMail;
   import Mail.SendMail;

public class IpPortDialog {
	
//  ===================
//  Method getIpandPort
//  ==================================	
	public static void getIpandPort(){
        
		int result;
		JPanel panel = new JPanel(new GridLayout(0, 1)); 
        JTextField ad  = new JTextField();
        JTextField outPort = new JTextField();
        JTextField inPort  = new JTextField();
        String add = "";
        String inPo = "";
        String outPo = "";
  
        panel.add(new JLabel("Server IP"));
        panel.add(ad);
        panel.add(new JLabel("Out Bound Port"));
        panel.add(outPort);
        panel.add(new JLabel("In Bound Port"));
        panel.add(inPort);
        result = JOptionPane.showConfirmDialog(null, panel, "Set IP and Port Info",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            
                 add   = ad.getText();
                 outPo = outPort.getText();
                 inPo  = inPort.getText();
                 
                 if(add.equals("") == true || outPo.equals("") == true || inPo.equals("") == true){
                	 
                	 Warning.portIpWarn(add, outPo, inPo);
                	 getIpandPort();
                	 
                 }
                 else{
                	 System.out.println(Integer.parseInt(inPo) + " " + add + " " + outPo);
                	 InBoundMail.port = Integer.parseInt(inPo);
                	 SendMail.address = add;
                	 SendMail.port = outPo;
                 }  
        }
        else{
        	
        	Warning.defaultAddPort();
        	InBoundMail.port = 9992;
       	    SendMail.address = "127.0.0.1";
       	    SendMail.port = "9993";	
        	
        }
    }

}
