package model.skin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import core.Game;
import util.MathUtil;

public class SkinManager {

	private Skin skin;
	private int angle;
	private Image upperImage;
	private Image lowerImage;
	private int x, y;
	private int rotationX, rotationY = 0;
	
	public SkinManager(String name) {
		skin = new Skin(name);
		upperImage = skin.getUpper();
		lowerImage = skin.getLower();
	}
	
	public void render(Graphics g, int newX, int newY) {
		this.x = newX-(4*Game.TILE_SIZE/5);
		this.y = newY-(4*Game.TILE_SIZE/5);
		g.drawImage(lowerImage, x, y+(4*Game.TILE_SIZE/5), null);
		g.drawImage(upperImage, x+ rotationX, y + rotationY, null);
	}
	
	public void rotate(int mouseX, int mouseY){
		// if mouse is to the right of character
		if(mouseX >= x+upperImage.getWidth(null)/2) {
			double angle = MathUtil.angleBetweenPoints(x+upperImage.getWidth(null)/2, y+upperImage.getHeight(null)/2, mouseX, mouseY);
			upperImage = rotateImage((BufferedImage) skin.getUpper(), angle);
			lowerImage = skin.getLower();
			
			if(Math.abs(Math.toDegrees(angle)) <= 45){
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*(Math.abs(Math.toDegrees(angle))/(45)));
				if(y < mouseY) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 *(Math.abs(Math.toDegrees(angle))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *(Math.abs(Math.toDegrees(angle))/(45)) - Game.TILE_SIZE*0.3);
				}
			}
			else {
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				if(y < mouseY) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 * ((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *((90.0-Math.abs(Math.toDegrees(angle)))/(45)) - Game.TILE_SIZE*0.3);
				}
			}
		}
		// if mouse is to the left of character
		else {
			double angle = MathUtil.angleBetweenPoints(mouseX, mouseY, x+upperImage.getWidth(null)/2, y+upperImage.getHeight(null)/2);
			upperImage = rotateImage(createFlipped((BufferedImage) skin.getUpper()),angle);
			lowerImage = createFlipped((BufferedImage) skin.getLower());
			
			
			if(Math.abs(Math.toDegrees(angle)) <= 45){
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*(Math.abs(Math.toDegrees(angle))/(45)));
				if(y < mouseY) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 *(Math.abs(Math.toDegrees(angle))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *(Math.abs(Math.toDegrees(angle))/(45)) + Game.TILE_SIZE*0.3);
				}
				
			}
			else {
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				if(y < mouseY) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 * ((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *((90.0-Math.abs(Math.toDegrees(angle)))/(45)) + Game.TILE_SIZE*0.3);
				}
				
			}
		}
	}
	
	public void rotate(double angle, boolean rightSide, boolean topSide){
		// if mouse is to the right of character
		if(rightSide) {
			upperImage = rotateImage((BufferedImage) skin.getUpper(), angle);
			lowerImage = skin.getLower();
			
			if(Math.abs(Math.toDegrees(angle)) <= 45){
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*(Math.abs(Math.toDegrees(angle))/(45)));
				if(topSide) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 *(Math.abs(Math.toDegrees(angle))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *(Math.abs(Math.toDegrees(angle))/(45)) - Game.TILE_SIZE*0.3);
				}
			}
			else {
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				if(topSide) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 * ((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *((90.0-Math.abs(Math.toDegrees(angle)))/(45)) - Game.TILE_SIZE*0.3);
				}
			}
		}
		// if mouse is to the left of character
		else {
			upperImage = rotateImage(createFlipped((BufferedImage) skin.getUpper()),angle);
			lowerImage = createFlipped((BufferedImage) skin.getLower());
			
			
			if(Math.abs(Math.toDegrees(angle)) <= 45){
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*(Math.abs(Math.toDegrees(angle))/(45)));
				if(topSide) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 *(Math.abs(Math.toDegrees(angle))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *(Math.abs(Math.toDegrees(angle))/(45)) + Game.TILE_SIZE*0.3);
				}
				
			}
			else {
				rotationY = (int)((double)Game.TILE_SIZE * -0.5*((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				if(topSide) {
					rotationX = (int)((double)Game.TILE_SIZE * -0.5 * ((90.0-Math.abs(Math.toDegrees(angle)))/(45)));
				}else {
					rotationX = (int)((double)Game.TILE_SIZE * -0.55 *((90.0-Math.abs(Math.toDegrees(angle)))/(45)) + Game.TILE_SIZE*0.3);
				}
				
			}
		}
	}
	
	
	
	public static BufferedImage rotateImage(BufferedImage image, double radians) {
		
        // Original image dimensions
        int width = image.getWidth();
        int height = image.getHeight();
        
        // Calculate new dimensions for the rotated image
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);

        // Create a new image with the calculated dimensions and transparency
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        
        // Set up rotation transformation centered on the image center
        AffineTransform transform = new AffineTransform();
        transform.translate((newWidth - width) / 2, (newHeight - height) / 2);
        transform.rotate(radians, width * (50.0/96.0), height * (47.0/96.0));
        
        // Apply the transformation and draw the original image onto the rotated image
        g2d.drawImage(image, transform, null);
        g2d.dispose();
        
        return rotatedImage;
    }
	
	private static BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }
	
	private static BufferedImage createTransformed(BufferedImage image, AffineTransform at)
	{
	        BufferedImage newImage = new BufferedImage(
	            image.getWidth(), image.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g = newImage.createGraphics();
	        g.transform(at);
	        g.drawImage(image, 0, 0, null);
	        g.dispose();
	        return rotateImage(newImage, Math.PI);
	}
	
}
