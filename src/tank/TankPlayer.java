package tank;

import gameframe.GamePanel;

import java.awt.event.KeyEvent;

public class TankPlayer extends Tank {

	TankBulletPlayer tbp;

	public TankPlayer(int x, int y, int camp) {
		super(x, y);
		this.camp = camp;
	}

	public void action(KeyEvent e, int[][] map) {
		if (!GamePanel.isOver) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				this.upDir(map);
				break;
			case KeyEvent.VK_A:
				this.leftDir(map);
				break;
			case KeyEvent.VK_S:
				this.downDir(map);
				break;
			case KeyEvent.VK_D:
				this.rightDir(map);
				break;
			case KeyEvent.VK_SPACE:
				this.createBulletPlayer();
				break;
			default:
				break;
			}
		}

	}

	private void createBulletPlayer() {
		createBullet();
		tbp = new TankBulletPlayer(bx, by, bDir, 7);
		GamePanel.bulletPlayer.add(tbp);
	}

}
