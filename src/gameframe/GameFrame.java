package gameframe;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public GameFrame(){
		
		//设置窗体的大小
		this.setSize(1104,774);
//		获取屏幕大小，设置居中
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) ((dm.getWidth() - 1120) / 2);
		int height = (int) ((dm.getHeight() - 768) / 2);
		this.setLocation(width , height);
		this.setContentPane(new GamePanel());
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
