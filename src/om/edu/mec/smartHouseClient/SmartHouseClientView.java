package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;


import om.edu.mec.smartHouseCommon.SmartHouseModel;

class SmartHouseClientView extends Frame implements Observer{
	//Class extends a frame to create a window
	//Class implements an Observer to "Observe" the Model and ConnectionManager
	//Changes in the Connection Manager and Model needs to be refelceted onto the View (This class)

	//Model and connection manger declared
	private SmartHouseModel myModel;
	private ClientConnectionManager myConnectionManager;

	//All componenets declared
	private MenuBar myMenuBar;
	private Menu myMenu;
	private MenuItem ipMenuItem;

	private Label ipLabel;

	private Button setIpButton;
	private Button refreshFireButton;
	private Button lightOnButton;
	private Button lightOffButton;

	private Panel topButtonsPanel; //Panel to hold Set IP button
	private Panel statusPanel;	//Panel to hold all the awesome graphics in the middle of the window
	private FireStatusPanel fireStatusPanel; //These are customized panels in other files
	private LeakStatusPanel leakStatusPanel;
	private LightStatusPanel lightStatusPanel;
	private Panel bottomContainerPanel; //Panel to hold everything in the SOUTH protion of the window
	private Panel buttonContainerPanel;	//Panel to hold all the buttons in the bottom
	private Panel refreshButtonPanel; //Panel to hold refresh button
	private Panel lightButtonPanel; //Panel to hold Light On and Light off buttons
	private Panel ipStatusBarPanel; //Panel to hold status bar at the bottom

	SmartHouseClientView(SmartHouseModel myModel,ClientConnectionManager myConnectionManager){
		//model and connection manager referenced passed from the main class
		this.myModel = myModel;
		this.myConnectionManager = myConnectionManager;
		//setSize and location and title
		setTitle("Smart House Client");
		setSize(700,700);
		setLocation(1200,900);
		addWindowListener(new WindowAdapter(){
			//Programming the window to close when the cross button is pressed
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		//instantiate 
		myMenuBar = new MenuBar();
		myMenu = new Menu("options");
		ipMenuItem = new MenuItem("Set Server IP");

		ipLabel = new Label("IP Address: "+ myConnectionManager.getServerAddress());
		ipLabel.setAlignment(Label.LEFT);

		setIpButton = new Button("Set IP");
		refreshFireButton = new Button("Refresh");
		lightOnButton = new Button("Lights On");
		lightOffButton = new Button("Lights Off");

		//Panels are instantiated with the Layouts embeded
		topButtonsPanel = new Panel(new FlowLayout(FlowLayout.LEFT,15,0));
		statusPanel = new Panel(new GridLayout(1,3,50,50));
		fireStatusPanel = new FireStatusPanel();
		leakStatusPanel = new LeakStatusPanel();
		lightStatusPanel = new LightStatusPanel();
		bottomContainerPanel = new Panel(new GridLayout(2,1));
		buttonContainerPanel = new Panel(new GridLayout(1,2));
		refreshButtonPanel = new Panel(new FlowLayout(FlowLayout.LEFT,15,0));
		lightButtonPanel = new Panel(new FlowLayout(FlowLayout.RIGHT,15,0));
		ipStatusBarPanel = new Panel(new FlowLayout(FlowLayout.LEFT,15,10));

		//Set Menu
		myMenu.add(ipMenuItem);
		myMenuBar.add(myMenu);
		setMenuBar(myMenuBar);

		//set layout manager
		setLayout(new BorderLayout(10,10));

		//add Panels to Main Layout
		add(topButtonsPanel,BorderLayout.NORTH);
		add(statusPanel, BorderLayout.CENTER);
		add(bottomContainerPanel, BorderLayout.SOUTH);

		//adding imagePanels to status panel
		statusPanel.add(fireStatusPanel);
		statusPanel.add(leakStatusPanel);
		statusPanel.add(lightStatusPanel);

		bottomContainerPanel.add(buttonContainerPanel);
		bottomContainerPanel.add(ipStatusBarPanel);

		buttonContainerPanel.add(refreshButtonPanel);
		buttonContainerPanel.add(lightButtonPanel);

		ipStatusBarPanel.add(ipLabel);

		//add refresh button
		topButtonsPanel.add(setIpButton);
		refreshButtonPanel.add(refreshFireButton);
		lightButtonPanel.add(lightOnButton);
		lightButtonPanel.add(lightOffButton);

		//adding ActionListeners
		//ActionListeners are in other files
		//
		setIpButton.addActionListener(new IpMenuItemActionListener(myConnectionManager));
		refreshFireButton.addActionListener(new RefreshFireButtonListener(myModel,myConnectionManager));
		lightOnButton.addActionListener(new LightsOnActionListener(myModel,myConnectionManager));
		lightOffButton.addActionListener(new LightsOffActionListener(myModel,myConnectionManager));
		ipMenuItem.addActionListener(new IpMenuItemActionListener(myConnectionManager));

	}

	public void update(Observable o, Object arg){
		//This method is called whenver a change is detected in the SmartHouseModel or ClientConnectionManager
		SmartHouseModel model =myModel;

		//if ClientConnectionManager triggered the update method point cast o as a ClientConnectionManager
		//else Cast o as a SmartHouse Model
		if(o instanceof ClientConnectionManager){
			ClientConnectionManager c = (ClientConnectionManager) o;
			ipLabel.setText("IP Address: " + c.getServerAddress()); //if IP Address is changed, through the dialog box, reflect that change onto the status bar
		}
		else{
			model = (SmartHouseModel) o;
		}


		//Change the View according to the new changes in the model

		//if fire/leak/lights are active enable or disable the panels
		//the definition of on() and off() are in different files
		if(model.getFireStatus()){
			fireStatusPanel.on();
		}else{
			fireStatusPanel.off();
		}

		if(model.getLeakStatus()){
			leakStatusPanel.on();
		}else{
			leakStatusPanel.off();
		}

		if(model.getLightStatus()){
			lightStatusPanel.on();
		}else{
			lightStatusPanel.off();
		}
	}
}