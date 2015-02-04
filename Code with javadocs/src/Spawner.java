
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Spawner extends Entity{

	int time;
	
	/**
	 *Constructs for a spawner
	 *
	 *@param x			starting x component
	 *@param y			starting y component
	 *@param width		width of spawner
	 *@param height		height of spawner
	 *@param dad		map of spawner
	 */
	public Spawner(int x, int y, int width, int height, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		passableReal = true;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("portal.png");
		spriteName = "portal.png";
		try {
			sprite = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	/**
	 *Duration until next spawn
	 */
	public void onTick()
	{
		super.onTick();
		
		time++;
		if(time>100)
		{
			//System.out.println("reached");
			try {
				Bot bot = new Bot(getX() + 100, getY(), 56, 65, parent);
				Runner.addQueue.add(bot);
				//parent.queue.add(bot);
				//parent.add(bot);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("Passed");
			time = 0;
		}
		//System.out.println("yo");
		
	}
	
	
}
