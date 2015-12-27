package om.edu.mec.smartHouseClient;

import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;

import om.edu.mec.smartHouseCommon.SmartHouseModel;

class SmartHouseClientView extends Frame implements Observer{

	SmartHouseModel myModel;
	ClientConnectionManager myConnectionManager;

	private MenuBar myMenuBar;
	private Menu myMenu;
	private MenuItem ipMenuItem;

	private Label fireLabel;
	private Label leakLabel;
	private Label lightLabel;
	private Label ipLabel;

	private Button refreshFireButton;

	private Panel statusPanel;
	private Panel bottomContainerPanel;
	private Panel buttonPanel;
	private Panel ipStatusBarPanel;

	SmartHouseClientView(SmartHouseModel myModel,ClientConnectionManager myConnectionManager){
		this.myModel = myModel;
		this.myConnectionManager = myConnectionManager;
		//setSize and location and title
		setTitle("Smart House Client");
		setSize(650,500);
		setLocation(1200,900);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		//instantiate 
		myMenuBar = new MenuBar();
		myMenu = new Menu("options");
		ipMenuItem = new MenuItem("Set Server IP");


		fireLabel = new Label("No Fire");
		leakLabel = new Label("No Water Leak");
		lightLabel = new Label("Lights Off");
		ipLabel = new Label("IP Address: "+ myConnectionManager.getServerAddress());
		ipLabel.setAlignment(Label.LEFT);

		refreshFireButton = new Button("Refresh");

		statusPanel = new Panel(new GridLayout(1,3,50,50));
		bottomContainerPanel = new Panel(new GridLayout(2,1));
		buttonPanel = new Panel(new FlowLayout(10));
		ipStatusBarPanel = new Panel(new FlowLayout(FlowLayout.LEFT));

		//Set Menu
		myMenu.add(ipMenuItem);
		myMenuBar.add(myMenu);
		setMenuBar(myMenuBar);

		//set layout manager
		setLayout(new BorderLayout(10,10));

		//add Panels to Main Layout
		add(statusPanel, BorderLayout.CENTER);
		add(bottomContainerPanel, BorderLayout.SOUTH);

		bottomContainerPanel.add(buttonPanel);
		bottomContainerPanel.add(ipStatusBarPanel);
		ipStatusBarPanel.add(ipLabel);

		//add status to layout
		statusPanel.add(fireLabel);
		statusPanel.add(leakLabel);
		statusPanel.add(lightLabel);

		//set Alignment of Label
		fireLabel.setAlignment(Label.CENTER);
		leakLabel.setAlignment(Label.CENTER);
		lightLabel.setAlignment(Label.CENTER);

		//add refresh button
		buttonPanel.add(refreshFireButton);

		//adding ActionListeners
		refreshFireButton.addActionListener(new RefreshFireButtonListener(myModel,myConnectionManager));
		ipMenuItem.addActionListener(new IpMenuItemActionListener(myConnectionManager));

	}

	public void update(Observable o, Object arg){

		SmartHouseModel model =myModel;

		if(o instanceof ClientConnectionManager){
			ClientConnectionManager c = (ClientConnectionManager) o;
			ipLabel.setText("IP Address: " + c.getServerAddress());
		}
		else{
			model = (SmartHouseModel) o;
		}

		if(model.getFireStatus()){
			fireLabel.setText("On Fire!!! T.T");
		}else{
			fireLabel.setText("No Fire");
		}

		if(model.getLeakStatus()){
			leakLabel.setText("Water is leaking!!! T.T");
		}else{
			leakLabel.setText("No Water Leak");
		}

		if(model.getLightStatus()){
			lightLabel.setText("Lights are On ^.^");
		}else{
			lightLabel.setText("Lights Off");
		}
	}
}