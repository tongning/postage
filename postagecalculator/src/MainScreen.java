import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/*
 * @author Karina Chang, Anthony Li
 * trying to make changes
 */

public class MainScreen extends JPanel{
	private JLabel header;

	private JLabel instructions;
	private static int screen=0;
	/**DIFFRENT SCREEN SWITCHING**/
	public static final int SCREEN_HOME=0;

	private JPanel currentScreen;
	private int previousS=0;
	private ArrayList<Integer> tracking=new ArrayList<Integer>();
	
	public static final int SCREEN_WEIGHT=1;
	public static final int SCREEN_RATE=2;
	public static final int SCREEN_DIM=3;
	public static final int SCREEN_ZIP=4;
	public static final int SCREEN_BOXSIZE=5;
	public static final int SCREEN_FINAL=6;
	
	/**INSTANCES OF NEW SCREENS**/
	private JPanel buttonOptions=new ButtonOptions(this);
	private JPanel weight = new WeightScreen(this);
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
	
	//zipcodes
	private int zipStart=0;
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



	private int zipEnd=0;
	
	//flat rate box size, medium or large
	//0 is undefined, 1 is medium, 2 is large
	private int boxType=0;
	
	
	//flat rate express vs. flat rate regular
	//0 is undefined, 1 is regular, 2 is express
	private int shipSpeed=0;
	
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
		
//		nFrame.setVisible(false);
//		JFrame weight=new JFrame();
//		weight.setSize(new Dimension(400,600));
//		weight.add(new WeightScreen());
//		weight.setVisible(true);
		
		

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
		//jpanel which will hold the button options for types of mail: postcard, envelope, large envelope, package
	

		
		
		add(header,BorderLayout.NORTH);
		changeScreen(SCREEN_HOME,SCREEN_HOME);
		
	}//end of constructor
	public void setTracking(ArrayList<Integer> newT){
		tracking=newT;
	}
	
	
	
	public void changeScreen(int screen, int previous){
		JPanel newScreen=null;
		tracking.add(screen);
		System.out.println("Tracking: "+tracking);
		//switch statement for different screens
		switch(screen){
		case SCREEN_WEIGHT:
			newScreen=weight;
			weight=new WeightScreen(this);
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
		if(currentScreen!=null) remove(currentScreen);
		add(newScreen,BorderLayout.CENTER);
	 
		newScreen.setVisible(true);
		validate();
		currentScreen=newScreen;
	}
	
}
