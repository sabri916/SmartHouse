package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class FireStatusPanel extends Panel{
	// This extends a panel to create a highly cuztomized panel
	//This class is basically the colourful fancy bit of the frame in the middle
	
	private boolean isActive = false; // if this is true, then there is a fire

	//components for heading
	private Panel heading;			
	private Label fireStatusLabel;

	//components for the images
	private Panel imagePanel;
	private BufferedImage img;

	//Status light will be drawn on this panel
	private Panel statusLight;

	//componenets for the status message 
	private Panel messagePanel;
	private Label warningMessage;

	public FireStatusPanel(){

		//Layout of the Panel
		setLayout(new GridLayout(4,1));

		//////heading//////////

		fireStatusLabel = new Label("Fire Status");
		fireStatusLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,21));
		fireStatusLabel.setAlignment(Label.CENTER);
		fireStatusLabel.setForeground(Color.RED);

		/////image///////////////////

		//get Image from res folder
		try{
			img = ImageIO.read(getClass().getResource("/res/Moltres.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}
		
		//These three lines will produce a grayscale image.
		//gimg will hold the gray image, and imp will hold the coloured image
		ImageFilter filter = new GrayFilter(true, 50);  
		ImageProducer producer = new FilteredImageSource(img.getSource(), filter);  
		Image gimg = Toolkit.getDefaultToolkit().createImage(producer);

		//this overrides the paint method in the new panel such that it draws the image based on the size of the panel
		imagePanel = new Panel(){
			public void paint(Graphics g){

				if(isActive){
					//draws coloured image if active else, draw grayscale image
					g.drawImage(img,0,0,getWidth(),getHeight(),null);
				}
				else{
					g.drawImage(gimg,0,0,getWidth(),getHeight(),null);
				}
			}
		};

		/////Status Light/////////////
		statusLight = new Panel(){
			public void paint(Graphics g){
				//draws that circular status "light"
				int r = getHeight()-50; //radius
				int x = getWidth()/2-r/2; //x coordinate
				int y = getHeight()/2-r/2; //y coordinate
				if(isActive){
					//colour is set according to whether the panel is active or not
					g.setColor(Color.RED);
				}
				else{
					g.setColor(Color.GREEN);
				}

				g.fillOval(x,y,r,r);
			}

		};

		////Warning Message//////////////
		//the warning messages are the default values

		warningMessage = new Label("No Fire");
		warningMessage.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		warningMessage.setAlignment(Label.CENTER);
		warningMessage.setForeground(Color.GREEN);

		////Add everything
		add(fireStatusLabel);
		add(imagePanel);
		add(statusLight);
		add(warningMessage);
	}

	void on(){
		//turns the panel on or "Active" this is called if a fire is found
		isActive = true; //changes the state of the class
		statusLight.repaint(); //repaints the "Status light" to reflect the change
		imagePanel.repaint(); //repaints the image to reflect the change
		warningMessage.setForeground(Color.RED); //change colour of status message
		warningMessage.setText("Fire!!!");	//Change the status message string
	}

	void off(){
		//This is the opposite of the above method
		isActive = false;
		statusLight.repaint();
		imagePanel.repaint();
		warningMessage.setForeground(Color.GREEN);
		warningMessage.setText("No Fire");
	}
}