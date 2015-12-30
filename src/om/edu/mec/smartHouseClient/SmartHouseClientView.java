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

	private SmartHouseModel myModel;
	private ClientConnectionManager myConnectionManager;

	private MenuBar myMenuBar;
	private Menu myMenu;
	private MenuItem ipMenuItem;

	private Label fireLabel;
	private Label leakLabel;
	private Label lightLabel;
	private Label ipLabel;

	private BufferedImage moltres; 

	private Button setIpButton;
	private Button refreshFireButton;
	private Button lightOnButton;
	private Button lightOffButton;

	private Panel topButtonsPanel;
	private Panel statusPanel;
	private FireStatusPanel fireStatusPanel;
	private Panel leakStatusPanel;
	private Panel lightStatusPanel;
	private Panel bottomContainerPanel;
	private Panel buttonContainerPanel;
	private Panel refreshButtonPanel;
	private Panel lightButtonPanel;
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

		try{
			moltres = ImageIO.read(new File("Moltres.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}


		setIpButton = new Button("Set IP");
		refreshFireButton = new Button("Refresh");
		lightOnButton = new Button("Lights On");
		lightOffButton = new Button("Lights Off");

		topButtonsPanel = new Panel(new FlowLayout(FlowLayout.LEFT,15,0));
		statusPanel = new Panel(new GridLayout(1,3,50,50));
		fireStatusPanel = new FireStatusPanel();
		leakStatusPanel = new Panel(new FlowLayout());
		lightStatusPanel = new Panel(new FlowLayout());
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

		//add status to layout
		//statusPanel.add(fireLabel);
		//statusPanel.add(leakLabel);
		//statusPanel.add(lightLabel);

		//set Alignment of Label
		fireLabel.setAlignment(Label.CENTER);
		leakLabel.setAlignment(Label.CENTER);
		lightLabel.setAlignment(Label.CENTER);

		//add refresh button
		topButtonsPanel.add(setIpButton);
		refreshButtonPanel.add(refreshFireButton);
		lightButtonPanel.add(lightOnButton);
		lightButtonPanel.add(lightOffButton);

		//adding ActionListeners
		setIpButton.addActionListener(new IpMenuItemActionListener(myConnectionManager));
		refreshFireButton.addActionListener(new RefreshFireButtonListener(myModel,myConnectionManager));
		lightOnButton.addActionListener(new LightsOnActionListener(myModel,myConnectionManager));
		lightOffButton.addActionListener(new LightsOffActionListener(myModel,myConnectionManager));
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

	public void paint(Graphics g){
		//g.drawImage(moltres,50,50,null);
	}
}