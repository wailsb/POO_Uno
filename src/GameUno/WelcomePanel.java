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

class WelcomePanel extends JPanel {

	private String IconPath=System.getProperty("user.dir")+"\\src\\resources\\Icon.png";
	private JFrame Main;
	WelcomePanel(JFrame MainWindow){

		this.Main=MainWindow;
		this.setLayout(new GridBagLayout());
		
		// Initialisation of Components :
		JPanel Spacer=new JPanel();
		GridBagConstraints gbc=new GridBagConstraints();
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
		// Positioning the Components :
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(imgLabel,gbc);
		gbc.gridy=1;
		this.add(newGameBtn,gbc);
		gbc.gridy=2;
		this.add(loadOldGameBtn,gbc);
		gbc.weighty=0.5;
		gbc.gridy=3;
		this.add(Spacer,gbc);
		
	}
	private void newGameEv() {
		if(this.Main != null) 
		{
			this.Main.remove(this);
			this.Main.add(new newGamePanel());
			this.Main.revalidate();
			this.Main.repaint();
		}
	}

	

	

}
