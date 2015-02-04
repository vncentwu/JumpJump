
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;



public class Bot extends Entity{
	
	Point previous;
	int time;
	
	/*
	 *	Constructs a bot
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of bot
	 *	@param height		height of bot
	 *	@param dad			map of the bot
	 */
	public Bot(int x, int y, int width, int height, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		ID = 1;
		/*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("foxright2.png");
		sprite = ImageIO.read(input);
		spriteRight = sprite;
		input = classLoader.getResourceAsStream("foxleft2.png");
		spriteLeft = ImageIO.read(input);	*/
		
		sprite = ImageLoader.bot;
		spriteLeft = ImageLoader.botLeft;
		spriteRight = ImageLoader.bot;
		
		previous = new Point((int)(mesh.getX()), (int)(mesh.getY()));
	}
	
	/*
	 *	Constructs for a bot
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of bot
	 *	@param height		height of bot
	 *	@param time			how long the bot moves
	 *	@param direction	direction the bot is moving
	 *	@param dad			map of the bot
	 */
	public Bot(int x, int y, int width, int height, int time, int direction, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		ID = 1;
		/*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("foxright2.png");
		sprite = ImageIO.read(input);
		spriteRight = sprite;
		input = classLoader.getResourceAsStream("foxleft2.png");
		spriteLeft = ImageIO.read(input);	*/
		previous = new Point((int)(mesh.getX()), (int)(mesh.getY()));
		sprite = ImageLoader.bot;
		spriteLeft = ImageLoader.botLeft;
		spriteRight = ImageLoader.bot;	
		
		this.time = time;
		this.direction = direction;
	}
	
	/*
	 *	Changes the direction of the bot
	 */
	public void changeDirection()
	{
		if(mesh.getLocation().equals(previous))
		{
			time++;
			if(time>=40)
			{
				
				if(direction == 0)
				{
					direction = 1;
					time = 0;
				}
				else if(direction == 1)
				{
					direction = 0;
					time = 0;
				}
								
			}
		}
		else
		{
			time = 0;
		}

	}
	
	/*
	 *	Duration until turn of the bot
	 */
	public void onTick()
	{
		super.onTick();
		changeDirection();
		gravity(speed);
		if(direction == 0)
		{
			left();
		}
		else if(direction == 1)
		{
			right();
		}
			
		previous = new Point((int)(mesh.getX()), (int)(mesh.getY()));
	}
	
}
