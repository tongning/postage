
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ButtonOptions is a JPanel object that displays the name of the app in large characters,
 * along with four large buttons that present the initial options to the user: Postcard,
 * Envelope, Large Envelope, and Package. The buttons are contained in a ButtonGroup object.
 * @author Karina Chang, Anthony Li
 *
 */
public class ButtonOptions extends JPanel{
	private JLabel instructions;
	private JPanel bGroup;
	private MainScreen linkApp;
	
	public ButtonOptions(MainScreen linkToApp){
		super();
		linkApp=linkToApp;
		setFocusable(true);
		setVisible(true);
		setLayout(new BorderLayout());
		
		instructions=new JLabel("Select an item type:  ");
		bGroup=new ButtonGroup(linkApp);
		
		add(instructions,BorderLayout.NORTH);
		add(bGroup,BorderLayout.CENTER);
	}

	
	/**
	 * ButtonGroup is a JPanel object that displays four large buttons for the
	 * initial package type selection.
	 * @author Karina Chang, Anthony Li
	 *
	 */
	public class ButtonGroup extends JPanel{
		private JButton postcard;
		private JButton envelope;
		private MainScreen linkApp;
		private JButton largeEnvelope;
		private JButton pack; //means package
		public ButtonGroup(MainScreen linkToApp){
			super();
			linkApp=linkToApp;
			setFocusable(true);
			setVisible(true);
			
			setLayout(new GridLayout(4,1,20,10));
			postcard=new JButton("Postcard");
			envelope=new JButton("Envelope");
			largeEnvelope=new JButton("Large Envelope");
			pack=new JButton("Package");
			

			
			
			//adding the jbuttons
			add(postcard);
			add(envelope);
			add(largeEnvelope);
			add(pack);//
			
			//action listener for postcard, envelope, and large Envelope because they all go to the same following screen
			ActionListener clickPostcard=new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//System.out.println("You are "+e.getActionCommand());
					linkApp.setTypePackage(e.getActionCommand());
					if(e.getActionCommand().equals("Envelope") || e.getActionCommand().equals("Large Envelope")){
						linkApp.changeScreen(MainScreen.SCREEN_WEIGHT,MainScreen.SCREEN_HOME);
					}
					else
						linkApp.changeScreen(MainScreen.SCREEN_DIM,MainScreen.SCREEN_HOME);

				}//
			};
			
			//action listener for package
			ActionListener clickPack=new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//System.out.println("You are "+e.getActionCommand());
					linkApp.setTypePackage(e.getActionCommand());
					linkApp.changeScreen(MainScreen.SCREEN_RATE,MainScreen.SCREEN_HOME);

				}
			};
			
			try{
				ImageIcon postC=new ImageIcon(ImageIO.read(new File("Postcard.png")));
				ImageIcon env=new ImageIcon(ImageIO.read(new File("Letter.png")));
				ImageIcon lEnv=new ImageIcon(ImageIO.read(new File("smallPackage.png")));
				ImageIcon packI=new ImageIcon(ImageIO.read(new File("bigPackage.png")));
				postcard.setIcon(postC);
				envelope.setIcon(env);
				largeEnvelope.setIcon(lEnv);
				pack.setIcon(packI);
			}
			catch (IOException e){
				System.exit(-1);
			}
			postcard.addActionListener(clickPostcard);
			envelope.addActionListener(clickPostcard);
			largeEnvelope.addActionListener(clickPostcard);
			
			pack.addActionListener(clickPack);
			
		}
	}
}
