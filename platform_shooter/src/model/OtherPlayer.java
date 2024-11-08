package model;

import java.awt.Graphics;

import core.Game;

public class OtherPlayer extends Player{

	public OtherPlayer(String skin) {
		super(skin);
		super.setScreenX(worldX - super.getWorldX() + super.getScreenX());
		super.setScreenY(worldY - super.getWorldY() + super.getScreenY());
	}
	
	public OtherPlayer(String skin, int id) {
		super(skin);
		super.setScreenX(worldX - super.getWorldX() + super.getScreenX());
		super.setScreenY(worldY - super.getWorldY() + super.getScreenY());
		super.setId(id);
	}
	
	public void render(Graphics g) {
		Player player = Game.playerHandler.getPlayer();
		super.setScreenX(worldX - player.getWorldX() + player.getScreenX());
		super.setScreenY(worldY - player.getWorldY() + player.getScreenY());
		
		super.sm.render(g, screenX, screenY);
	}

}
