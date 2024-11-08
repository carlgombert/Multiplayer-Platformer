package core.multiplayer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import core.Game;
import core.Game.GameState;
import model.OtherPlayer;
import model.Player;
import util.Sound;

public class ServerInput {
	
	public static boolean decodeMessage(String message) {
		String[] msgsplit = message.split(" ");
		if(msgsplit[0].equals("/init")) {
			int id = Integer.parseInt(msgsplit[1]);
			if(!Game.playerHandler.playerExists(id)) {
				System.out.println(msgsplit[2]);
				Game.playerHandler.addPlayer(new OtherPlayer(msgsplit[2], Integer.parseInt(msgsplit[1])));
				Game.playerHandler.getPlayer(id).setWorldX(Integer.parseInt(msgsplit[3]));
				Game.playerHandler.getPlayer(id).setWorldY(Integer.parseInt(msgsplit[4]));
				return true;
			}
			return false;
		}
		else {
			int id = Integer.parseInt(msgsplit[0]);
			if(id != Game.playerHandler.getPlayer().getId()) {
				if(msgsplit[1].equals("/keypress")) {
					keyPressed(id, Integer.parseInt(msgsplit[2]));
					return false;
				}
				else if(msgsplit[1].equals("/keyrelease")) {
					keyReleased(id, Integer.parseInt(msgsplit[2]));
					return false;
				}
				else if(msgsplit[1].equals("/mousemove")) {
					mouseMoved(id, Integer.parseInt(msgsplit[2]), Integer.parseInt(msgsplit[3]));
					return false;
				}
				else if(msgsplit[1].equals("/update")) {
					update(id, Integer.parseInt(msgsplit[2]), Integer.parseInt(msgsplit[3]));
					return false;
				}
			}
		}
		return false;
	}
	
	public static void keyPressed(int id, int key) {
		Player player = Game.playerHandler.getPlayer(id);
		
		if(Game.gamestate == GameState.Running) {
				
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) player.jump();
			if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) player.setLeft(-5);
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) player.setRight(5);
			
			if(key == KeyEvent.VK_R) 
			{
				player.setAmmo(10);
			}
		}
	}
	
	public static void keyReleased(int id, int key) {
		Player player = Game.playerHandler.getPlayer(id);

		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) player.setLeft(0);
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) player.setRight(0);
	}

	public static void mouseMoved(int id, int x, int y)
	{
		Player player = Game.playerHandler.getPlayer(id);
		player.setMouse(x, y);
	}
	
	public static void update(int id, int x, int y) {
		Player player = Game.playerHandler.getPlayer(id);
		player.setWorldX(x);
		player.setWorldY(y);
	}
}
