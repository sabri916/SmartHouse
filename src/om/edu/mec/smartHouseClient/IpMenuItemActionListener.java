package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.text.ParseException;


class IpMenuItemActionListener implements ActionListener {

	private Panel panel;
	private Label label;
	private TextField ipField;
	private ClientConnectionManager myConnectionManager;

	IpMenuItemActionListener(ClientConnectionManager myConnectionManager){

		this.myConnectionManager = myConnectionManager;

		panel = new Panel();
		label = new Label("IP Address");

		ipField = new TextField(15);

		panel.add(label);
		panel.add(ipField);



	}
	
	public void actionPerformed(ActionEvent e){
		int response = JOptionPane.showConfirmDialog(null, panel,"Please Enter IP Address of Server", JOptionPane.OK_CANCEL_OPTION);

		if (response == JOptionPane.OK_OPTION){
			myConnectionManager.setServerAddress(ipField.getText());
			System.out.println(ipField.getText());
		}
		else if (response == JOptionPane.CANCEL_OPTION){
			System.out.println("you cancelled =(");
		}
	}
}