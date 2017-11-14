package gameframe;

import gamemap.GameMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tank.TankAuto;
import tank.TankBulletAuto;
import tank.TankBulletPlayer;
import tank.TankPlayer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	
//	创建图片变量
	private Image ground;
	private Image playerImg;
	private Image autoImg;
	private Image bullet;
	private Image bulletBoom; 
	private Image star; 
	private Image isWin;
	private Image notWin;
//	创建坦克对象
	private TankPlayer player = new TankPlayer(13,21,9);
	private TankAuto Autoer;
//	记录敌方坦克数量变量
	private int tankWh;
	private int tankCount;
//	记录胜败变量
	public static boolean isOver = false;
	public static boolean win = false;
//	创建坦克和子弹集合
	public static Vector<TankAuto> tanks = new Vector<TankAuto>();
	public static Vector<TankBulletPlayer> bulletPlayer = new Vector<TankBulletPlayer>();
	public static Vector<TankBulletAuto> bulletAuto = new Vector<TankBulletAuto>();
	
//	导入地图map
	public static int[][] map = GameMap.map;
	public MediaTracker mt;

//	初始化主面板
	public GamePanel(){
		this.setBackground(Color.BLACK);
		try {
			ground = ImageIO.read(new File("image/_com.bmp"));
			
			playerImg = ImageIO.read(new File("image/_player.bmp"));
			autoImg = ImageIO.read(new File("image/_enemy.bmp"));
			bullet = ImageIO.read(new File("image/bullet.bmp"));
			bulletBoom = ImageIO.read(new File("image/boom.png"));
			star = ImageIO.read(new File("image/_flash.bmp"));
			isWin = ImageIO.read(new File("image/success.png"));
			notWin = ImageIO.read(new File("image/fail.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mt = new MediaTracker(this);
		mt.addImage(ground, 1);
		mt.addImage(playerImg, 9);
		mt.addImage(autoImg, 8);
		mt.addImage(bullet, 7);
		mt.addImage(bulletBoom, 6);
		mt.addImage(star, 5);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		防止失焦
		this.setFocusable(true);
//		收集按键入口
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
					player.action(e, map);
			}
		});
//		启动主线程		
		new Thread(this).start();
		
	}
	
	
//	画主面板
	private void drawMap(Graphics g){
		for(int i = 0 ; i < 23; i++){
			for(int j = 0 ; j < 34; j++){				
				if(map[i][j] == 0){					
					g.drawImage(ground, j << 5, i << 5, (j+1) << 5, (i+1) << 5, 256, 0, 288, 32, this);					
				}else if(map[i][j] == 3){					
					g.drawImage(ground, j << 5, i << 5, (j+1) << 5, (i+1) << 5, 0, 0, 32, 32, this);					
				}else if(map[i][j] == 4){					
					g.drawImage(ground, j << 5, i << 5, (j+1) << 5, (i+1) << 5, 64, 0, 96, 32, this);					
				}else if(map[i][j] == 5){					
					g.drawImage(ground, j << 5, i << 5, (j+1) << 5, (i+1) << 5, 32, 0, 64, 32, this);					
				}else if(map[i][j] == 2){					
					g.drawImage(ground, j << 5, i << 5, (j+1) << 5, (i+1) << 5, 160, 0, 192, 32, this);					
				}			
			}
		}
//		画结束画面
		if(isOver){
			if(win){
				g.drawImage(isWin, 350, 290, 690, 460, 0, 0, 359, 245,this);
			}else{
				g.drawImage(notWin, 350, 290, 690, 460, 0, 0, 359, 245,this);
			}
		}
		
	}
	
	
//	画我方坦克
	private void drawPlayer(Graphics g){
		player.drawTank(playerImg, this, g);
	}
	
//	画敌方坦克
	private void drawAuto(Graphics g){
		for(int i =0 ; i < tanks.size() ;i++){
			tanks.get(i).drawTank(autoImg,star, this, g ,map);
		}
		
	}
	
	
//	画我方子弹
	private void drawBulletPlayer(Graphics g){
		
		for(int i =0 ; i < bulletPlayer.size() ;i++){
			bulletPlayer.get(i).drawBulletPlayer(bullet, bulletBoom, this, g ,map);
		}
		
	}
	
	
//	画敌方子弹
	private void drawBulletAuto(Graphics g){
		for(int i =0 ; i < bulletAuto.size() ;i++){
			bulletAuto.get(i).drawBulletAuto(bullet, bulletBoom, this, g ,map);
		}
		
	}

	
//	画画主方法
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawMap(g);
		drawPlayer(g);
		drawAuto(g);
		drawBulletPlayer(g);
		drawBulletAuto(g);
		
	}
	
	
//	生成敌方坦克并加入集合
	private void createTank(){
		if(tankCount == 4 && tanks.size() == 0){
			isOver = true;
			win = true;
			}
		if(tanks.size() < 3 && tankCount < 4){
			if(tankWh > 2){
				tankWh = 0;
			}			
			int tj = 7 + (tankWh+6)*2;
			Autoer = new TankAuto(tj,2,8);
			tanks.add(Autoer);
			tankWh++;
			tankCount++;
		}
	} 
	
//	移除我方子弹
	private void dieBulletPlayer(){
		int temp = 0;
		for(int i =0 ; i < bulletPlayer.size() ;i++){
			temp = bulletPlayer.get(i).dieAction(map , 8);
			if(temp == 1){				
				if(bulletPlayer.get(i).temp > 8){
					bulletPlayer.remove(i);
				}				
				if(bulletPlayer.get(i).temp > 3){
					map[bulletPlayer.get(i).y][bulletPlayer.get(i).x] = 1;	
				}
			} else if(temp == 2){								
				if(bulletPlayer.get(i).temp > 8){
					bulletPlayer.remove(i);
				}		
			}				
		}
	}
	
//	移除敌方子弹
	private void dieBulletAuto(){
		int temp = 0;
		for(int i =0 ; i < bulletAuto.size() ;i++){
			temp = bulletAuto.get(i).dieAction(map , 9);
			if(temp == 1){				
				if(bulletAuto.get(i).temp > 5){
					bulletAuto.remove(i);
				}				
				if(bulletAuto.get(i).temp > 1){
					map[bulletAuto.get(i).y][bulletAuto.get(i).x] = 1;	
				}
			} else if(temp == 2){								
				if(bulletAuto.get(i).temp > 5){
					bulletAuto.remove(i);
				}		
			}				
		}
	}
	
	
//	移除敌方坦克
	private void dieTankAuto(){
		for(int i =0 ; i < tanks.size() ;i++){
			if(map[tanks.get(i).y][tanks.get(i).x] == 7){
				map[tanks.get(i).y][tanks.get(i).x] = 1;
				tanks.remove(i);				
			}
		}
	}
	
//	移除我方坦克
	private void dieTankPlayer(){
			if(map[player.y][player.x] == 6){
				map[player.y][player.x] = 1;
				isOver = true;
				win = false;
				System.out.println("炸弹失败");
			}		
	}
	
//	判断boss死亡
	public void boosOver(){
		if(map[21][17] != 2){
//			isOver = true;
//			win = false;
		}		
	}
	
	
	@Override
	public void run() {
		while(true){
			this.repaint();
			createTank();
			dieTankPlayer();
			dieBulletPlayer();
			dieBulletAuto();
			dieTankAuto();
			boosOver();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
