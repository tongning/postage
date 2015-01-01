

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class DimensionScreen extends JPanel {
	private int unitselection=1;
	private JTextField widthbox = new JTextField("Width (inches)");
	private JTextField heightbox = new JTextField("Height (inches)");
	private JTextField lengthbox = new JTextField("Length (inches)");
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton inches = new JRadioButton("Inches");
	private JRadioButton cm = new JRadioButton("Centimeters");
	
	//link to main screen
	private MainScreen linkApp;
	
//	public static void main (String[] args){
//		JFrame f = new JFrame("Postage Calc");
//		f.setContentPane(new DimensionScreen());
//		f.setDefaultCloseOperation(3);
//		f.setDefaultLookAndFeelDecorated(true);
//		f.pack();
//		f.setSize(new Dimension(400,600));
//		
//		f.setVisible(true);
//	}
	
	public DimensionScreen(MainScreen linkToApp){
		
		linkApp=linkToApp;
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=GridBagConstraints.REMAINDER;
		JLabel instructions = new JLabel("Enter box dimensions:");
		gridbag.setConstraints(instructions, c);
		add(instructions);




		bg.add(inches);
		bg.add(cm);


		c.anchor=GridBagConstraints.FIRST_LINE_START;
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=GridBagConstraints.REMAINDER;
		gridbag.setConstraints(inches, c);
		add(inches);

		c.gridy=2;
		gridbag.setConstraints(cm, c);
		add(cm);

		inches.setSelected(true);



		c.gridx=0;
		c.gridy=3;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets=new Insets(50,0,0,0);
		String unitstring="";
		inches.addItemListener(new ItemListener() {


			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Inches state changed");
				if(e.getStateChange()==1) {
					unitselection=1;
					widthbox.setText("Width (inches)");
					heightbox.setText("Height (inches)");
					lengthbox.setText("Length (inches)");
				}
				else if(e.getStateChange()==2) {
					unitselection=2;
					widthbox.setText("Width (cm)");
					heightbox.setText("Height (cm)");
					lengthbox.setText("Length (cm)");
				}
			}           
		});


		widthbox.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e){
				widthbox.setText("");
			}



			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(widthbox.getText().equals("")){
					if(unitselection==1)widthbox.setText("Width (inches)");
					else if(unitselection==2)widthbox.setText("Width (cm)");
				}
			}
		});
		lengthbox.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e){
				lengthbox.setText("");
			}



			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(lengthbox.getText().equals("")){
					if(unitselection==1)lengthbox.setText("Length (inches)");
					else if(unitselection==2)lengthbox.setText("Length (cm)");
				}
			}
		});

		heightbox.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e){
				heightbox.setText("");
			}



			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(heightbox.getText().equals("")){
					if(unitselection==1)heightbox.setText("Height (inches)");
					else if(unitselection==2)heightbox.setText("Height (cm)");
				}
			}
		});

		lengthbox.setPreferredSize(new Dimension(200,30));
		gridbag.setConstraints(lengthbox, c);
		add(lengthbox);

		c.gridx=0;
		c.gridy=4;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets=new Insets(50,0,0,0);

		widthbox.setPreferredSize(new Dimension(200,30));
		gridbag.setConstraints(widthbox, c);
		add(widthbox);

		c.gridx=0;
		c.gridy=5;
		c.gridwidth=1;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.insets=new Insets(50,0,0,0);

		heightbox.setPreferredSize(new Dimension(200,30));
		gridbag.setConstraints(heightbox, c);
		add(heightbox);

		/*
		c.gridx=1;
		c.gridy=3;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.CENTER;
		JLabel units = new JLabel("unit");
		gridbag.setConstraints(units, c);
		add(units);

		c.gridx=1;
		c.gridy=4;
		gridbag.setConstraints(units, c);
		add(units);

		c.gridx=1;
		c.gridy=5;
		gridbag.setConstraints(units, c);
		add(units);
		 */

		c.gridy=6;
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_START;
		c.fill=GridBagConstraints.NONE;
		JButton back = new JButton("Back");
		gridbag.setConstraints(back, c);
		add(back);

		c.gridy=6;
		c.gridx=0;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.LAST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		JButton forward = new JButton("Forward");
		gridbag.setConstraints(forward, c);
		add(forward);

		
		//action listener for forward screens
		ActionListener clickForward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				linkApp.setPackHeight(Integer.parseInt(heightbox.getText()));
				linkApp.setPackWidth(Integer.parseInt(widthbox.getText()));
				linkApp.setPackLength(Integer.parseInt(lengthbox.getText()));
				if(unitselection==1)
					linkApp.setPackUnit("Inches");
				else if(unitselection==2)
					linkApp.setPackUnit("Centimeters");
				if(linkApp.getTypePackage().equals("Postcard"))
					linkApp.changeScreen(MainScreen.SCREEN_FINAL,MainScreen.SCREEN_DIM);
				else if(!linkApp.getTypePackage().equals("Package"))
					linkApp.changeScreen(MainScreen.SCREEN_ZIP,MainScreen.SCREEN_DIM);
				else
					linkApp.changeScreen(MainScreen.SCREEN_WEIGHT,MainScreen.SCREEN_DIM);

			}//
		};
		
		//action listener for backward screens
		ActionListener clickBackward=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("You are "+e.getActionCommand());
				linkApp.changeScreen(MainScreen.SCREEN_HOME,MainScreen.SCREEN_DIM);

			}
		};
		
		forward.addActionListener(clickForward);
		back.addActionListener(clickBackward);
		
	}
}
