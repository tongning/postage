
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * FinalScreen is a JPanel object used to display the final postage price
 * calculation. It retrieves all variables entered, calculates the price, and
 * displays the result.
 * @author Karina Chang, Anthony Li
 *
 */
public class FinalScreen extends JPanel{
	private JLabel finalprice= new JLabel("<html><font size='60'>3.56</font></html>"); //JLabel for price display
	private double pricevar=0.00; //variable to store result
	private MainScreen linkApp; //allows retrieval of variable values
	private ArrayList<Integer> previousS=new ArrayList<Integer>(); //back and forward button tracking
	private JButton home = new JButton("Home"); 
	private JButton back= new JButton("Back");
	private JLabel instructions = new JLabel("Your estimated price is:");
	
	

	/**
	 * Getter to return the int of the previous screen
	 * @return int of previous screen
	 */
	public int getPreviousScreen() {
		return previousS.get(previousS.size()-2);
	}

	/**
	 * Setter to set the arraylist of previous (tracks the path)
	 * @param previousScreen
	 */
	public void setPreviousScreen(ArrayList<Integer> previousScreen) {
		this.previousS = previousScreen;

	}

	/**
	 * Setter which sets the main screen link
	 * @param linkToApp - mainscreen
	 */
	public void setLinkToApp(MainScreen linkToApp){
		linkApp=linkToApp;
	}
	/**
	 * Constructor which sets the grid bag layout and sets the preliminary things such as previous screen and linkApp
	 * @param linkToApp - links to the main screen
	 * @param previous - arraylist of tracking
	 */
	public FinalScreen(MainScreen linkToApp, ArrayList<Integer> previous){
		//setting previous and linkToApp
		setPreviousScreen(previous);
		setLinkToApp(linkToApp);
		
		/**GRID BAG LAYOUT**/
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridx=0;
		c.gridy=0;
		c.anchor=GridBagConstraints.CENTER;
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.insets=new Insets(50,0,0,0);
		
		gridbag.setConstraints(instructions, c);
		add(instructions);

		c.insets=new Insets(0,0,0,0);

		c.anchor=GridBagConstraints.CENTER;
		c.gridx=0;
		c.gridy=1;
		c.weighty=30;
		c.insets=new Insets(100,110,50,50);
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.fill=GridBagConstraints.BOTH;

		//System.out.println("Package Type is "+linkApp.getTypePackage());
		//calculation algorithm for flat rate options
		if(linkApp.getTypePackage().equals("PackageFlatRate")){
			if(linkApp.getBoxType()==2){
				if(linkApp.getShipSpeed()==2)
					pricevar=44.95; //express large box
				else if(linkApp.getShipSpeed()==1)
					pricevar=17.90; //regular large box
			}
			else if(linkApp.getBoxType()==1){
				if(linkApp.getShipSpeed()==2)
					pricevar=44.95; //express med box
				else if(linkApp.getShipSpeed()==1)
					pricevar=12.65; //regular med box
			}
		}
		//calculation algorithm for packages
		else if(linkApp.getTypePackage().equals("Package")){   //non-flatrate package
			//get zip code, weight, dimensions, and units
			int startzip=linkApp.getZipStart();
			int endzip=linkApp.getZipEnd();
			double weight=linkApp.getWeight();
			String weightunit=linkApp.getWeightUnit();
			
			double length=linkApp.getPackLength();
			double width=linkApp.getPackWidth();
			double height=linkApp.getPackHeight();
			String packUnit=linkApp.getPackUnit();
			
			//convert all lengths to inches
			if(packUnit.equals("Centimeters")){
				System.out.println("Converting lengths to inches...");
				length=length/2.54;
				width=width/2.54;
				height=height/2.54;
			}
			
			//estimate the distance between zip codes
			double distance=(double)(Math.abs(endzip-startzip));
			distance=(distance/99999.0)*2500; //formula to estimate distance based on difference in zip codes
			//convert weight to pounds if necessary
			
			if(weightunit.equals("grams")){
				System.out.println("Converting grams to pounds...");
				weight*=0.00220462;
			}
			else if(weightunit.equals("pages")){
				System.out.println("Converting pages to pounds...");
				weight*=0.0099208;
				
			}
			
			//check if package is considered "large" - any dimension over 12 inches
			boolean isLarge=false;
			if(length>12 || width >12 || height>12){
				isLarge=true;
			}
			System.out.println(linkApp.getShipSpeed());
			/*
			 * Calculate the price according to following rule:
			 * 3 dollar base price plus:
			 * "Large" packages: .5 cents per pound per mile
			 * Regular packages: .3 cents per pound per mile
			 */
			if(isLarge){
				pricevar=weight*distance*0.005+3;
			}
			else{
				pricevar=weight*distance*0.003+3;
			}
			
			//for express shipments, double the price
			if(linkApp.getShipSpeed()==2){
				System.out.println("Express speed; doubling price...");
				pricevar*=2;
			}
			
		}
		//calculation for postcards
		else if(linkApp.getTypePackage().equals("Postcard")){
			//get the dimensions
			double length=linkApp.getPackLength();
			double width=linkApp.getPackWidth();
			double height=linkApp.getPackHeight();
			String packUnit=linkApp.getPackUnit();
			
			//convert all lengths to inches
			if(packUnit.equals("Centimeters")){
				System.out.println("Converting lengths to inches...");
				length=length/2.54;
				width=width/2.54;
				height=height/2.54;
			}
			
			if(length<=6 && width<=4.25){
				pricevar=0.34;
			}
			else{
				pricevar=0.49;
			}
			
		}
		//calculation for envelopes
		else if(linkApp.getTypePackage().equals("Envelope")){
			double weight=linkApp.getWeight();
			String weightunit=linkApp.getWeightUnit();
			//convert weight to pounds if necessary
			if(weightunit.equals("grams")){
				System.out.println("Converting grams to pounds...");
				weight*=0.00220462;
			}
			else if(weightunit.equals("pages")){
				System.out.println("Converting pages to pounds...");
				weight*=0.0104167;
				
			}
			//now convert pounds to ounces
			weight*=16; //16 ounces per pound
			
			/*
			 * Pricing scheme:
			 * 49 cents for envelopes below 1 oz
			 * For every oz over 1, add 21 cents
			 */
			if(weight<=1){
				pricevar=0.49;
			}
			else{
				double weightover=weight-1;
				pricevar=0.49+weightover*0.21;
			}
		}
		//calculation for large envelopes
		else if(linkApp.getTypePackage().equals("Large Envelope")){
			double weight=linkApp.getWeight();
			String weightunit=linkApp.getWeightUnit();
			//convert weight to pounds if necessary
			if(weightunit.equals("grams")){
				System.out.println("Converting grams to pounds...");
				weight*=0.00220462;
			}
			else if(weightunit.equals("pages")){
				System.out.println("Converting pages to pounds...");
				weight*=0.0104167;
				
			}
			//now convert pounds to ounces
			weight*=16.0; //16 ounces per pound
			
			/*
			 * Pricing scheme:
			 * 98 cents for envelopes below 1 oz
			 * For every oz over 1, add 21 cents
			 * http://www.stamps.com/usps/postage-rate-increase/
			 */
			if(weight<=1){
				pricevar=0.98;
			}
			else{
				double weightover=weight-1;
				pricevar=0.98+weightover*0.21;
			}
		}
		pricevar=Math.round(pricevar * 100.0) / 100.0;
		
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		//display the price
		finalprice= new JLabel("<html><font size='40'>$"+df.format(pricevar)+"</font></html>");
		gridbag.setConstraints(finalprice, c);
		add(finalprice);


		c.insets=new Insets(0,0,0,0);
		c.weighty=0;
		c.gridy=2;

		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_START;
		c.fill=GridBagConstraints.NONE;
		c.insets=new Insets(0,0,0,200);
		//creating back button
		back = new JButton("Back");
		gridbag.setConstraints(back, c);
		add(back);


		c.insets=new Insets(0,0,0,0);
		c.gridy=2;
		c.gridx=1;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		//home button
		gridbag.setConstraints(home, c);
		add(home);


		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//due to the adding of the arraylist in main screen, it's necessary to check if there are repeats if the user goes back and forward
				//multiple times
				//adds the clean version to the newHold

				ArrayList<Integer> newHold=new ArrayList<Integer>();

				for (int x=0;x<previousS.indexOf(linkApp.SCREEN_FINAL)-1;x++){
					newHold.add(previousS.get(x));
				}

				linkApp.setTracking(newHold);
				linkApp.changeScreen(getPreviousScreen(),MainScreen.SCREEN_FINAL);
			}
		};
		//home button action listener
		ActionListener clickHome=new ActionListener(){
			public void actionPerformed(ActionEvent e){

				linkApp.changeScreen(MainScreen.SCREEN_HOME,MainScreen.SCREEN_FINAL);
			}
		};
		back.addActionListener(clickBackward);
		home.addActionListener(clickHome);

	}
}
