

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Player extends Entity{
	
	String sprit;
	String spritRight;
	String spritLeft;
	
	/*
	 *	Constructs for player
	 */
	public Player() throws IOException
	{
		super();
	}
	
	/*
	 *	Constructs a player
	 *
	 *	@param par map of player
	 */
	public Player(Map par)
	{
		super(par);
	}
	
	/*
	 *	Constructs player
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of player
	 *	@param height		height of player
	 *	@param dad			map of player
	 */
	public Player(int x, int y, int width, int height, Map par) throws IOException
	{
		super(x, y, width, height, par);
		sprit = "foxright2.png";
		spritRight = "foxright2.png";
		spritLeft = "foxleft2.png";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(sprit);
		sprite = ImageIO.read(input);
		spriteRight = sprite;
		input = classLoader.getResourceAsStream(spritLeft);
		spriteLeft = ImageIO.read(input);		
	}
	
	/*
	 *	Constructs player
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of player
	 *	@param height		height of player
	 *	@param spriLeft		Left side of sprite
	 *	@paaram spriRight	Right sie of sprite
	 *	@param dad			map of player
	 */
	public Player(int x, int y, int width, int height, String spriLeft, String spriRight, Map par) throws IOException
	{
		super(x, y, width, height, par);
		if(spriRight == null)
		{
			spritRight = "foxright2.png";
			sprit = "foxright2.png";
		}
		else
		{
			sprit = spriRight;
			spritRight = spriRight;			
		}
		if(spriLeft == null)
		{
			spritLeft = "foxleft2.png";
		}
		else
		{
			spritLeft = spriLeft;	
		}
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(sprit);
		sprite = ImageIO.read(input);
		spriteRight = sprite;
		input = classLoader.getResourceAsStream(spritLeft);
		spriteLeft = ImageIO.read(input);		
	}	
	
	/*
	 * Accerlation of player falling
	 */
	public void accel()
	{
		if(tethered)
			return;
		if(Math.abs(v_x) > 0)
		{
			boolean negative = false;
			if(v_x < 0)
				negative = true;
		
			
			
			if(negative)
				v_x = v_x + airRes;
			else
				v_x = v_x - airRes;	
			
			right(v_x);
			//System.out.println(v_x);
		}
		if(Math.abs(v_y) > 0)
		{
			boolean negative = false;
			if(v_y < 0)
				negative = true;
		
			
			
			if(negative)
				v_y = v_y + airRes;
			else
				v_y = v_y - airRes;	
			
			down(v_y);
			//System.out.println(v_x);
		}		
		
	}
	
}
