package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.text.ParseException;


class IpMenuItemActionListener implements ActionListener {
	//This actionListener is fired when "set IP" Button is pressed
	//or when the set IP is selected from the Options menu
	//its job is to show a dialog box which will ask the user for the input 

	//components needed to build the dialogbox
	private Panel panel;
	private Label label;
	private TextField ipField;

	//ClientConnectionManager is needed since it holds the IP Address
	private ClientConnectionManager myConnectionManager;

	IpMenuItemActionListener(ClientConnectionManager myConnectionManager){
		//construction of dialog box

		this.myConnectionManager = myConnectionManager;

		panel = new Panel();
		label = new Label("IP Address");

		ipField = new TextField(15);

		panel.add(label);
		panel.add(ipField);



	}
	
	public void actionPerformed(ActionEvent e){
		//shows the dialog box with the "panel" embeded into it
		int response = JOptionPane.showConfirmDialog(null, panel,"Please Enter IP Address of Server", JOptionPane.OK_CANCEL_OPTION /*This makes it an OK- Cancel dialog box*/);

		//if OK is pressed, changed the ip in the connection manager.
		//change in the connection manager will automatically be reflectd in the View
		//if Cancel is pressed, do nothing.
		if (response == JOptionPane.OK_OPTION){
			myConnectionManager.setServerAddress(ipField.getText());
			System.out.println(ipField.getText());
		}
		else if (response == JOptionPane.CANCEL_OPTION){
			System.out.println("you cancelled =(");
		}
	}
}