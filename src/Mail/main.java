package Mail;


import ipInfo.copy.IpPortDialog;
import gui.MailInterface;

class main {
	
	public static void main(String[] args) throws InterruptedException{
		
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Mail Relay");
		IpPortDialog.getIpandPort();
		InBoundMail a = new InBoundMail();
		a.start();

		MailInterface n = new MailInterface();
		n.setVisible(true);
        
		a.join();

	}

}
