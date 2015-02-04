import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Decoration extends Entity{

	
	/*
	 *	Constructs decoration
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of decoration
	 *	@param height		height of decoration
	 *	@param dad			map of the decoration
	 */
	public Decoration(int x, int y, int width, int height, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("5key.png");
		sprite = ImageIO.read(input);
		passableReal = true;	
		spriteName = "5key.png";
	}	
	
	/*
	 *	Constructs for decoration
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of decoration
	 *	@param height		height of decoration
	 *	@param spriteName	name of the sprite
	 *	@param dad			map of the decoration
	 */
	public Decoration(int x, int y, int width, int height, String spriteName, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		
		int i = (int)(Math.random()*2);
		/*if(i == 0)
			spriteName = "heat.png";
		else if(i == 1)
			spriteName = "q1framed.png";*/
	    try
	    {
	    	sprite = ImageIO.read(this.getClass().getResource(spriteName));

	       
	    } catch (IOException e)
	    {
	    	sprite = ImageLoader.entity;
	        e.printStackTrace();
	    }
		
		
		/*InputStream in = getClass().getResource(spriteName); 
		if(in == null)
			sprite = ImageLoader.entity;
		else
			sprite = ImageIO.read(in);
		*/
		
		/*mageLoader.input = ImageLoader.classLoader.getResourceAsStream(spriteName);
		if(ImageLoader.input == null)
			sprite = ImageLoader.entity;
		else
		{
			this.spriteName = spriteName;
			sprite = ImageIO.read(ImageLoader.input);
						
		}*/
		passableReal = true;
	}		
	
	
}
