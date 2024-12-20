package GameUno;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WelcomePanel extends PanelGBLayout{

	private String IconPath;
	
	WelcomePanel(JFrame MainWindow){
		super(MainWindow);
		this.IconPath=this.getDirPath()+"\\src\\resources\\Icon.png";
		
		// Initialisation of Components :
		JPanel Spacer=new JPanel();
		Button newGameBtn=new Button("Start a new Game");
		Button loadOldGameBtn=new Button("Load old Game results");
		JLabel imgLabel=new JLabel(new ImageIcon(IconPath));
		// Button Click Events :
		newGameBtn.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						newGameEv();
					}
		});
		loadOldGameBtn.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				
			}
		});
		// Positioning the Components :
		this.setGBC(
				0,0,
				1,1,
				1,1,
				GridBagConstraints.BOTH,GridBagConstraints.CENTER
				);
		// Adding the Image
		setGBC(0,0);
		this.add(imgLabel,this.getGBC());
		
		// Adding the buttons
		setGBC(0,1);
		this.add(newGameBtn,this.getGBC());
		setGBC(0,2);
		this.add(loadOldGameBtn,this.getGBC());
		
		// Adding Spacer
		this.getGBC().weighty=0.5;
		setGBC(0,3);
		this.add(Spacer,this.getGBC());
		
	}
	private void newGameEv() {
		if(this.getMainWindow() != null) 
		{
			this.getMainWindow().remove(this);
			this.getMainWindow().add(new newGamePanel(getMainWindow()));
			this.getMainWindow().revalidate();
			this.getMainWindow().repaint();
		}
	}
	private void loadOldEv() {
		System.out.println("not set yet");
	}

	

	

}
