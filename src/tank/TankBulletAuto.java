package tank;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TankBulletAuto extends TankBullet {

	public TankBulletAuto(int x, int y, int dir ,int camp) {
		super(x, y, dir);
		this.camp = camp;
	}
	
	public void drawBulletAuto(Image player1,Image player2, JPanel p,Graphics g ,int[][] map){
		
		if(map[y][x] == 0 || map[y][x] == 2 || map[y][x] == 3 || map[y][x] == 5 || map[y][x] == 9){
			temp++;
		}
		
		if(temp > 0){
			if(map[y][x] == 9){
				map[y][x] = 6;
			}
			g.drawImage(player2, x << 5, y << 5, (x+1) << 5, (y+1) << 5, (0+step) <<5, 0 <<5, 
					(1+step) <<5, 1 << 5, p);
			step++;
		}else{
			
			g.drawImage(player1, x << 5, y << 5, (x+1) << 5, (y+1) << 5, (0+dir) << 5, (0+step) <<5, 
					(1+dir) << 5, (1+step) <<5, p);
			action(map);
		}
	}

	
		

}
