
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map{
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Entity> queue = new ArrayList<Entity>();
	BufferedImage background;
	String identifier;
	int x;
	int y;
	String name;
	
	/*
	 *	Constructs a map
	 */
	public Map() throws IOException
	{
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("background1.png");
		background = ImageIO.read(input);
		x = 0;
		y = 0;
		name = "default";
	}
	
	/*
	 *	Constructs a map
	 *
	 *	@param x	X component of map
	 *	@param y	Y component of map
	 */
	public Map(int x, int y) throws IOException
	{
		
		this.x = x;
		this.y = y;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("realscene2.jpg");
		background = ImageIO.read(input);
		name = "default";
	}
	
	/*
	 *	Constructs a map
	 *
	 *	@param x			X component of map
	 *	@param y			Y component of map
	 *	@param pictureName	name of picture
	 */
	public Map(int x, int y, String pictureName) throws IOException
	{
		
		this.x = x;
		this.y = y;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(pictureName);
		background = ImageIO.read(input);
		name = "default";
	}
	
		/*
	 *	Constructs a map
	 *
	 *	@param x			X component of map
	 *	@param y			Y component of map
	 *	@param name			name of map
	 *	@param pictureName	name of picture
	 */
	public Map(int x, int y, String name, String pictureName) throws IOException
	{
		
		this.x = x;
		this.y = y;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input;
		if(pictureName == null)
			input = classLoader.getResourceAsStream("background1.png");
		else
			input = classLoader.getResourceAsStream(pictureName);
		background = ImageIO.read(input);
		this.name = name;
	}	
	
	/*
	 *	Adds an entity to map
	 *
	 *	@param e entity to add
	 */
	public void add(Entity e)
	{
		entities.add(e);
	}
	
	/*
	 *	Adds an entity to map
	 *	
	 *	@param e entity to add
	 */
	public void add0(Entity e)
	{
		entities.add(0, e);
	}	
		
	/*
	 *	Removes an entity from map
	 *	
	 *	@param e entity to remove
	 */
	public void remove(Entity e)
	{
		entities.remove(e);
	}
	
	/*
	 *	Timer for entity
	 */
	public void onTick()
	{
		//if(queue.size() > 0)
		//{
		//	entities.add(queue.get(0));
		//	entities.remove(queue.get(0));
		//}
			
		
		
		for(Entity e:entities)
		{
			//System.out.println(e.getClass().getSimpleName());
			e.onTick();	
		}
		//System.out.println("here");
	}
	
	/*
	 *	Gets entities on map
	 *
	 *	@return Arraylist of entities
	 */
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	/*
	 *	Gets background of map
	 *
	 *	@return background of map
	 */
	public BufferedImage getBackground()
	{
		return background;
	}
	
	/*
	 *	Sets the X component of map
	 *
	 *	@param x  X component
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/*
	 *	Sets the Y component of map
	 *
	 *	@param y  Y component
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/*
	 *	Sets the name of map
	 *	
	 *	@param name	name of map
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}