package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;

public class FireStatusPanel extends Panel{
	
	Panel heading;
	Label fireStatusLabel;

	Panel image;
	BufferedImage img;

	Panel statusLight;

	public FireStatusPanel(){
		setLayout(new GridLayout(3,1));

		//////heading//////////

		fireStatusLabel = new Label("Fire Status");

		/////image/////////////

		try{
			img = ImageIO.read(new File("Moltres.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}

		image = new Panel(){
			public void paint(Graphics g){
				Dimension panelSize = getSize();
				g.drawImage(img,0,0,getWidth(),getHeight(),null);
			}
		};

		statusLight = new Panel();

		add(fireStatusLabel);
		add(image);
		add(statusLight);
	}
}