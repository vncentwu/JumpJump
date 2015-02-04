import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class ImageLoader {

	
	public static BufferedImage bot;
	public static BufferedImage botLeft;
	public static BufferedImage decoration;
	public static BufferedImage entity;
	public static BufferedImage movingPlatform;
	public static BufferedImage platform;
	public static BufferedImage player;
	public static BufferedImage playerLeft;
	public static BufferedImage portal;
	public static BufferedImage spawner;
	public static BufferedImage terrain;
	public static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	public static InputStream input;
	public static BufferedImage menuBackground;
	
	
	/*
	 *	Loads the game
	 */
	public static void load() throws IOException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("foxright2.png");
		bot = ImageIO.read(input);	
		input = classLoader.getResourceAsStream("foxleft2.png");
		botLeft = ImageIO.read(input);			
		input = classLoader.getResourceAsStream("5key.png");
		decoration = ImageIO.read(input);		
		
		
		input = classLoader.getResourceAsStream("def.png");
		entity = ImageIO.read(input);
		input = classLoader.getResourceAsStream("def.png");
		movingPlatform = ImageIO.read(input);			
		input = classLoader.getResourceAsStream("def.png");
		platform = ImageIO.read(input);			
		input = classLoader.getResourceAsStream("foxright2.png");
		player = ImageIO.read(input);	
		input = classLoader.getResourceAsStream("foxleft2.png");
		playerLeft = ImageIO.read(input);	
		input = classLoader.getResourceAsStream("castledoors.png");
		portal = ImageIO.read(input);			
		input = classLoader.getResourceAsStream("portal.png");
		spawner = ImageIO.read(input);			
		input = classLoader.getResourceAsStream("def.png");
		terrain = ImageIO.read(input);			
		input = classLoader.getResourceAsStream("background1.png");
		menuBackground = ImageIO.read(input);			
	}
	
	
}
