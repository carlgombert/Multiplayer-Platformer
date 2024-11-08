package model.skin;

import java.awt.Image;

import core.Game;
import util.ImageUtil;

public class Skin {
	private Image upper;
	private Image lower;
	private int scale = (int)Math.round(2.5*Game.TILE_SIZE);
	
	public Skin(String name) {
		upper = ImageUtil.resize(ImageUtil.addImage(Game.TILE_SIZE, Game.TILE_SIZE, "resources/skins/"+ name +"/upper.png"), scale, scale);
		lower = ImageUtil.resize(ImageUtil.addImage(Game.TILE_SIZE, Game.TILE_SIZE, "resources/skins/"+ name +"/lower.png"), scale, scale);
	}

	public Image getUpper() {
		return upper;
	}

	public void setUpper(Image upper) {
		this.upper = upper;
	}

	public Image getLower() {
		return lower;
	}

	public void setLower(Image lower) {
		this.lower = lower;
	}
	
	
}
