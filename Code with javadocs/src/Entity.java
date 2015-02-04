

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Entity{
	
	Rectangle mesh;
	Rectangle normalMesh;
	Rectangle ceilingMesh;
	public int speed;
	int tickLife;
	Map parent;
	BufferedImage sprite;
	BufferedImage spriteLeft;
	BufferedImage spriteRight;
	String dImage = "def2.png";
	int dSpeed = 6;
	int direction = 0;
	boolean passable = false;
	int status = 0;
	boolean selected;
	boolean selected2;
	//boolean down = true;
	int ID = 0;
	boolean blocked2;
	boolean normal;
	Entity hitch;
	Entity hitcher;
	boolean hitCeiling;
	boolean dead;
	boolean passableReal;
	boolean onPortal;
	Portal portal;
	String spriteName;
	boolean hasGravity = true;;
	boolean tethered;
	
	Line line;
	
	int v_y;
	int v_x;
	int airRes;
	public static final int AIR = 2;
	public static BufferedImage img;
	
	/*
	 *	Constructs an entity
	 */
	public Entity() throws IOException
	{
		mesh = new Rectangle(100, 100, 30, 100);
		speed = dSpeed;
		parent = new Map();
		airRes = AIR;
	}
	
	/*
	 *	Constructs an entity
	 *	
	 *	@param x	X component of entity 
	 *	@param y	Y component of entity  
	 */
	public Entity(int x, int y) throws IOException
	{
		mesh = new Rectangle(x, y, 30, 100);
		speed = dSpeed;
		parent = new Map();
		airRes = AIR;
	}
	
	/*
	 *	Constructs an entity
	 *	
	 *	@param x		X component of entity 
	 *	@param y		Y component of entity  
	 *	@param width	width of entity
	 *	@param height	height of entity
	 */
	public Entity(int x, int y, int width, int height) throws IOException
	{
		mesh = new Rectangle(x, y, width, height);
		speed = dSpeed;
		parent = new Map();
		airRes = AIR;
	}
	
	/*
	 *	Constructs an entity
	 *	
	 *	@param dad	map if entity
	 */
	public Entity(Map dad)
	{
		mesh = new Rectangle(100, 100, 30, 100);
		speed = dSpeed;
		parent = dad;
		sprite = null;
		try {
		    sprite = ImageIO.read(new File(dImage));
		} catch (IOException e) {
		}		
		airRes = AIR;
	}
	
	/*
	 *	Constructs an entity
	 *	
	 *	@param x	X component of entity 
	 *	@param y	Y component of entity
	 *	@param dad 	map of entity  
	 */
	public Entity(int x, int y, Map dad)
	{
		mesh = new Rectangle(x, y, 30, 100);
		speed = dSpeed;
		parent = dad;
		sprite = null;
		try {
		    sprite = ImageIO.read(new File(dImage));
		} catch (IOException e) {
		}	
		airRes = AIR;
	}
	
	/*
	 *	Constructs an entity
	 *	
	 *	@param x		X component of entity 
	 *	@param y		Y component of entity  
	 *	@param width	width of entity
	 *	@param height	height of entity
	 *	@param dad		map of entity
	 */
	public Entity(int x, int y, int width, int height, Map dad) throws IOException
	{
		mesh = new Rectangle(x, y, width, height);
		normalMesh = new Rectangle(x, y + 5, width, height);
		ceilingMesh = new Rectangle(x, y - 5, width, height);
		speed = dSpeed;
		parent = dad;
		airRes = AIR;
		/*
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(dImage);
		sprite = ImageIO.read(input);*/
		
		sprite = ImageLoader.entity;
	
	}
	
	/*
	 *	Constructs an entity
	 *
	 *	@param e entity of construct
	 */
	public Entity(Entity e)
	{
		mesh = new Rectangle(e.mesh.x, e.mesh.y, e.mesh.width, e.mesh.height);
		normalMesh = new Rectangle(e.mesh.x, e.mesh.y, e.mesh.width, e.mesh.height);
		airRes = AIR;
		
	}
	
	/*
	 *	Sets the parent
	 *
	 *	@param par	parent map
	 */
	public void setParent(Map par)
	{
		parent = par;
	}
	
	/*
	 *	Gets the sprite
	 *		
	 *	@return spirite on map
	 */	
	public BufferedImage getSprite()
	{
		if(direction == 0)
		{
			if(spriteLeft == null)
				return sprite;
			else
				return spriteLeft;
		}
		else if(direction == 1)
		{
			if(spriteRight == null)
				return sprite;
			else
				return spriteRight;
		}		
		return sprite;
	}
	
	/*
	 *	Gets center of point
	 *
	 *	@return center point of rectangle
	 */
	public Point getCenter()
	{
		return new Point(getX() + getWidth()/2, getY() + getHeight()/2);
	}
	
	/*
	 *	Gets x component 
	 *	
	 *	@return x component of rectangle
	 */
	public int getX()
	{
		return (int)(mesh.getX());
	}
	
	/*
	 *	Gets y component 
	 *	
	 *	@return y component of rectangle
	 */
	public int getY()
	{
		return (int)(mesh.getY());
	}	
	
	/*
	 *	Gets the width
	 *
	 *	@return width of rectangle
	 */
	public int getWidth()
	{
		return (int)(mesh.getWidth());
	}	
	
	/*
	 *	Gets the height
	 *
	 *	@return height of rectangle
	 */	
	public int getHeight()
	{
		return (int)(mesh.getHeight());
	}
	
	/*
	 *	Sets X component
	 *
	 *	@param x new X component
	 */
	public void setX(int x)
	{
		mesh.setLocation(x, getY());
		ceilingMesh.setLocation(x, getY()-5);
		normalMesh.setLocation(x, getY()+5);
	}
	
	/*
	 *	Sets Y component
	 *
	 *	@param Y new Y component
	 */
	public void setY(int y)
	{
		mesh.setLocation(getX(), y);
		ceilingMesh.setLocation(getX(), y - 5);
		normalMesh.setLocation(getX(), y + 5);
	}
	
	/*
	 *	Sets the size of rectangle
	 *
	 *	@param x	X component of rectangle
	 *	@param y	Y component of rectangle
	 */	
	public void setSize(int x, int y)
	{
		mesh.setSize(x, y);
		ceilingMesh.setSize(x, y);
		normalMesh.setSize(x, y);	
	}
	
	/*
	 *	Sets the location of rectangle
	 *
	 *	@param x	X component of rectangle
	 *	@param y	Y component of rectangle
	 */	
	public void setLocation(int x, int y)
	{
		mesh.setLocation(x, y);
		ceilingMesh.setLocation(x, y - 5);
		normalMesh.setLocation(x, y + 5);		
	}
	
	/*
	 *	Warps to winning portal
	 *
	 *	@param dest	destination point
	 */
	public boolean cc(Point dest)
	{	
		Rectangle future = new Rectangle((int)(dest.getX()), (int)(dest.getY()), (int)(mesh.getWidth()), (int)(mesh.getHeight()));		
		ArrayList<Entity> ents = parent.getEntities();
		boolean marked = false;
		for(Entity e: ents)
		{
			if(e == this)
			{
				
			}
			else if(future.intersects(e.mesh) && e instanceof Portal)
			{
				onPortal = true;
				portal = (Portal)e;
				marked = true;
			}
			else
			{
				if(future.intersects(e.mesh) && (!e.passable || status == 1 || status == 2) && (!e.passableReal))
				{
					blocked2 = true;
					
					if(!marked)
					{
						onPortal = false;
						portal = null;
					}
					onHit();	
					return true;
				}
			}
			
			
			
		}
		if(!marked)
		{
			onPortal = false;
			portal = null;
		}
			
		blocked2 = false;
		return false;
		
	}
	
	/*
	 *	Moves entity left
	 */
	public void left()
	{
		boolean blocked = true;
		int tempSpeed = speed;
		direction = 0;
		while(blocked)
		{
			blocked = cc(new Point(getX() - tempSpeed, getY()));
			if(!blocked)
			{
				setX(getX()-tempSpeed);	
			}
			else if(tempSpeed == 0)
			{
				return;
			}			
			else
			{
				tempSpeed--;
			}
		}

		
		
	}
	
	/*
	 *	What happens if hit
	 */
	public void onHit()
	{
		
	}
	
	/*
	 *	Moves entity right
	 */
	public void right()
	{
		boolean blocked = true;
		int tempSpeed = speed;
		direction = 1;
		while(blocked)
		{
			blocked = cc(new Point(getX() + tempSpeed, getY()));
			if(!blocked)
			{
				setX(getX()+tempSpeed);	
			}
			else if(tempSpeed == 0)
			{
				return;
			}			
			else
			{
				tempSpeed--;
			}
		}
	}	
	
	/*
	 *	Moves entity left
	 *
	 *	@param s	temporary speed
	 */
	public void left(int s)
	{
		boolean blocked = true;
		int tempSpeed = s;
		direction = 0;
		while(blocked)
		{
			blocked = cc(new Point(getX() - tempSpeed, getY()));
			if(!blocked)
			{
				setX(getX()-tempSpeed);	
			}
			else if(tempSpeed == 0)
			{
				return;
			}			
			else
			{
				tempSpeed--;
			}
		}

		
		
	}
	
	/*
	 *	Moves entity right
	 *
	 *	@param s	temporary speed
	 */
	public void right(int s)
	{
		boolean blocked = true;
		int tempSpeed = s;
		direction = 1;
		while(blocked)
		{
			blocked = cc(new Point(getX() + tempSpeed, getY()));
			if(!blocked)
			{
				setX(getX()+tempSpeed);	
			}
			else if(tempSpeed == 0)
			{
				return;
			}			
			else
			{
				tempSpeed--;
			}
		}
	}	
	
	/*
	 *	Sets sprites 
	 *	
	 *	@param name name of sprite
	 */
	public void setSprite(String name)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(name);
		spriteName = name.toLowerCase();
		try {
			sprite = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/*
	 *	Sets sprites 
	 *	
	 *	@param left		left of sprite
	 *	@param right	right of sprite
	 */
	public void setSprite(String left, String right)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(right);
		try {
			sprite = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spriteRight = sprite;
		input = classLoader.getResourceAsStream(left);
		try {
			spriteLeft = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
	
	/*
	 *	Sets the line
	 *
	 *	@param line line of line
	 */
	public void setLine(Line line)
	{
		this.line = line;
	}
	
	/*
	 *	Lets go of the line
	 */
	public void releaseLine()
	{
		this.line = null;
	}
	
	/*
	 *	Goes up with line
	 */
	public void up()
	{
		setY(getY() - speed);
	}
	
	/*
	 *	Goes down from line
	 */
	public void down()
	{
		
		boolean blocked = true;
		int tempSpeed = speed;
		while(blocked)
		{
			blocked = cc(new Point(getX(), getY() + tempSpeed));
			if(!blocked)
			{
				setY(getY() + tempSpeed);
			}
			else if(tempSpeed == 0)
			{
				return;
			}			
			else
			{
				tempSpeed--;
			}		
		
		
		}
	}
	
	/*
	 *	Sets the gravity
	 */	
	public void gravity()
	{
		//if(!hasGravity)
			//return;
		boolean blocked = true;
		int tempSpeed = speed + 1;
		status = 1;
		while(blocked)
		{
			blocked = cc(new Point(getX(), getY() + tempSpeed));
			if(!blocked)
			{
				setY(getY() + tempSpeed);
			}
			else if(tempSpeed == 0)
			{
				status = 0;
				return;
			}
			else
			{
				tempSpeed--;
			}		
		
		
		}
		status = 0;
	}
	
	/*
	 *	Sets the gravity
	 *
	 *	@param amount change of gravity
	 */
	public void gravity(int amount)
	{
		//if(!hasGravity)
			//return;		
		//down = true;
		boolean blocked = true;
		status = 1;
		int tempSpeed = amount + 1;
		if(tempSpeed>amount*3)
			tempSpeed = amount*3;
		while(blocked)
		{
			blocked = cc(new Point(getX(), getY() + tempSpeed));
			//down = false;
			if(!blocked)
			{
				setY(getY() + tempSpeed);
			}
			else if(tempSpeed == 0)
			{
				status = 0;
				return;
			}
			else
			{
				tempSpeed--;
			}		
		
		
		}
		status = 0;
	}	
	
	/*
	 *	Going up with line
	 *
	 *	@param amount how much up
	 */
	public void up(int amount)
	{
		boolean blocked = true;
		status = 2;
		int tempSpeed = amount;
		while(blocked)
		{
			blocked = cc(new Point(getX(), getY() - tempSpeed));
			if(!blocked)
			{
				setY(getY() - tempSpeed);
			}
			else if(tempSpeed == 0)
			{
				status = 0;
				return;
			}			
			else
			{
				tempSpeed--;
			}		
		
		
		}
		status = 0;
	}	
	
	/*
	 *	Going down with line
	 *
	 *	@param amount how much down
	 */
	public void down(int amount)
	{
		setY(getY() + amount);
	}	
		
	/*
	 *	Checks the map 
	 */
	public void onTick()
	{
		tickLife++;
		checkNormal();
		checkCeiling();
	}
	
	/*
	 *	Sets the hitcher
	 *
	 *	@param e entity to be hitched
	 */	
	public void setHitcher(Entity e)
	{
		hitcher = e;
	}
	
	/*
	 *	Removes the hitcher
	 */
	public void removeHitcher()
	{
		hitcher = null;
	}
	public void checkNormal()
	{
		ArrayList<Entity> ents = parent.getEntities();
		for(Entity e: ents)
		{
			if(e == this)
			{
				
			}
			else if(e.mesh.intersects(normalMesh) && !e.passableReal)
			{
				normal = true;
				hitch = e;
				e.setHitcher(this);
				return;
			}
		}
		normal = false;
		if(hitch !=null)
		{
			hitch.removeHitcher();
			hitch = null;			
		}

	}
	
	/*
	 *	Checks for ceiling above entity
	 */
	public void checkCeiling()
	{
		ArrayList<Entity> ents = parent.getEntities();
		for(Entity e: ents)
		{
			if(e == this)
			{
				
			}
			else if(e.mesh.intersects(ceilingMesh) && !e.passableReal)
			{
				hitCeiling = true;
				
				
				return;
			}
		}
		hitCeiling = false;		
	}
}