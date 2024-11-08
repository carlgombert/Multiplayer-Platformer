package view.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import core.Game;
import model.Player;
import util.ImageUtil;


// the games map is built as a grid of a bunch of different tiles.
public class TileManager {
	public static Tile[] tile;
	public static int mapTileNum[][];
	private String mapPath = "resources/maps/map1.txt";
	
	public TileManager () {
		
		tile = new Tile [40];
		
		mapTileNum = new int[Game.MAP_ROW][Game.MAP_COL];
		loadMap();
		
		getTileImage();
	}
	
	// sets map tile types to numbers
	public void getTileImage () {
		
		// we now have 3 different grass tiles for variation
		tile [0] = new Tile();
		tile [0].setImage(ImageUtil.addImage(Game.TILE_SIZE, Game.TILE_SIZE, "resources/tile/air.png"));
		
		tile [1] = new Tile();
		tile [1].setImage(ImageUtil.addImage(Game.TILE_SIZE, Game.TILE_SIZE, "resources/tile/stone.png"));
		tile [1].setCollision(true);
		
		tile [2] = new Tile();
		tile [2].setImage(ImageUtil.addImage(Game.TILE_SIZE, Game.TILE_SIZE, "resources/tile/air.png"));
		tile [2].setCollision(true);
	}
	
	//loads in map as txt files and reads in numbers as their specified tile type
	public void loadMap() {
		try {
			URL url = getClass().getClassLoader().getResource(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			int col = 0;
			int row = 0;
			while (col < Game.MAP_COL && row < Game.MAP_ROW) {
				String line = br.readLine ();
				while (col < Game.MAP_COL) {
					String numbers[] = line.split("\t");
					int num = Integer.parseInt (numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == Game.MAP_COL) {
					col = 0;
					row++;
				}
			}
			br.close () ;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int[][] getMap()
	{
		return mapTileNum;
	}
	
	public void render (Graphics g) {
		int col = 0;
		int row = 0;
		Player player = Game.playerHandler.getPlayer();
		while (col < Game.MAP_COL && row < Game.MAP_ROW) {
			int tileNum = mapTileNum[col][row];
			
			int worldX = col * Game.TILE_SIZE;
			int worldY = row * Game.TILE_SIZE;
			
			int screenX = worldX - player.getWorldX() + player.getScreenX();
			int screenY = worldY - player.getWorldY() + player.getScreenY();
			
			// if the tile is outside of the players view, do not render.
			if(worldX > player.getWorldX() - Game.WIDTH &&
					worldX < player.getWorldX() + Game.WIDTH &&
					worldY > player.getWorldY() - Game.HEIGHT &&
					worldY < player.getWorldY() + Game.HEIGHT)
			{
				g.drawImage(tile[tileNum].getImage(), screenX, screenY, Game.TILE_SIZE, Game.TILE_SIZE, null);
			}
			
			col++;
			
			if (col == Game.MAP_COL) {
				col = 0;
				row++;
			}
		}
	}
}
