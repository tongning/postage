
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//git test
public class FinalScreen extends JPanel{
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton grams = new JRadioButton("Grams");
	private JRadioButton pounds = new JRadioButton("Pounds");
	private JRadioButton estimate = new JRadioButton("Use SmartEstimate");
	private JLabel units = new JLabel("lbs  ");
	private JLabel finalprice= new JLabel("<html><font size='60'>3.56</font></html>");
	private double pricevar=0.00;
	private MainScreen linkApp;
	private ArrayList<Integer> previousS=new ArrayList<Integer>();

	public FinalScreen(MainScreen linkToApp, ArrayList<Integer> previous){
		previousS=previous;
		linkApp=linkToApp;
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridx=0;
		c.gridy=0;
		c.anchor=GridBagConstraints.CENTER;
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.insets=new Insets(50,0,0,0);
		JLabel instructions = new JLabel("Your price is:");
		gridbag.setConstraints(instructions, c);
		add(instructions);

		c.insets=new Insets(0,0,0,0);



		c.anchor=GridBagConstraints.CENTER;
		c.gridx=0;
		c.gridy=1;
		c.weighty=30;
		c.insets=new Insets(100,140,50,50);
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.fill=GridBagConstraints.BOTH;

		System.out.println("Package Type is "+linkApp.getTypePackage());

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
				weight*=0.0099208;
				
			}
			//now convert pounds to ounces
			weight*=16; //16 ounces per pound
			
			/*
			 * Pricing scheme:
			 * 49 cents for envelopes below 13 oz
			 * For every oz over 13, add 21 cents
			 */
			if(weight<=13){
				pricevar=0.49;
			}
			else{
				double weightover=weight-13;
				pricevar=0.49+weightover*0.21;
			}
		}

		finalprice= new JLabel("<html><font size='40'>"+pricevar+"</font></html>");
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
		JButton back = new JButton("Back");
		gridbag.setConstraints(back, c);
		add(back);


		c.insets=new Insets(0,0,0,0);
		c.gridy=2;
		c.gridx=1;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		JButton forward = new JButton("Forward");
		gridbag.setConstraints(forward, c);
		add(forward);


		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){


				ArrayList<Integer> newHold=new ArrayList<Integer>();

				for (int x=0;x<previousS.indexOf(6);x++){
					newHold.add(previousS.get(x));
				}


				//	previousScreen.remove(previousScreen.size()-1);
				System.out.println(newHold);
				linkApp.setTracking(newHold);

				linkApp.changeScreen(previousS.get(previousS.size()-2),MainScreen.SCREEN_FINAL);
			}
		};
		back.addActionListener(clickBackward);


	}
}
