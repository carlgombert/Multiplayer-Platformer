package view.tile;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private boolean collision = false;

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	public boolean isCollision() {
		return collision;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
