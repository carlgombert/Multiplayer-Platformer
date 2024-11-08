package core;

import java.awt.Graphics;
import java.util.LinkedList;

import model.Player;
import util.MathUtil;

public class PlayerHandler {
	
	private static Player mainPlayer;
	private LinkedList<Player> players = new LinkedList<Player>();
	private String[] skins = {"raven", "camo_jack", "ricky"};
	
	public PlayerHandler() {
		mainPlayer = new Player(skins[MathUtil.randomNumber(0, skins.length)]);
		addPlayer(mainPlayer); 
	}

	
	public void tick() {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).tick();
		}
	}
	
	public void render(Graphics g) 
	{
		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).render(g);
		}
		
	}
	
	public void addPlayer(Player addedPlayer) 
	{
		players.add(addedPlayer);
	}
	
	public void removePlayer(Player removedPlayer) {
		players.remove(removedPlayer);
	}

	public LinkedList<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer() {
		return mainPlayer;
	}
	
	public Player getPlayer(int id) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getId() == id) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public boolean playerExists(int id) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getId() == id) {
				return true;
			}
		}
		return false;
		
	}

}
