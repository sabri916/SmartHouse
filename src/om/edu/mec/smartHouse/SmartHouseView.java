package om.edu.mec.smartHouse;

import java.awt.*;

class SmartHouseView extends Frame{

	private Label fireLabel;
	private Label leakLabel;
	private Label lightLabel;

	private Button fireOn;
	private Button fireOff;
	private Button leakOn;
	private Button leakOff;
	private Button lightOn;
	private Button ligthtOff;

	private Panel statusPanel;
	private Panel buttonPanel;
	private Panel fireButtonPanel;
	private Panel leakButtonPanel;
	private Panel lightButtonPanel;

	private BorderLayout mainLayout;
	private GridLayout statusLayout;
	private FlowLayout fireButtonLayout;
	private FlowLayout leakButtonLayout;
	private FlowLayout lightButtonLayout;

	SmartHouseView(){
		System.out.println("View Constructor");
		//setSize and location and title
		setTitle("Smart House Server");
		setSize(650,500);
		setLocation(650,1100);

		//instantiate 
		fireLabel = new Label("No Fire");
		leakLabel = new Label("No Water Leak");
		lightLabel = new Label("Lights Off");

		fireOn = new Button("Fire On");
		fireOff = new Button("Fire Off");
		leakOn = new Button("Water Leak On");
		leakOff = new Button("Water Leak Off");
		lightOn = new Button("Lights On");
		ligthtOff = new Button("Lights Off");

		statusPanel = new Panel(new GridLayout(1,3,50,50));
		buttonPanel = new Panel(new GridLayout(1,3,50,50));
		fireButtonPanel = new Panel(new GridLayout(1,2));
		leakButtonPanel = new Panel(new GridLayout(1,2));
		lightButtonPanel = new Panel(new GridLayout(1,2));

		//set layout manager
		setLayout(new BorderLayout(10,10));

		//add Panels to Main Layout
		add(statusPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		//add status to layout
		statusPanel.add(fireLabel);
		statusPanel.add(leakLabel);
		statusPanel.add(lightLabel);

		//set Alignment of Label
		fireLabel.setAlignment(Label.CENTER);
		leakLabel.setAlignment(Label.CENTER);
		lightLabel.setAlignment(Label.CENTER);

		//add Button Layouts
		buttonPanel.add(fireButtonPanel);
		buttonPanel.add(leakButtonPanel);
		buttonPanel.add(lightButtonPanel);

		//add On/off Buttons
		fireButtonPanel.add(fireOn);
		fireButtonPanel.add(fireOff);
		leakButtonPanel.add(leakOn);
		leakButtonPanel.add(leakOff);
		lightButtonPanel.add(lightOn);
		lightButtonPanel.add(ligthtOff);

	}
}