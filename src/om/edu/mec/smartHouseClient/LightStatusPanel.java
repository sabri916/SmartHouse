package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class LightStatusPanel extends Panel{
	
	boolean isActive = false;

	Panel heading;
	Label fireStatusLabel;

	Panel imagePanel;
	BufferedImage img;

	Panel statusLight;

	Panel messagePanel;
	Label warningMessage;

	public LightStatusPanel(){

		setLayout(new GridLayout(4,1));

		//////heading//////////

		fireStatusLabel = new Label("Lights Status");
		fireStatusLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,21));
		fireStatusLabel.setAlignment(Label.CENTER);
		fireStatusLabel.setForeground(Color.YELLOW);

		/////image///////////////////

		try{
			img = ImageIO.read(new File("res/Zapdos.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}
		
		ImageFilter filter = new GrayFilter(true, 50);  
		ImageProducer producer = new FilteredImageSource(img.getSource(), filter);  
		Image gimg = Toolkit.getDefaultToolkit().createImage(producer);

		imagePanel = new Panel(){
			public void paint(Graphics g){

				if(isActive){
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
				int r = getHeight()-50;
				int x = getWidth()/2-r/2;
				int y = getHeight()/2-r/2;
				if(isActive){
					g.setColor(Color.YELLOW);
				}
				else{
					g.setColor(Color.GRAY);
				}

				g.fillOval(x,y,r,r);
			}

		};

		////Warning Message//////////////

		warningMessage = new Label("No Lights");
		warningMessage.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		warningMessage.setAlignment(Label.CENTER);
		warningMessage.setForeground(Color.GRAY);

		////Add everything
		add(fireStatusLabel);
		add(imagePanel);
		add(statusLight);
		add(warningMessage);
	}

	void on(){
		isActive = true;
		statusLight.repaint();
		imagePanel.repaint();
		warningMessage.setForeground(Color.YELLOW);
		warningMessage.setText("Lights!!!");
		validate();

	}

	void off(){
		isActive = false;
		statusLight.repaint();
		imagePanel.repaint();
		warningMessage.setForeground(Color.GRAY);
		warningMessage.setText("No Light");
		validate();
	}
}