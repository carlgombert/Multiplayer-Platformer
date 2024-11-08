package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import core.Game.GameState;
import core.multiplayer.Client;
import model.Player;
import util.Sound;

public class Input extends KeyAdapter implements MouseListener, MouseMotionListener, ActionListener{
	private boolean canShoot = true;
	private boolean canReload = true;
	
	private Client client = new Client();
	
	private int mouseX;
	private int mouseY;
	
	public Input() {
		Thread t = new Thread(client);
		t.start();
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**
     * After mouse release, Resets flags for various actions to allow them in the next frame.
     */
	public void mouseReleased(MouseEvent e) 
	{
		canShoot = true;
	}
	
	public void mousePressed(MouseEvent e) 
	{
		Player player = Game.playerHandler.getPlayer();
		if(Game.gamestate == GameState.Running) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if (canShoot == true && player.getAmmo() != 0)
				{
					
					// calculates the mouse X and Y relative to the player
					mouseX = (e.getX() - player.getScreenX());
					mouseY = (e.getY() - player.getScreenY());
					
					// calculates the angle between the player and the current mouse location
					double angle = Math.atan2(mouseY, mouseX); // atan2 seems to work better than atan, idk why
		
					// create bullet
					Sound.pistolSound();
					
					canShoot = false; // prevents the player from being able to shoot infinitely fast by holding down the mouse button
					player.setAmmo(player.getAmmo() - 1); // remove 1 from the player's ammo
				}
				/*else if(canShoot == true && player.getAmmo() == 0){
					Sound.emptySound();
				}*/
			
			}
		}
		/*if(Game.gamestate == GameState.MainMenu) {
			MainMenu.checkButton(e.getX(), e.getY());
		}
		if(Game.gamestate == GameState.Paused) {
			PauseMenu.checkButton(e.getX(), e.getY());
		}*/
	}
	
	public void keyPressed(KeyEvent e) {
		Player player = Game.playerHandler.getPlayer();
		
		if(Game.gamestate == GameState.Running) {
			int key = e.getKeyCode();
				
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) player.jump();
			//if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) player.setDown(5);
			if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) player.setLeft(-5);
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) player.setRight(5);
			
			if(key == KeyEvent.VK_R && canReload == true) // player reload
			{
				player.setAmmo(10);
				canReload = false;
				Sound.reloadSound();
			}
			
			client.sendMessage(player.getId() + " /keypress " + e.getKeyCode());
			client.sendMessage(player.getId() + " /update " + player.getWorldX() + " " + player.getWorldY());
		}
	}
	
	public void keyReleased(KeyEvent e) {
		Player player = Game.playerHandler.getPlayer();
		
		int key = e.getKeyCode();

		//if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W)) player.setUp(0);
		//if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) player.setDown(0);
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) player.setLeft(0);
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) player.setRight(0);
		
		if(key == KeyEvent.VK_R) canReload = true;
		
		client.sendMessage(player.getId() + " /keyrelease " + e.getKeyCode());
		client.sendMessage(player.getId() + " /update " + player.getWorldX() + " " + player.getWorldY());
	}

	public void mouseMoved(MouseEvent e)
	{
		Player player = Game.playerHandler.getPlayer();
		player.setMouse(e.getX(), e.getY());
		client.sendMessage(player.getId() + " /mousemove " + e.getX() + " " + e.getY());
		client.sendMessage(player.getId() + " /update " + player.getWorldX() + " " + player.getWorldY());
	}
	
	public void mouseDragged(MouseEvent e) 
	{
		
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
