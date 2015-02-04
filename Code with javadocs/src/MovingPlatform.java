
import java.io.IOException;


public class MovingPlatform extends Platform
{

	int time;
	/*
	 *	Constructs a moving platform
	 *
	 *	@param x 	     starting x coordinate
	 *	@param y		 starting y coordinate
	 *	@param height  height of platform
	 *	@param width   width of platform
	 *	@param dad     map platform is drawn on
	 */
	public MovingPlatform(int x, int y, int width, int height, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		speed = 1;
		time = 0;
	}
	
	/*
	 *	Construcs a moving platform with current time
	 *
	 *	@param x 	     	 starting x coordinate
	 *	@param y		     starting y coordinate
	 *	@param height  	 height of platform
	 *	@param width  	 width of platform
	 *	@param dad    	 map platform is drawn on
	 *	@param currentTime current time
	 */
	public MovingPlatform(int x, int y, int width, int height, Map dad, int currentTime) throws IOException
	{
		super(x, y, width, height, dad);
		speed = 1;
		time = currentTime;
	}
	
	/*
	 *	Construcs a moving platform with current time and direction
	 *
	 *	@param x 	     	 starting x coordinate
	 *	@param y		     starting y coordinate
	 *	@param height  	 	 height of platform
	 *	@param width  	   	 width of platform
	 *	@param dad    		 map platform is drawn on
	 *	@param currentTime	 current time
	 *	@param direction	 direction the platform moves
	 */
	public MovingPlatform(int x, int y, int width, int height, Map dad, int currentTime, int direction) throws IOException
	{
		super(x, y, width, height, dad);
		speed = 1;
		time = currentTime;
		this.direction = direction;
	}	
	
	/*
	 *	Changes the direction platform moves
	 */
	public void onTick()
	{
		super.onTick();
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
				direction =  0;
				time = 0;
			}
		}
		if(direction == 0)
		{
			left();
			if(hitcher!= null)
			{
				hitcher.left(speed);
			}
			
		}
		else if(direction == 1)
		{
			right();
			if(hitcher!=null)
			{
				hitcher.right(speed);	
			}
			
		}
		
	}
}
