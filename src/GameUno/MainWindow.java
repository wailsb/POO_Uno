package GameUno;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

class MainWindow {
	private JFrame MainWindow;
	private String IconPath=System.getProperty("user.dir")+"\\src\\resources\\Icon.png";
	MainWindow(){

		ImageIcon myImg=new ImageIcon(this.IconPath);
		WelcomePanel welcomePanel=new WelcomePanel();
		MainWindow = new JFrame();
		MainWindow.setTitle("Uno Game");
		MainWindow.setIconImage(myImg.getImage());
		MainWindow.setSize(600,600);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.add(welcomePanel);
		MainWindow.setVisible(true);
		
	}
	
}
