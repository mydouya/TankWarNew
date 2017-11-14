package tank;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TankBulletPlayer extends TankBullet {

	public TankBulletPlayer(int x, int y, int dir , int camp) {
		super(x, y, dir);
		this.camp = camp;
	}
	
//	实现画我方子弹
	public void drawBulletPlayer(Image player1,Image player2, JPanel p,Graphics g ,int[][] map){
//		判断子弹当前坐标，判断是否爆炸
		if(map[y][x] == 0 || map[y][x] == 2 || map[y][x] == 3 || map[y][x] == 5 || map[y][x] == 8){
			temp++;
		}
		
//		判断并实现子弹和爆炸动画
		if(temp > 0){
			if(map[y][x] == 8){
				map[y][x] = 7;
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
