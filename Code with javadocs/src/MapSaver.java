
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class MapSaver {

	Map map;
	String name;
	ArrayList<Map> maps;
	
	/*
	 *	Default constructor of mapsavor
	 */
	public MapSaver()
	{
		
	}
	
	/*
	 *	Constructs a mapsaver
	 *
	 *	@param maps an arraylist of maps
	 */
	public MapSaver(ArrayList<Map> maps)
	{
		this.maps = maps;
	}	
	
	/*
	 *	Adds maps to the arraylist
	 *
	 *	@param maps map that will be added
	 */
	public void addMaps(ArrayList<Map> maps)
	{
		this.maps = maps;
	}
	
	/*
	 *	Sets the current map
	 *
	 *	@param map current map
	 */
	public void setMap(Map map)
	{
		this.map = map;
	}
	
	/*
	 *	Sets the name of the map
	 *
	 *	@param name name of the map
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 *	Saves the map
	 *
	 *	@param map  map to save
	 *	@param name name of the map
	 */
	public void saveMap(Map map, String name)
	{
		String mapName = name;
		String shortName = name;
		name = name + ".map";
		name = name.toLowerCase();
		
		
		PrintWriter p = null;
		try {
			p = new PrintWriter(new File(name));
		} catch (FileNotFoundException e) {

		}
		ArrayList<Entity> list = map.entities; 
		p.println("POS" + ","  + map.x + "," + map.y);
		if(map.name.equals("default"))
		{
			map.name = mapName;
		}
			
		p.println("NAME" + "," + shortName);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof MovingPlatform)
			{
				MovingPlatform e = (MovingPlatform)(list.get(i));
				
				p.println("MOVINGPLATFORM," + e.getX() + "," + e.getY() + "," + e.getWidth() + "," + e.getHeight() + "," + e.time + "," + e.direction);
				
			}
			else if(list.get(i) instanceof Platform)
			{
				Platform e = (Platform)(list.get(i));
				
				p.println("PLATFORM," + e.getX() + "," + e.getY() + "," + e.getWidth() + "," + e.getHeight());
				
			}			
			else if(list.get(i) instanceof Bot)
			{
				Bot e = (Bot)(list.get(i));
				
				p.println("BOT," + e.getX() + "," + e.getY() + "," + e.getWidth() + "," + e.getHeight() + "," + e.time + "," + e.direction);
			}	
			else if(list.get(i) instanceof Portal)
			{
				Portal e = (Portal)(list.get(i));
				p.println("PORTAL," + e.getX() + "," + e.getY() + "," + e.getWidth() + "," + e.getHeight() + "," + e.targetx + "," + e.targety + "," + e.mapName);
				
			}
			else if(list.get(i) instanceof Decoration)
			{
				Decoration e = (Decoration)(list.get(i));
				p.println("DECORATION," + e.getX() + "," + e.getY() + "," + e.getWidth() + "," + e.getHeight() + "," + e.spriteName);
				
			}			
			else if(list.get(i) instanceof Spawner)
			{
				Spawner e = (Spawner)(list.get(i));
				p.println("SPAWNER," + e.getX() + "," + e.getY() + "," + e.getWidth() + "," + e.getHeight());
				
			}
	
		}
		p.close();
	}
	
	/*
	 *	Loads a map
	 *
	 *	@param name name of map
	 */
	public Map loadMap(String name)
	{
		SplashPanel.status = "loading";
		Runner.splash.repaint();
		
		name = name.toLowerCase();
		if(!(name.contains(".map")))
			name = name + ".map";	
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//InputStream input = classLoader.getResourceAsStream(name);
		InputStream input = MapSaver.class.getResourceAsStream(name);
		
		if(input == null)
		{
			SplashPanel.status = "stream not found. File: " + name;
			Runner.splash.repaint();
		}
			
		Scanner in = null;
		try {
			in = new Scanner(new File(name));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*try {
			//in = new Scanner(new FileReader(name));
			
		} catch (FileNotFoundException e) {
			System.out.println("map file not found");
			SplashPanel.status = "map file not found";
			Runner.splash.repaint();
		}*/
		SplashPanel.status = name;
		Runner.splash.repaint();		
		Map map = null;
		try {
			map = new Map();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(in.hasNextLine())
		{
			SplashPanel.status = "stopped?";
			Runner.splash.repaint();
			String s = in.nextLine();
			String[] parts = s.split(",");
			
			SplashPanel.status = "here?";
			Runner.splash.repaint();
			switch(parts[0])
			{
			
			case "POS":
				if(parts.length == 3)
				{
					map.setX(Integer.parseInt(parts[1]));
					map.setY(Integer.parseInt(parts[2]));	
				}
				else
				{
					System.out.println("corrupt pos map data");
				}
				break;
			case "PLATFORM":
				if(parts.length == 5)
				{
					try {
						map.add(new Platform(Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]), 
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]), 
								map));
					} catch (NumberFormatException | IOException e) {
					}
	
				}
				else
				{
					System.out.println("corrupt platform map data");
				}		
				break;
			case "MOVINGPLATFORM":
				if(parts.length == 7)
				{

					try {
						map.add(new MovingPlatform(Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]), 
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]), 
								map,
								Integer.parseInt(parts[5]),
								Integer.parseInt(parts[6])));
					} catch (NumberFormatException | IOException e) {
					}
	
				}		
				else
				{
					System.out.println("corrupt movingplatform map data");
				}
				break;
			case "BOT":
				if(parts.length == 7)
				{

					try {
						map.add(new Bot(Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]), 
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]), 
								Integer.parseInt(parts[5]),
								Integer.parseInt(parts[6]),
								map));
					} catch (NumberFormatException | IOException e) {
					}
	
				}		
				else
				{
					System.out.println("corrupt bot map data");
				}
				break;
			case "NAME":
				if(parts.length == 2)
				{
					map.name = parts[1];
				}
				break;
			case "PORTAL":
				if(parts.length == 8)
				{

					try {
						map.add(new Portal(Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]), 
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]), 
								Integer.parseInt(parts[5]),
								Integer.parseInt(parts[6]),
								parts[7],
								map));
					} catch (NumberFormatException | IOException e) {
					}
	
				}		
				else
				{
					System.out.println("corrupt portal map data");
				}
				break;	
			case "DECORATION":
				if(parts.length == 6)
				{
					int i = (int)(Math.random()*2);
					String n = null;
					if(i == 0)
						n = "heat.png";
					else if(i == 1)
						n = "q1framed.png";
					
			    	SplashPanel.status = parts[5];
			    	Runner.splash.repaint();
			    	
					try {
						map.add(new Decoration(Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]), 
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]),
								parts[5],
								map));
					} catch (NumberFormatException | IOException e) {
					}
	
				}
				else
				{
					System.out.println("corrupt decoration map data");
				}		
				break;
			case "SPAWNER":
				if(parts.length == 5)
				{
					try {
						map.add(new Spawner(Integer.parseInt(parts[1]), 
								Integer.parseInt(parts[2]), 
								Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]),
								map));
						//System.out.println("created spawner");
					} catch (NumberFormatException | IOException e) {
					}
	
				}
				else
				{
					System.out.println("corrupt spawner map data");
				}		
				break;	
				
			}//end switch
			
			SplashPanel.status = "stopped2?";
			Runner.splash.repaint();			
			
			
		}
		
		in.close();
		return map;
	}
	
	/*
	 *	Saves the player
	 *
	 *	@param player	player to save
	 *	@param name	name of player
	 */
	public void savePlayer(Player player, String name)
	{
		String playerName = name;
		name = name + ".player";
		name = name.toLowerCase();
		PrintWriter p = null;
		try {
			p = new PrintWriter(new File(name));
		} catch (FileNotFoundException e) {
		}		
		
		p.println("POS" + "," + player.getX() + "," + player.getY()  + "," + player.getWidth()  + "," +  player.getHeight());
		p.println("SPRITE" + "," + player.spritLeft  + "," +  player.spritRight);
		p.println("PARENT" + "," + player.parent.name);
		
		p.close();
	}
	
	/*
	 *	Loads the player
	 *
	 *	@param name name of player
	 */
	public Player loadPlayer(String name)
	{
		name = name.toLowerCase();
		if(!(name.contains(".player")))
			name = name + ".player";	

		Scanner in = null;
		try {
			in = new Scanner(new FileReader(name));
		} catch (FileNotFoundException e) {
			System.out.println("player file not found");
			return null;
		}
		Player player = null;
		try {
			player = new Player(0, 0, 0, 0, null, null, null);
		} catch (IOException e) {
		}		
		
		while(in.hasNextLine())
		{
			String[] parts = in.nextLine().split(",");
			
			switch(parts[0])
			{
			case "POS":
				if(parts.length == 5)
				{
					player.setX(Integer.parseInt(parts[1]));
					player.setY(Integer.parseInt(parts[2]));
					player.setSize(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
				}
				else
				{
					System.out.println("corrupt player pos data");
				}
				break;
			case "SPRITE": 
				if(parts.length == 3)
				{
					player.setSprite(parts[1], parts[2]);
				}
				else
				{
					System.out.println("currupt player sprite data");					
				}
				break;
			case "PARENT":
				if(parts.length == 2)		
				{
					Map tempMap = null;
					for(int i = 0; i< maps.size(); i++)
					{
						if(maps.get(i).name.equals(parts[1]))
						{
							tempMap = maps.get(i);
						}
					}
					player.setParent(tempMap);
				}
				
			}
			
		}
		in.close();
		
		
		return null;
	}
}

