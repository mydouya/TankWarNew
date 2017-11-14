package tank;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Tank {
	
	public int x;
	public int y;
	public int step;
	public int dir;
	public int camp;
	public int camps;
	public int bx;
	public int by;
	public int bDir;
	public int oldCoor = 1;
	public int nowCoor = 1;
	
	public Tank(int x , int y){
		this.x = x;
		this.y = y;
		this.step = 0;
		this.dir = 0;
	}
	
	public void drawTank(Image player, JPanel p,Graphics g){
		
		g.drawImage(player, x << 5, y << 5, (x+1) << 5, (y+1) << 5, (0+step) << 5, (0+dir) <<5, 
				(1+step) << 5, (1+dir) <<5, p);
		step();
	}
	
	public void step() {
		step++;
		if (step > 1) {
			step = 0;
		}
	}
	
	public void upDir(int[][] map){
		dir = 0;
		if (map[y - 1][x] == 1 || map[y - 1][x] == 4  || map[y - 1][x] == 6 || map[y - 1][x] == 7){
			oldCoor = nowCoor;
			nowCoor = map[y][x];
			y--;
			if( map[y + 1][x] == 9 || map[y + 1][x] == 8 ){
				map[y + 1][x] = 1;
			}			
			map[y][x] = camp;
		}
		
	}
	
	public void downDir(int[][] map){
		dir = 2;
		if (map[y + 1][x] == 1 || map[y + 1][x] == 4 || map[y][x - 1] == 6 || map[y][x - 1] == 7){
			oldCoor = nowCoor;
			nowCoor = map[y][x];
			y++;
			if( map[y - 1][x] == 9 || map[y - 1][x] == 8 ){
				map[y - 1][x] = 1;
			}
		
			map[y][x] = camp;
		}
	}
	
	public void leftDir(int[][] map){
		dir = 3;
		if (map[y][x - 1] == 1 || map[y][x - 1] == 4 || map[y][x - 1] == 6 || map[y][x - 1] == 7){
			oldCoor = nowCoor;
			nowCoor = map[y][x];
			x--;
			if( map[y][x  + 1] == 9 || map[y][x  + 1] == 8 ){
				map[y][x  + 1] = 1;
			}
			
			map[y][x] = camp;
		}
	}
	
	public void rightDir(int[][] map){
		dir = 1;
		if (map[y][x + 1] == 1 || map[y][x + 1] == 4 || map[y][x + 1] == 6 || map[y][x + 1] == 7){
			oldCoor = nowCoor;
			nowCoor = map[y][x];
			x++;
			if( map[y][x  - 1] == 9 || map[y][x  - 1] == 8 ){
				map[y][x  - 1] = 1;
			}
			map[y][x] = camp;
		}
		
	}
	
    public void createBullet() {
    	bx = x;
    	by = y;
    	bDir = dir;
		switch (bDir) {
		case 0:
			by--;
			bDir = 0;
			break;
		case 3:
			bx--;
			bDir = 3;
			break;
		case 2:
			by++;
			bDir = 2;
			break;
		case 1:
			bx++;
			bDir = 1;
			break;
		default:
			break;
		}
	}
	
	
}
