package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Game;
import model.skin.SkinManager;
import util.MathUtil;
import view.tile.TileUtil;

public class Player {
	// object identifier
	protected int id;
	
	// boolean signifying if the object is colliding with a tile or not
	protected boolean XtileCollision;
	protected boolean bottomYtileCollision;
	protected boolean topYtileCollision;

	// world coordinates represent the position of the object on the broader map
	protected int worldX, worldY;
	protected int speedX, speedY;
	
	// screen coordinates represent where the object should be rendered
	protected int screenX, screenY;
	
	private int ammo = 10;
	
	private int right = 0; // variables representing the player's speeds in certain direction
	private int left = 0;
	private double up = 0;
	private int down = 0;
	
	private final double GRAVITY = 0.5;
	
	protected SkinManager sm;
	
	private int mouseX, mouseY;
	
	// the direction the object is facing
	protected int direction = 0;
	/*
	 * front: 0
	 * back: 1
	 * left: 2
	 * right: 3
	 */
	
	private String skinName;
	
	public Player(String skin) {
		skinName = skin;
		this.id = MathUtil.randomNumber(10000, 99999);
		
		setScreenX((Game.WIDTH / 2) - getSize().width/2);
		setScreenY((Game.HEIGHT / 2)- getSize().height/2);
		
		worldX = MathUtil.randomNumber(50,53)*Game.TILE_SIZE;
		worldY = 79*Game.TILE_SIZE;
		
		sm = new SkinManager(skin);
	}

	public void tick() {
		speedX = left + right; // better way of calculating speed
		speedY = (int)Math.round(up);
		
		TileUtil.checkTileCollision(this);
		
		if (!XtileCollision) worldX += speedX;
		if (!bottomYtileCollision) {
			worldY += speedY;
			up += GRAVITY;
		}
		if (topYtileCollision) {
			up = Math.max(0, up);
			worldY += GRAVITY;
			up += GRAVITY;
		}
		
	}
	
	public void render(Graphics g) {
		sm.render(g, screenX, screenY);
		
		g.setColor(Color.red);
		
		g.drawLine(screenX + Game.TILE_SIZE/2, screenY + Game.TILE_SIZE/2, mouseX, mouseY);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getScreenX()+5, getScreenY()+5, getSize().width-10, getSize().height-5);
	}
	
	public Rectangle getHeadBounds() {
		return new Rectangle(getScreenX()+2, getScreenY()-10, getSize().width-4, Game.TILE_SIZE/2);
	}
	
	public Rectangle getSize() {
		return new Rectangle(Game.TILE_SIZE, Game.TILE_SIZE*2);
	}
	
	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public int getSpeedX() {
		return speedX;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setUp(int var)
	{
		up = var;
	}
	
	public void setDown(int var)
	{
		down = var;
	}
	
	public void setLeft(int var)
	{
		left = var;
	}
	
	public void setRight(int var)
	{
		right = var;
	}
	public void jump()
	{
		if (bottomYtileCollision) {
			worldY += -12;
			up = -12;
		}
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getScreenX() {
		return screenX;
	}

	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}
	
	public boolean isXTileCollision() {
		return XtileCollision;
	}

	public void setXTileCollision(boolean XtileCollision) {
		this.XtileCollision = XtileCollision;
	}
	
	public boolean isTopYTileCollision() {
		return topYtileCollision;
	}

	public void setTopYTileCollision(boolean topYtileCollision) {
		this.topYtileCollision = topYtileCollision;
	}
	
	public boolean isBottomYTileCollision() {
		return bottomYtileCollision;
	}

	public void setBottomYTileCollision(boolean bottomYtileCollision) {
		this.bottomYtileCollision = bottomYtileCollision;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	public void setMouse(int x, int y) {
		sm.rotate(x, y);
		this.mouseX = x;
		this.mouseY = y;
	}
	
	public void rotateUpper(double angle, boolean rightSide, boolean topSide) {
		sm.rotate(angle, rightSide, topSide);
	}

	public String getSkinName() {
		return skinName;
	}

}
