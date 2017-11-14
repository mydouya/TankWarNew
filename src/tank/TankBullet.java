package tank;

public class TankBullet extends Tank {
	
	public int temp = 0;
	
	public TankBullet(int x, int y ,int dir) {
		super(x, y);
		this.dir = dir;
	}
	
	public void action(int[][] map){
		
			switch (dir) {
			case 0:
					y--;
				break;
			case 3:
					x--;
				break;
			case 2:
					y++;
				break;
			case 1:
					x++;
				break;
			default:
				break;
		}
	}
	
	
	public int dieAction(int[][] map , int camp){
		int i = 0;
		
		if(map[y][x] == 3 || map[y][x] == 2){
			i = 1;	
		}else if(map[y][x] != 4 && map[y][x] != 1 /*&& map[y][x] != camp*/){
			i = 2;
		}		
		return i;
		
	}
	
}