package tank;

import gameframe.GamePanel;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

public class TankAuto extends Tank implements Runnable {
	
	int temp = 0;
	int buttletSpeed = 0;
	int tankSpeed = 0;
	int tankDirSpeed = 0;
	TankBulletAuto tba ;

	public TankAusto(int x, int y, int camp) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.camp = camp;
	}
	
	public void drawTank(Image player,Image star,JPanel p,Graphics g ,int[][] map){
		if(temp < 5){
			g.drawImage(star, x << 5, y << 5, (x+1) << 5, (y+1) << 5, (0+step) << 5, (0+dir) <<5, 
					(1+step) << 5, (1+dir) <<5, p);
		}else{
			g.drawImage(player, x << 5, y << 5, (x+1) << 5, (y+1) << 5, (0+step) << 5, (0+dir) <<5, 
					(1+step) << 5, (1+dir) <<5, p);
			
		}
		new Thread(this).start();
	}
	
	public void stepStar(){
		step++;
		if (step > 4) {
			step = 0;
		}
	}
	
	public void checkStar(){
		temp++;
		if (step > 10) {
			step = 11;
		}
	}
	
	public void action(int[][] map){
		 Random rand=new Random();
		 if(tankDirSpeed > 5){
			 dir = rand.nextInt(4);
			 tankDirSpeed = 0;
		 }
		 tankDirSpeed++;
		 camp = 8;
		 switch (dir) {
			case 0:
				this.upDir(map);
				break;
			case 3:
				this.leftDir(map);
				break;
			case 2:
				this.downDir(map);
				break;
			case 1:
				this.rightDir(map);
				break;
			default:
				break;
			}
		 if(buttletSpeed == 1){
			 checkBulletAuto();
		 }
	}
	
	public void checkBulletAuto(){
		createBullet();
		 if(GamePanel.bulletAuto.size()>=0){
			 tba = new TankBulletAuto(bx, by ,dir ,6);
				GamePanel.bulletAuto.add(tba);
		 }
			
	}
	
	
//	public boolean setDirs(int dir , int[][] map){
//		
//		boolean temp = false ;
//		if(dir == 0 ){	
//			if( map[y - 1][x] == 1 || map[y - 1][x] == 4  || map[y - 1][x] == 6|| map[y - 1][x] == 7){
//				temp = true;
//			}
//			
//		}else if(dir == 2){
//			if( map[y + 1][x] == 1 || map[y + 1][x] == 4  || map[y + 1][x] == 8|| map[y + 1][x] == 7){
//				temp = true;
//			}
//		}else if(dir == 3){
//			if( map[y][x - 1] == 1 || map[y][x - 1] == 4  || map[y][x - 1] == 6|| map[y][x - 1] == 7){
//				temp = true;
//			}
//		}else if(dir == 1){
//			if( map[y][x + 1] == 1 || map[y][x + 1] == 4  || map[y][x + 1] == 6|| map[y][x + 1] == 7){
//				temp = true;
//			}
//		}
//		return temp;		
//	}

	@Override
	public void run() {
		if(!GamePanel.isOver){
			checkStar();
			if(temp < 5){			
				stepStar();
			}else{			
				step();	
				if(tankSpeed > 1){
					action(GamePanel.map);
					tankSpeed = 0;
				}
				tankSpeed++;
				buttletSpeed++;
				if(buttletSpeed > 4){
					buttletSpeed = 0;
				}
			}
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
