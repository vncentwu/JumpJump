import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Portal extends Entity{

	Map target;
	int targetx;
	int targety;
	String mapName;
	
	/*
	 *	Constructs a portal
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of portal
	 *	@param height		height of portal
	 *	@param dad			map of the portal
	 */
	public Portal(int x, int y, int width, int height, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("portal.png");
		sprite = ImageIO.read(input);
		passableReal = true;
		target = dad;
		targetx = 100;
		targety = 100;
	}
	
	/*
	 *	Constructs a portal
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of portal
	 *	@param height		height of portal
	 *	@param targetx		X component of target
	 *	@param targety		Y component of target
	 *	@param mapName		name of map
	 *	@param dad			map of the portal
	 */
	public Portal(int x, int y, int width, int height, int targetx, int targety, String mapName, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
		this.mapName = mapName;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("castledoors.png");
		sprite = ImageIO.read(input);
		passableReal = true;
		
		for(Map e: Runner.maps)
		{
			System.out.println(e.name);
			System.out.println(mapName);			
			if(e.name.equals(mapName))
			{
				target = e;

			}
		}
		
		if(target ==  null)
		{
			System.out.println("no map found with target name");
			target = dad;
		}

		this.targetx = targetx;
		this.targety = targety;
	}
	
	/*
	 *	Refreshes the map with portal
	 */
	public void refresh()
	{
		for(Map e: Runner.maps)
		{
			System.out.println(e.name);
			System.out.println(mapName);			
			if(e.name.toUpperCase().equals(mapName.toUpperCase()))
			{
				target = e;

			}
		}
		
		if(target ==  null)
		{
			//System.out.println("no map found with target name");
		}		
	}
	
	/*
	 *	Teleports player to map
	 *
	 *	@param currrentMap current map of player
	 *	@param player plater to teleport
	 *	@return current map with player on it
	 */
	public Map teleport(Map currentMap, Player player)
	{
			currentMap.remove(player);
			currentMap = target;
	  		player.setParent(currentMap);
	  		player.setLocation(targetx, targety);
	  		currentMap.add(player);  
	  		return currentMap;
		
	}
	
	
}
