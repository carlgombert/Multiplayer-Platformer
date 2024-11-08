package view.tile;

import core.Game;
import model.Player;

/**
 * Utility functions tiles. mainly used to check player-tile collisions
 */
public class TileUtil {
	
	public static void checkTileCollision(Player player) {
        
		// updated the below commented code to use getbounds instead of getsize, so we can use collision with their hitboxes
		int playerLeftWorldX = player.getWorldX() + player.getBounds().x - player.getScreenX();
		int playerRightWorldX = player.getWorldX() + player.getBounds().x + player.getBounds().width - player.getScreenX();
		int playerTopWorldY = player.getWorldY() + player.getBounds().y - player.getScreenY();
		int playerBottomWorldY = player.getWorldY() + player.getBounds().y + player.getBounds().height - player.getScreenY();
		
        int playerLeftCol = playerLeftWorldX / Game.TILE_SIZE;
        int playerRightCol = playerRightWorldX / Game.TILE_SIZE;
        int playerTopRow = playerTopWorldY / Game.TILE_SIZE;
        int playerBottomRow = playerBottomWorldY / Game.TILE_SIZE;

        int tileNum1, tileNum2;

        playerTopRow = (playerTopWorldY + player.getSpeedY()) / Game.TILE_SIZE;

        tileNum1 = TileManager.mapTileNum[playerLeftCol][playerTopRow];
        tileNum2 = TileManager.mapTileNum[playerRightCol][playerTopRow];

        if (TileManager.tile[tileNum1].isCollision() || TileManager.tile[tileNum2].isCollision()) 
        {
            player.setTopYTileCollision(true);
        }
        else {
        	player.setTopYTileCollision(false);
        }
        
    
        
        
        playerBottomRow = (playerBottomWorldY + player.getSpeedY()) / Game.TILE_SIZE;

        tileNum1 = TileManager.mapTileNum[playerLeftCol][playerBottomRow];
        tileNum2 = TileManager.mapTileNum[playerRightCol][playerBottomRow];

        if (TileManager.tile[tileNum1].isCollision() || TileManager.tile[tileNum2].isCollision()) 
        {
            player.setBottomYTileCollision(true);
            int margin = (playerBottomWorldY) % Game.TILE_SIZE - 22;
            player.setWorldY(player.getWorldY()-margin);
        }
        else player.setBottomYTileCollision(false);
            
        
        
        playerTopRow = playerTopWorldY / Game.TILE_SIZE; // resets these for when checking x tile collision
        playerBottomRow = playerBottomWorldY / Game.TILE_SIZE;
        
        if(player.getSpeedX() < 0) {
            playerLeftCol = (playerLeftWorldX + player.getSpeedX()) / Game.TILE_SIZE;

            tileNum1 = TileManager.mapTileNum[playerLeftCol][playerTopRow];
            tileNum2 = TileManager.mapTileNum[playerLeftCol][playerBottomRow];

            if (TileManager.tile[tileNum1].isCollision() || TileManager.tile[tileNum2].isCollision()) 
            {
                player.setXTileCollision(true);
            }
            else player.setXTileCollision(false);
        }
        
        if(player.getSpeedX() > 0) {
            playerRightCol = (playerRightWorldX + player.getSpeedX()) / Game.TILE_SIZE;

            tileNum1 = TileManager.mapTileNum[playerRightCol][playerTopRow];
            tileNum2 = TileManager.mapTileNum[playerRightCol][playerBottomRow];

            if (TileManager.tile[tileNum1].isCollision() || TileManager.tile[tileNum2].isCollision()) 
            {
                player.setXTileCollision(true);
            }
            else player.setXTileCollision(false);
        }
    }
}
