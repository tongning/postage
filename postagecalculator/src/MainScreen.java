import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





/**
 * MainScreen is a JPanel object that contains the main() method that launches
 * the postage calculator app. MainScreen is responsible for keeping track of variables
 * used throughout the program.
 * @author Karina Chang, Anthony Li
 *
 */
public class MainScreen extends JPanel{
	private JLabel header;

	//to keep track of current screen
	private JPanel currentScreen;
	
	//to keep track of previous screens
	private ArrayList<Integer> tracking=new ArrayList<Integer>();
	
	/**SCREEN FINAL INTS**/
	public static final int SCREEN_HOME=0; //home screen which has the button options
	public static final int SCREEN_WEIGHT=1;  //screen which asks for weight of item
	public static final int SCREEN_RATE=2;  //screen for rate speed
	public static final int SCREEN_DIM=3;  //screen for dimension and sizing of item
	public static final int SCREEN_ZIP=4;  //screen for start and end zip code
	public static final int SCREEN_BOXSIZE=5;  //screen for large or medium box size
	public static final int SCREEN_FINAL=6; //final screen which shows the price
	
	/**INSTANCES OF NEW SCREENS**/
	private JPanel buttonOptions=new ButtonOptions(this);
	private JPanel weight;
	private JPanel rate=new RateOptions(this);
	private JPanel zip;
	private JPanel dim;
	private JPanel boxSize=new BoxSize(this);
	private JPanel finalScreen;
	
	/**VARIABLES TO BE CARRIED THROUGHOUT**/
	private String typePackage="";
	private String typeRate="";

	//package dimensions
	private double packWidth=0;
	private double packLength=0;
	private double packHeight=0;
	private String packUnit="";
	
	/**Series of getters and setters for things that are carried throughout all the screens**/
	//package units/width/length/height
	public String getPackUnit() {
		return packUnit;
	}
	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}
	public double getPackWidth() {
		return packWidth;
	}
	public void setPackWidth(double packWidth) {
		this.packWidth = packWidth;
	}
	public double getPackLength() {
		return packLength;
	}
	public void setPackLength(double packLength) {
		this.packLength = packLength;
	}
	public double getPackHeight() {
		return packHeight;
	}
	public void setPackHeight(double packHeight) {
		this.packHeight = packHeight;
	}


	
	//postcard/envelope/large envelope weight
	private double pWeight=0;
	private String weightUnits="";
	
	/**Getters and setters for zip code start and end**/
	//zipcodes
	private int zipStart=0;
	private int zipEnd=0;
	public int getZipStart() {
		return zipStart;
	}
	public void setZipStart(int zipStart) {
		this.zipStart = zipStart;
	}
	public int getZipEnd() {
		return zipEnd;
	}
	public void setZipEnd(int zipEnd) {
		this.zipEnd = zipEnd;
	}


	
	//flat rate box size, medium or large
	//0 is undefined, 1 is medium, 2 is large
	private int boxType=0;
	
	
	//flat rate express vs. flat rate regular
	//0 is undefined, 1 is regular, 2 is express
	private int shipSpeed=0;
	
	/**GETTERS AND SETTERS for weight units and boxes**/
	public String getTypePackage(){
		return typePackage;
	}
	public void setTypePackage(String newType){
		typePackage=newType;
	}
	public double getWeight(){
		return pWeight;
	}
	public void setWeight(double newWeight){
		pWeight=newWeight;
	}
	public String getWeightUnit(){
		return weightUnits;
	}
	public void setWeightUnit(String newUnit){
		weightUnits=newUnit;
	}
	public int getShipSpeed(){
		return shipSpeed;
	}
	public void setShipSpeed(int newSpeed){
		shipSpeed=newSpeed;
	}
	
	public int getBoxType(){
		return boxType;
	}
	public void setBoxType(int newType){
		boxType=newType;
	}
	
	public static void main(String[] args){
		
		JFrame nFrame=new JFrame();
		nFrame.setDefaultLookAndFeelDecorated(true);
		nFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nFrame.setResizable(false);
		nFrame.setContentPane(new MainScreen());
		nFrame.pack();
		nFrame.setSize(new Dimension(400,600));
		nFrame.setVisible(true);

	}//end of main
	
	public MainScreen(){
		super();
		setFocusable(true);
		setSize(400,600);
		setVisible(true);
		
		//setting the layout to Border Layout
		setLayout(new BorderLayout());
		
		header=new JLabel("Postage Price Calculator"); //header which will go in the north
		header.setFont(new Font("Times New Roman",Font.BOLD,24));
	
		add(header,BorderLayout.NORTH); //adds it to the border layout
		changeScreen(SCREEN_HOME,SCREEN_HOME); //references to the button options
		
	}//end of constructor
	
	/*
	 * Setter for tracking
	 * @param ArrayList<Integer> newT - new arraylist of the ints which tracks the path of the clicking
	 * @return void
	 */
	public void setTracking(ArrayList<Integer> newT){
		tracking=newT;
	}

	
	/*
	 * Method for changing the screens
	 * @param int screen, int previous - screen is the screen to change to, and previous is the screen which they just came from
	 * @return void
	 */
	public void changeScreen(int screen, int previous){
		JPanel newScreen=null;
		tracking.add(screen); //adds it to the array list for tracking
		//System.out.println("Tracking: "+tracking);
		
		//switch statement for different screens
		switch(screen){
		case SCREEN_WEIGHT:
			weight=new WeightScreen(this,tracking);
			newScreen=weight;
		
			break;
		case SCREEN_HOME:
			newScreen=buttonOptions;
			buttonOptions=new ButtonOptions(this);
			break;
		case SCREEN_RATE:
			newScreen=rate;
			rate=new RateOptions(this);
			break;
		case SCREEN_ZIP:
			zip=new ZipCode(this,tracking);
			newScreen=zip;
			System.out.println("Previous: "+previous);
			break;
		case SCREEN_DIM:
			
			dim=new DimensionScreen(this, tracking);
			newScreen=dim;
			break;
		case SCREEN_BOXSIZE:
			newScreen=boxSize;
			boxSize=new BoxSize(this);
			break;
		case SCREEN_FINAL:
			
			finalScreen=new FinalScreen(this,tracking);
			newScreen=finalScreen;
			break;
			
		}
		 
	//	else if (screen==SCREEN_RATE) newScreen=rate;
		
		//newScreen.setBackground(Color.red);
		
		//removing the current screen so that the new screen can go on
		if(currentScreen!=null) remove(currentScreen);
		add(newScreen,BorderLayout.CENTER); //adds the new screen to the center of the border layout
		newScreen.setVisible(true);
		validate();
		
		currentScreen=newScreen;
	}
	
}
