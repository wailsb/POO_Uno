package Game.Uno;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;

class MainWindow {
	private JFrame MainWindow;
	private String IconPath=System.getProperty("user.dir")+"\\src\\resources\\Icon.png";
	MainWindow(){

		MainWindow = new JFrame();
		MainWindow.setTitle("Uno Game");
		MainWindow.setResizable(true);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ImageIcon myImg=new ImageIcon(this.IconPath);
		WelcomePanel welcomePanel=new WelcomePanel(MainWindow);

		
		MainWindow.setIconImage(myImg.getImage());
		MainWindow.add(welcomePanel);

		MainWindow.setMinimumSize(new Dimension(500,600));
		MainWindow.setSize(600,600);
		MainWindow.setVisible(true);
		
	}
	
}
