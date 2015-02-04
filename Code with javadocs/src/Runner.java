
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Runner extends JPanel 
{ 
	

	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	JDialog dialog;
	public static int tick;
	
	Point mouse;
	public static double wmx;
	public static double wmy;
	public static double DEFAULT_WIDTH = 1280;
	public static double DEFAULT_HEIGHT = 720;
	public final static int NEW_GAME = 0;
	public int loadValue = 0;
	public static JFrame splash;
	Player player;
	Camera camera;
	boolean left;
	boolean right;
	boolean jumping;
	public static boolean reeling;
	int airTime;
	int airTime2;
	int v_up;
	boolean air;
	int y_o;
	int v_down;
	boolean drawing;
	Point clicked;
	Point dragged = new Point(0,0);
	Entity testEnt;
	Rectangle meshDrawn = new Rectangle(0, 0, 0, 0);
	Map guiMap;
	Map currentMap;
	
	public static ArrayList<Map> maps;
	public static ArrayList<Entity> addQueue;
	Entity selected;
	int selectedID = -1;
	boolean dragging;
	MapSaver mapSaver;
	
	Entity selectedEnt;
	Entity selectedEnt2;
	
	
	JTextArea field;
	JTextField input;
	String consoleText = "";
	boolean consoleOn;
	String command = "";
	int delay;
	Point shoot = new Point(0,0);
	Line line = new Line(new Point(0,0), new Point(0,0), null);
	Entity hookedOn;
	boolean showOutline;
	
	public static boolean isResize;
	public static int w_resize;
	public static int h_resize;
	public static boolean isBorder;
	public static boolean doBorder;
	public static boolean pause;
	public static int progress;
	
	/*
	 *	Constructs the runner
	 */
	public Runner()
	{
		addKeyListener(new MyKeyListener());
		frame.addKeyListener(new MyKeyListener());
		frame.addMouseListener(new MyMouseListener());
		frame.addMouseMotionListener(new MyMotionListener());		
		setLayout(new FlowLayout());
		reprogress();
		
	}
	
	/*
	 *	Creates the initial screen
	 */
	public void init() throws IOException
	{
		//ImageLoader.load();
		mapSaver = new MapSaver();
		loadValue = 0;//this needs to be determined later
		player = new Player(100, 100, 56, 65, currentMap); //change later
		camera = new Camera(player);
		mouse = new Point(0,0);
		
		
		reprogress();
		loadMaps();
		reprogress();
		wmx= 1;
		wmy = 1;
		guiMap = new Map();
		guiMap.add(new Entity(10, 200, 60, 20, guiMap));
		reprogress();
		initConsole();
		reprogress();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("foxright.png");
		Entity.img = ImageIO.read(input);		
		
		//showConsole();
	}
	
	/*
	 *	Creates the initial input console
	 */
	public void initConsole()
	{
		input= new JTextField(50);
		
		input.addKeyListener(new ConsoleKeyListener());

		
		input.addAncestorListener( new RequestFocusListener() );
		field = new JTextArea();
		field.setEditable(false);
		field.setLineWrap(true);
		JScrollPane area = new JScrollPane(field, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		field.setRows(10);
		field.setText(consoleText);		
	}

  	@SuppressWarnings("deprecation")
  	/*
  	 *	Shows the console
  	 */
	public void showConsole()
  	{
  		consoleOn = true;
		UIManager UI=new UIManager();
		 UI.put("OptionPane.background", Color.BLACK);
		 UI.put("Panel.background", Color.BLACK);
		 UI.put("OptionPane.messageForeground", Color.WHITE);
		boolean go = true;
		while(go)
		{
			
			input= new JTextField(50);
		
			input.addKeyListener(new ConsoleKeyListener());

			
			input.addAncestorListener( new RequestFocusListener() );
			field = new JTextArea();
			field.setEditable(false);
			field.setLineWrap(true);
			field.setOpaque(false);
			field.setBackground(new Color(0,0,0,0));
			JScrollPane area = new JScrollPane(field, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			field.setRows(10);
			field.setText(consoleText);
			JPanel myPanel = new JPanel();

			
			myPanel.setLayout(new BorderLayout(0,0));
			myPanel.add(input, BorderLayout.PAGE_END);
			myPanel.add(area, BorderLayout.PAGE_START);
			input.setFocusable(true);
			input.requestFocus();
			int result = 101;
			
			JOptionPane pane = new JOptionPane(myPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		
			
			//dialog = new JDialog((Frame)null, "");
			dialog = new JDialog(frame, "");
			dialog.setUndecorated(true);
			dialog.setOpacity(0.80f);

            dialog.getRootPane().setOpaque(false);
            dialog.getRootPane().setBackground(new Color(0,0,0,0));
            
            dialog.setLayout(new BorderLayout());
            dialog.add(pane);
            dialog.pack();
			dialog.setLocationRelativeTo(this);
			dialog.setLocation(new Point(0 ,getHeight()-dialog.getHeight()));             
            dialog.setVisible(true);			


			dialog.show();
			
			input.requestFocusInWindow();
			if(result == 100)
			{
				
			}
			else
			{
				go = false;
			}
			
		} 
		 
  	} 
	
	/*
	 *	Handles the input command of console
	 */	
  	public void handleCommand() throws IOException
  	{
  		command = command.toUpperCase();
  		
  		
  		if(command.contains("RESIZE"))
  		{
 			int spaces = 0;
  			for(int i = 0; i<command.length(); i++)
  			{
  				if(command.charAt(i) == ' ')
  				{
  					spaces++;
  				}
  			}
  			if(spaces == 0)
  			{
  				print("No values specified..resize <x> <y>");
  			}
  			else if(spaces == 1)
  			{
  				print("Missing value..resize <x> <y>");
  			} 			
  			else if(spaces == 2)
  			{
  				String[] pieces = command.split(" ");
  				int val1;
  				int val2;
  				try
  				{
  					val1 = Integer.parseInt(pieces[1]);
  					val2 = Integer.parseInt(pieces[2]);
  				}
  				catch(NumberFormatException e)
  				{
  					print("Incorrect format of 'resize' command entered. Correct format is: resize <x> <y>)");
  					return;
  				}
  				//closeConsole();
  				resize(val1, val2);
  				print("Window size has been resized to (" + val1 + "," + val2 + ").");


  				//showConsole();
  				
  				return;
			
  			}
  			else
  			{
					print("Incorrect format of 'spawn' command entered. Correct format is: spawn <amount of bots> <radius>)");
  					return;  				
  			}

  			return;
  		}
  		else if(command.contains("SAVEMAP") || command.contains("SAVE"))
  		{
 			int spaces = 0;
  			for(int i = 0; i<command.length(); i++)
  			{
  				if(command.charAt(i) == ' ')
  				{
  					spaces++;
  				}
  			}
  			
  			if(spaces == 1)
  			{
  				String[] pieces = command.split(" ");
  				String fileName = pieces[1];
  				mapSaver.saveMap(currentMap, fileName);
  				
  				System.out.println("111111111111111111111111111111111111111111111");
  				
  			}
  			
  		}
  		else if(command.contains("LOADMAP") || command.contains("LOAD"))
  		{
 			String[] parts = command.split(" ");
  			
  			
  			if(parts.length == 2)
  			{
  				String fileName = parts[1];
  				Map tempMap = mapSaver.loadMap(fileName);
  				if(tempMap != null)
  				{
  					maps.add(tempMap);
  				}
  			}
  			
  		}
  		else if(command.contains("SWITCHMAP") || command.contains("SWITCH"))
  		{
 			String[] parts = command.split(" ");	
  			if(parts.length == 2)
  			{
  				String mapName = parts[1];
  				Map tempMap = null;
  				for(int i = 0; i<maps.size(); i++)
  				{
					//System.out.println("other" + maps.get(i).name);
					//System.out.println("finding" + mapName);					
  					if(maps.get(i).name.toUpperCase().equals(mapName))
  					{
  						tempMap = maps.get(i);

  					}
  				}
  				if(tempMap == null)
  					print("no map with the name was found");
  				else
  				{
	   				currentMap.remove(player);
	  				currentMap = tempMap;
	  		  		player.setParent(currentMap);
	  		  		player.setLocation(100, 100);
	  		  		currentMap.add(player);   					
  				}
					
  			}  			 			
  		} 
  		else if(command.contains("SET"))
  		{
  			String[] parts = command.split(" ");
  			if(parts.length == 3)
  			{
  				switch(parts[1])
  				{
  				case "WIDTH":
  					if(selectedEnt != null)
  					{
  						selectedEnt.setSize(Integer.parseInt(parts[2]), selectedEnt.getHeight());
  						
  					}
  					break;
  				case "HEIGHT":
  					if(selectedEnt != null)
  					{
  						selectedEnt.setSize(selectedEnt.getWidth(), Integer.parseInt(parts[2]));
  					}
  					break; 				
  				case "X":
  					if(selectedEnt != null)
  					{
  						selectedEnt.setX(Integer.parseInt(parts[2]));
  					}
  					break; 	 				
  				case "Y":
  					if(selectedEnt != null)
  					{
  						selectedEnt.setY(Integer.parseInt(parts[2]));
  					}
  					break;  	
  				case "SPRITE":
  					if(selectedEnt != null)
  					{
  						selectedEnt.setSprite(parts[2]);
  					}
  					break;  					
  				
  				}
	
  			}	
  		}
  		
  		
  		
  		switch(command)
  		{
  			case "DRAW":
  				toggleDrawing();
  				return;
  			case "DRAG":
  				toggleDragging();
  				return; 				
  			case "BORDER":
  				isBorder = true;
  				return;
  			case "NEW MAP":
  				newMap();
  				return;
  			case "DRAW BOT":
  				if(!drawing)
  					toggleDrawing();
  				selectedID = 1;
  				return;
  			case "DRAW BOX":
  				if(!drawing)
  					toggleDrawing();
  				selectedID = 0;
  				return;  
  			case "DRAW MOVING BOX":
  				if(!drawing)
  					toggleDrawing();
  				selectedID = 2;
  				return; 
  			case "DRAW DECORATION":
  				if(!drawing)
  					toggleDrawing();
  				selectedID = 3;
  				return;   				
  			case "DRAW PORTAL":
  				if(!drawing)
  					toggleDrawing();
  				selectedID = 4;
  				return; 
  			case "DRAW SPAWNER":
  				if(!drawing)
  					toggleDrawing();
  				selectedID = 5;
  				return;  				
  			case "PAUSE": 
  				togglePause();
  				return;
  			case "DELETE":
  				deleteSelection();
  				return;
  			case "SWITCH MAP":
  				currentMap.remove(player);
  				currentMap = maps.get(1);
   		  		//maps.add(currentMap);
  		  		player.setParent(currentMap);
  		  		player.setLocation(100, 100);
  		  		currentMap.add(player);  				
  				
  				return;
  			case "MAPS":
  				String list = "";
  				for(Map e: maps)
  				{
  					list = list + e.name + " ";
  				}
  					
  				print(list);
  				return;
  			case "DUPLICATE":
  				
				
  		}
  		
  	}
  	
  	/*
  	 *	Resizes the runner
  	 */
  	public void resize(int x, int y)
  	{
  		w_resize = x;
  		h_resize = y;
  		isResize = true;
  	}
  	
  	/*
  	 *	Toggles drawin feature on map
  	 */
  	public void toggleDrawing()
  	{
  		if(drawing)
  		{
  			print("Drawing disabled.");
  		}
  		else
  		{
  			print("Drawing enabled.");
  		}
  		drawing = !drawing;
  		if(drawing)
  			dragging = false;
  	}
	
	/*
	 *	Toggles dragging feature on map
	 */
  	public void toggleDragging()
  	{
  		if(dragging)
  		{
  			print("Dragging disabled.");
  		}
  		else
  		{
  			print("Dragging enabled.");
  		}
  		dragging = !dragging;
  		if(dragging)
  			drawing = false;  		
  	}

	/*
	 *	Prints messages
	 *
	 *	@param message message that is going to print
	 */
  	public void print(String message)
  	{
  		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	//System.out.println(  );
    	
  		consoleText = consoleText + "\n(SYSTEM " + sdf.format(cal.getTime()) + "): " + message;
  		field.setText(consoleText);
  		
  	}
  	
  	/*
  	 *	Adds platform to the map
  	 */
	public void temp() throws IOException
	{
		currentMap.add(new Platform(830, 120, 800, 20, currentMap));
		
		currentMap.add(new Platform(0, 300, 1280, 20, currentMap));
		currentMap.add(new MovingPlatform(300, 20, 100, 20, currentMap, 0));
		currentMap.add(new MovingPlatform(-190, 300, 100, 20, currentMap, 40));
		currentMap.add(new MovingPlatform(-380, 300, 100, 20, currentMap, 0));
		currentMap.add(new MovingPlatform(-570, 300, 100, 20, currentMap, 40));
		currentMap.add(new MovingPlatform(-760, 300, 100, 20, currentMap, 0));
		
		currentMap.add(new Platform(114, 245, 100, 20, currentMap));
		currentMap.add(new Platform(256, 221, 100, 20, currentMap));
		currentMap.add(new Platform(406, 204, 100, 20, currentMap));
		currentMap.add(new Platform(530, 180, 100, 20, currentMap));
		currentMap.add(new Platform(691, 150, 100, 20, currentMap));
		currentMap.add(new Bot(533, 100, 56, 65, currentMap));
		currentMap.add(new Portal(0, 204, 64, 96, 100, 100, "TEST4", currentMap));
		currentMap.add(new Decoration(-100, 100, 100, 50, "5key.png", currentMap));
		currentMap.add(new Spawner(0, 120, 50, 50, currentMap));
	}
	
	/*
	 *	Finds the map 
	 *
	 *	@param name	name of the map
	 */
	public Map findMap(String name)
	{
		for(Map e: maps)
		{
			if(e.name.toUpperCase().equals(name.toUpperCase()))
			{
				return e;
			}
		}
		return null;
	}
	
	/*
	 *	Loads maps
	 */
	public void loadMaps() throws IOException
	{
		switch(loadValue)
		{
			case NEW_GAME: 
				maps = new ArrayList<Map>();
				addQueue = new ArrayList<Entity>();
				currentMap = new Map();
				currentMap.setName("first");
				reprogress();
				maps.add(currentMap);
				currentMap.add(player);
				player.setParent(currentMap);
				testEnt = new Entity(0, 0, 0, 0, currentMap);
				temp();
				reprogress();
				String[] fileNames = {"question1", "victory2", "test10", "test40"};
				for(String e: fileNames)
				{
					Map tempMap = mapSaver.loadMap(e);
					if(tempMap != null)
					{
						maps.add(tempMap);
					}							
				}
				reprogress();
   				currentMap.remove(player);
  				currentMap = findMap("test40");
  		  		player.setParent(currentMap);
  		  		player.setLocation(100, 100);
  		  		currentMap.add(player);  		
				
				
				mapSaver.addMaps(maps);
				
				reprogress();
				
				
				break;
		}
			
	}
	
	/*
	 *	Checks how long player is in the air
	 */
	public void normalCheck()
	{
		if(player.getY() == y_o)
		{
			air = false;
			airTime = 0;
		}
		else
		{
			air = true;
			airTime++;
		}
		y_o = player.getY();
	}
	
	/*
	 *	Checks the gravity on player and how fast player is moving
	 */	
	public void handle()
	{
		
		if(player.speed  - 1 + airTime > player.speed * 2)
			v_down = player.speed * 2;
		else
			v_down = player.speed  - 1 + airTime;
		
		if(!player.hasGravity)
		{
			//player.down(player.speed);
			//System.out.println("hi");
		}
		else
			player.gravity(v_down);
		normalCheck();
		
		
		if(left)
		{
			player.left();
			player.direction = 0;
		}
		if(right)
		{
			player.right();
			player.direction = 1;
		}
		if(jumping)
		{
			handleJump();
		}
		player.hasGravity = true;
		if(reeling)
		{
			handleReel();	
		}
		camera.refresh();
	}
	
	/*
	 *	Reels in the player
	 */
	public void handleReel()
	{
		if(hookedOn!=null)
		{
			player.hasGravity = false;
			player.setX(player.getX() + line.getXDist());
			player.setY(player.getY() + line.getYDist());			
		}

	}
	
	/*
	 *	Player jumps off of handle
	 */
	public void handleJump()
	{
		
		
		player.up(15);
		if(airTime > 7 || player.hitCeiling)
		{
			jumping = false;
			airTime = 0;
		}
	}
	
	/*
	 *	Shows bones
	 */
	public void showBones()
	{
		showOutline = !showOutline;
	}
	
	/*
	 *	paintComponent of runner
	 *	
	 *	@param g Graphics of paintComponent
	 */
	public void paintComponent(Graphics g) 
  	{
  		drawBackground(g);
		drawEntities(g);
  		drawMapMaker(g);

  		drawFPS(g);
  		
  	}
  	
  	/*
  	 *	Draws the line
  	 *
  	 *	@param g	Graphics of the drawLine method
  	 *	@param ex	x component of line
  	 *	@param ey	y component of line
  	 */
	public void drawLine(Graphics g, double ex, double ey)
	{
  		Point p = camera.frame.getLocation();
  		int x = (int)(p.getX());
  		int y = (int)(p.getY());		
		int fx = (int)((ex - x) *wmx);
		int fy = (int)((ey - y)* wmy);	
		
		int ex2 = (int)player.getCenter().getX();
		int ey2 = (int)player.getCenter().getY();
		int fx2 = (int)((ex2 - x) *wmx);
		int fy2 = (int)((ey2 - y)* wmy);		
		
		g.setColor(Color.black);
		g.drawLine(fx, fy, fx2, fy2);
	}
	
	/*
  	 *	Draws real
  	 *
  	 *	@param g		Graphics of the drawReal method
  	 *	@param img		image for real
  	 *	@param ex		x component of real
  	 *	@param ey		y component of real
  	 *	@param width	width of real
  	 *	@param height	height of real
  	 */
	public void drawReal(Graphics g, BufferedImage img, double ex, double ey, double width, double height)
	{
  		Point p = camera.frame.getLocation();
  		int x = (int)(p.getX());
  		int y = (int)(p.getY());		
		int fx = (int)((ex - x) *wmx);
		int fy = (int)((ey - y)* wmy);
		int fw = (int)(width*wmx);
		int fh = (int)(height*wmy);
		if(img == null || showOutline)
		{
			
			g.drawRect(fx,fy, fw,fh);
			
		}
		else
		{
			//g.setColor(Color.black);
			//g.fillRect(fx,fy, fw,fh);
			g.drawImage(img, fx,fy, fw,fh,null);
			//g.drawString("(" + ((int)(ex)) + "," + ((int)(ey)) + ")", fx, fy);			
		}

	}

	/*
	 *	Draws entities on map
	 *
	 *	@param g Graphics of entities
	 */
  	public void drawEntities(Graphics g)
  	{
  		
  		g.setColor(new Color(102, 52, 0));
  		drawLine(g, shoot.getX(), shoot.getY());
  		for(Entity e: currentMap.getEntities())
  		{
  			drawReal(g, e.getSprite(), e.getX(), e.getY(), e.getWidth(),e.getHeight());
  			if(e.selected2)
  				g.setColor(Color.red);
  			else
  				g.setColor(Color.yellow);
  			//drawReal(g, null, e.getX(), e.getY(), e.getWidth(),e.getHeight());
  			g.setColor(Color.black);
  		}
  		g.setColor(Color.red);
  		drawReal(g, null, shoot.getX(), shoot.getY(), 5, 5);
  		
  	}
  	
  	/*
  	 *	Draws a map
  	 *	
  	 *	@param g Graphics of map
  	 */
  	public void drawMapMaker(Graphics g)
  	{
  		//Entity e = new Entity(0, 0, 0, 0, currentMap);
  		g.setColor(Color.WHITE);
  		/*
  		if(drawing)
  		{
  			g.fillRect(0, 0, (int)(80 * wmx), (int)(720 * wmy));	
  			for(Entity e: guiMap.getEntities())
  			{
  				if(e.selected)
  				{
  					int border = 2;
  					g.setColor(Color.BLUE);
  					g.fillRect((int)((e.getX() - border)*wmx), (int)((e.getY()- border)*wmy), (int)((e.getWidth() + border * 2)*wmx), (int)((e.getHeight()+ border * 2)*wmy));
  				}
  				g.drawImage(e.getSprite(), (int)(e.getX()*wmx), (int)(e.getY()*wmy), (int)(e.getWidth()*wmx), (int)(e.getHeight()*wmy), null);
  				
  			}
  		}*/
  		
  		//g.fillRect((int)(meshDrawn.getX()), (int)(meshDrawn.getY()), (int)(meshDrawn.getWidth()), (int)(meshDrawn.getHeight()));
  		g.drawImage(testEnt.getSprite(), (int)(meshDrawn.getX()), (int)(meshDrawn.getY()), (int)(meshDrawn.getWidth()), (int)(meshDrawn.getHeight()), null);
  	}
  	
  	/*
  	 *	Draws the background
  	 *
  	 *	@param g Graphics of background
  	 */
  	public void drawBackground(Graphics g)
  	{
  		g.setColor(Color.black);
  		//g.fillRect(-1000, -1000, 2000, 2000);
  		//g.drawImage(currentMap.getBackground(), (int)(camera.getX()*wmx/1.5), (int)(camera.getY()*wmy/1.5), (int)(3840*wmx), (int)(2160 *1.5 * wmy), null);
  		g.drawImage(currentMap.getBackground(), -1000 - (int)(camera.getX()*wmx/1.5), -0 - (int)(camera.getY()*wmy/1.5), (int)(4096*wmx), (int)(720 *1.5 * wmy), null);
  	}
  	
  	/*
  	 *	Draws the FPS
  	 *
  	 *	@param g Graphics of FPS
  	 */
  	public void drawFPS(Graphics g)
  	{
  		g.setColor(Color.BLACK);
  		//g.drawString("Camera: "+camera.frame.getX() + "," + camera.frame.getY(), 20, 20);
  		//g.drawString("In air: " + air, 20, 40);
  		//g.drawString("Airtime: " + airTime, 20, 60);
  		//g.drawString("v_down: " + v_down, 20, 80);
  		int xx = (int)(mouse.getX() + camera.getX());
  		//xx = (int)(mouse.getX());
  		
  		int yy = (int)(mouse.getY() + camera.getY());
  		//yy =  (int)(mouse.getY());
  		
  		g.drawString("mouse: (" +xx  + "," + yy + ")", 20, 20);
  		//g.drawString("mapmaking: " + drawing, 20, 120);
  		g.drawString("clicked: " + clicked, 20, 40);
  		//g.drawString("dragged: " + dragged, 20, 160);
  		g.drawString("normal2: " + player.normal, 20, 60);
  		g.drawString("selectedEnt: " + selectedEnt, 20, 80);
  		//g.drawString("size: " + currentMap.getEntities().size(), 20, 220);
  		g.drawString("map name: " + currentMap.name, 20, 100);
  		g.drawString("# of maps: " + maps.size(), 20, 120);
  		g.drawString("Shoot point: " + "(" + shoot.getX() +  "," + shoot.getY() + ")", 20, 140);
  		g.drawString("Dragged point: " + "(" + dragged.getX() +  "," + dragged.getY() + ")", 20, 160);
  		g.drawString("v_x: " + player.v_x, 20, 180);
  		g.drawString("hooked: " + hookedOn, 20, 200);
  		//g.drawString("ceiling: " + player.hitCeiling, 20, 280);
  		//g.drawString("onPortal: " + player.onPortal, 20, 300);
  		//g.drawString()
		
  	}
  	
  	/*
  	 *	Creates a new map
  	 */
  	public void newMap() throws IOException
  	{
  		currentMap.remove(player);
  		currentMap = new Map();
  		maps.add(currentMap);
  		player.setParent(currentMap);
  		player.setLocation(100, 100);
  		currentMap.add(player);
  	}
  	
  	/*
  	 *	Creates a new bot
  	 */
  	public void newBot()
  	{
  		//currentMap.add(new Bot())
  	}
  	
  	/*
  	 *	Timer on the map
  	 */
  	public void onTick()
  	{
  		if(!pause)
  		{
	  		tick++;
	  		delay++;
			currentMap.onTick();
			Entity r = null;
			if(player!=null)
				player.accel();
			if(delay >50)
			{
				for(int i = 0; i<addQueue.size(); i++)
				{
					currentMap.add(addQueue.get(i));
					r = addQueue.get(i);
					break;
				}
				if(r!=null)
					addQueue.remove(r);	
				delay = 0;
			}

			//addQueue.clear();
			handle();	
  		}
		frame.repaint();
		
		
  	}
  	
  	/*
  	 *	Pauses the game if toggled
  	 */
	public void togglePause()
	{
		if(pause)
			print("Game has been resumed");
		else
			print("Game has been paused");
		//probably need to do a better job with the pause because pause is pausing everything, even console functions.
		
		pause = !pause;
	}
	
	/*
	 *	Checks if user as clicked
	 */
  	public boolean clickCheck()
  	{
  		for(Entity e: currentMap.getEntities())
  		{
  			int ax = (int)(clicked.getX());
  			int ay = (int)(clicked.getY());
  			if(e.mesh.contains(new Point((int)((ax/wmx) + camera.getX()), (int)(((ay)/wmy)+camera.getY()))))
  			{
  				
  				for(Entity e2: currentMap.getEntities())
  				{
  					e2.selected2 = false;
  				}
				e.selected2 = true;
			  	selectedEnt  = e;
			  	selectedEnt2 = e;
  				return true;
  			}
  			
  		} 
  		selectedEnt2 = null;
  		return false;
  	}
  	
  	/*
  	 *	Deleted selected particles
  	 */
  	public void deleteSelection()
  	{
  		currentMap.remove(selectedEnt);
  		
  	}
  	
  	/*
  	 *	Closes the console
  	 */
  	public void closeConsole()
  	{
		input.setEditable(false);
		input.setText("");					
		if(consoleOn)
			dialog.dispose();

		consoleOn = false;	 		
  	}
  	
  	/*
  	 *	Refreshes the screen
  	 */
  	public static void reprogress()
  	{
  		progress++;
  		splash.repaint();
  	}
  	
  	/*
  	 *	Starts the game
  	 */
  	public static void start()throws ConcurrentModificationException, UnsupportedAudioFileException, LineUnavailableException, IOException
  	{
  		 System.out.println("passed");
  		 splash = new JFrame();
  		 splash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		 splash.setSize(400, 200);
  		 splash.setLocationRelativeTo(null);
  		 splash.setUndecorated(true);
  		 splash.add(new SplashPanel());
  		 
  		 
  		 splash.setVisible(true);
  		 
  		 
  		Runner game = new Runner();
  		game.init();  
  	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	    frame.setUndecorated(true); 
  		//BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
  	    frame.add(game); 
  	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		double width = screenSize.getWidth();
  		double height = screenSize.getHeight();
  	    switch(StartMenu.size)
  	    {
  	    case StartMenu.SIZE_0:
  	    	width = 640;
  	    	height = 480;
  	    	break;
  	    case StartMenu.SIZE_1:
  	    	width = 800;
  	    	height = 600;
  	    	break;
  	    case StartMenu.SIZE_2:
  	    	width = 1024;
  	    	height = 768;
  	    	break;
  	    case StartMenu.SIZE_3:
  	    	width = 1280;
  	    	height = 800;
  	    	break;    	
  	    
  	    }

  		wmx = (width/DEFAULT_WIDTH);
  	    wmy = (height/DEFAULT_HEIGHT);
  	    reprogress();	

  	    		
  		frame.setSize((int)(1280 * wmx), (int)(720*wmy)); 
  	    frame.setVisible(true); 
  	    splash.dispose();

  	    while(true)
  	    {
  	    	try {
  					Thread.sleep(40);
  					game.onTick();
  					
  					if(isResize) //resize thing
  					{
  						game.closeConsole();
  						width = w_resize;
  						height = h_resize;
  						wmx = (width/DEFAULT_WIDTH);
  					    wmy = (height/DEFAULT_HEIGHT);
  						frame.setVisible(false);
  						frame.setSize((int)(1280 * wmx), (int)(720*wmy));
  						
  						frame.setVisible(true);
  						isResize = false;
  						game.showConsole();
  					}
  					if(isBorder) //border thing
  					{
  						
  						frame.dispose();
  						
  						if(doBorder)
  						{
  							frame.setUndecorated(true);
  							doBorder = false;
  						}
  						else
  						{
  							frame.setUndecorated(false);							
  							doBorder = true;
  						}
  					    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  					    frame.add(game); 
  					    
  						game.closeConsole();
  						width = 1280;
  						height = 720;
  						wmx = (width/DEFAULT_WIDTH);
  					    wmy = (height/DEFAULT_HEIGHT);
  						frame.setVisible(false);
  						frame.setSize((int)(1280 * wmx), (int)(720*wmy));			
  						
  						frame.setVisible(true);					
  						isBorder = false;
  					}
  					
  				} catch(Exception e) {
  					break;
  				}
  		}	 		
  	}
  	
  	public static void main(String[] args) throws ConcurrentModificationException, UnsupportedAudioFileException, LineUnavailableException, IOException
  { 
  	ImageLoader.load();
	 StartMenu.createAndShowGUI();
	 
	 while(!StartMenu.startButton)
	 {
		 try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ///System.out.println("wait");
	 }
  	start();
	 

  } 
	
  	  		
  		
  		
  	



class MyKeyListener extends KeyAdapter {
		/*
		 *	Checks if a key was pressed
		 *	
		 *	@param e Key pressed
		 */
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) {  
				
			case KeyEvent.VK_ENTER:
					if(player.onPortal)
					{
						player.portal.refresh();
						currentMap = player.portal.teleport(currentMap, player);
						frame.dispose();
						Meow meow = new Meow();
					}
				break;
			case KeyEvent.VK_D: 
					right = true;
				break;
				
			case KeyEvent.VK_A: 
				
				left = true;
				break;
				
			case KeyEvent.VK_W:
					
				
				break;
				
			case KeyEvent.VK_S:
					
					
				break;
				
			case KeyEvent.VK_B:
				
				showBones();
				break;											
			case KeyEvent.VK_RIGHT: 
				
					right = true;
				
				break;
				
			case KeyEvent.VK_LEFT: 
					left = true;
				break;

			case KeyEvent.VK_UP: 
				
				break;
				
			case KeyEvent.VK_DOWN: 
				
				break;
				
			case KeyEvent.VK_Y: 
				
				break;
				
			case KeyEvent.VK_CONTROL: 
				drawing = !drawing;
				
				break;
					
			case KeyEvent.VK_SHIFT:
				if(!drawing && reeling == false)
				{
					line = new Line(shoot, player.getCenter(), player);
					if(hookedOn !=null){
						hookedOn.releaseLine();	
					}	

					
					
					
				}
				reeling = true;
				
				break;
									
			case KeyEvent.VK_1: 
				
				
				break;	
									
			case KeyEvent.VK_2: 
				
			
				break;	
					
			case KeyEvent.VK_3: 
				
				
				break;
			case KeyEvent.VK_4: 
				
				
				break;					
			case KeyEvent.VK_5: 
				
				
				break;					
			case KeyEvent.VK_6: 
				
				
				break;						
														
			case KeyEvent.VK_SPACE:
				if(!air)
				{
					jumping = true;	
				}
				

				break;	
															
			case KeyEvent.VK_R:
				

				break;	
					
					
											    		
			}	
	
			}
			
		/*
		 *	Checks if a key was released
		 *
		 *	@param e key released
		 */
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) {  
				
				case KeyEvent.VK_D: 
					right = false;	
					
					break;
					
				case KeyEvent.VK_A: 
						left = false;
						
					break;
					
				case KeyEvent.VK_W: 
						
						
					break;	
						
				case KeyEvent.VK_S: 
						
						
					break;							
										
				case KeyEvent.VK_RIGHT: 
						right = false;
					break;
					
				case KeyEvent.VK_LEFT: 
						left = false;
					break;
					
				case KeyEvent.VK_UP: 
					
					break;	
						
				case KeyEvent.VK_DOWN: 
					
					break;
														
				case KeyEvent.VK_SPACE: 
					//jumping = false;
					//player.stopJump();
					
					break;	
				case KeyEvent.VK_SHIFT: 
					reeling = false;
					if(hookedOn!=null)
						hookedOn.releaseLine();
					break;			
				case KeyEvent.VK_CONTROL: 
					
					
					break;					
				case KeyEvent.VK_ESCAPE:
					frame.setVisible(false);
					frame.dispose();
					System.exit(0);
					break;	
																						
				case KeyEvent.VK_BACK_QUOTE:
					if(consoleOn)
					{
						input.setEditable(false);
						input.setText("");					
						if(consoleOn)
							dialog.dispose();
		
						consoleOn = false;							
					}
					else
					{
						showConsole();
					}
					
					
					break;					    		
			}	
	
			}			
			
		}
		
class MyMouseListener implements MouseListener{

	boolean found;
	/*
	 *	Adds an entity to the map
	 */
	public void addDrawnType()
	{
		//System.out.println(selectedID + "yo");
		switch(selectedID)
		{
			case 0: 
			   	try {
						currentMap.add(new Platform((int)(meshDrawn.getX() / wmx + camera.getX()), 
								(int)(meshDrawn.getY() / wmy + camera.getY()), 
								(int)(meshDrawn.getWidth() / wmx ), 
								(int)(meshDrawn.getHeight() / wmy), 
								currentMap));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   	break;
			case 1: 
				try{
				currentMap.add(new Bot((int)(meshDrawn.getX() / wmx + camera.getX()), 
						(int)(meshDrawn.getY() / wmy + camera.getY()), 
						(int)(meshDrawn.getWidth() / wmx ), 
						(int)(meshDrawn.getHeight() / wmy), 
						currentMap));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			break;
			case 2: 
			   	try {
						currentMap.add(new MovingPlatform((int)(meshDrawn.getX() / wmx + camera.getX()), 
								(int)(meshDrawn.getY() / wmy + camera.getY()), 
								(int)(meshDrawn.getWidth() / wmx ), 
								(int)(meshDrawn.getHeight() / wmy), 
								currentMap));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   	break;			
			case 3: 
			   	try {
						currentMap.add(new Decoration((int)(meshDrawn.getX() / wmx + camera.getX()), 
								(int)(meshDrawn.getY() / wmy + camera.getY()), 
								(int)(meshDrawn.getWidth() / wmx ), 
								(int)(meshDrawn.getHeight() / wmy), 
								currentMap));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   	break;	
			case 4: 
			   	try {
						currentMap.add(new Portal((int)(meshDrawn.getX() / wmx + camera.getX()), 
								(int)(meshDrawn.getY() / wmy + camera.getY()), 
								(int)(meshDrawn.getWidth() / wmx ), 
								(int)(meshDrawn.getHeight() / wmy), 
								currentMap));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   	break;	
			case 5: 
			   	try {
						currentMap.add(new Spawner((int)(meshDrawn.getX() / wmx + camera.getX()), 
								(int)(meshDrawn.getY() / wmy + camera.getY()), 
								(int)(meshDrawn.getWidth() / wmx ), 
								(int)(meshDrawn.getHeight() / wmy), 
								currentMap));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   	break;				   	
		}
	}
	
	/*
	 *	Checks if mouse is pressed
	 *
	 *	@param e mouse pressed
	 */
	public void mousePressed(MouseEvent e)
	{
		clicked = e.getPoint();
		//found = clickCheck();
		clickCheck();
		found = false;
		
    	int x = (int)(e.getPoint().getX()/wmx)+(camera.getX());
		int y = (int)(e.getPoint().getY()/wmy)+(camera.getY());
		shoot = new Point(x, y);

		
		hookedOn = selectedEnt2;
		if(hookedOn != null)
			hookedOn.setLine(line);	

		
	}
	
	/*
	 *	Checks if mouse is released
	 *
	 *	@param e mouse released
	 */
    public void mouseReleased(MouseEvent e) {
    	
    	if(drawing)
    	{
    		if(found)
    		{
    			found = false;
    			//System.out.println("breaking");
    		}
    		else
    		{
    			//System.out.println(selectedID);
    			addDrawnType();
    		}
  		
    	}
 
    	
    	meshDrawn = new Rectangle(0, 0, 0, 0);
    }
	
	/*
	 *	Checks if mouse is entered
	 *
	 *	@param e mouse entered
	 */
    public void mouseEntered(MouseEvent e) {
       
    }

	/*
	 *	Checks if mouse is exited
	 *
	 *	@param e mouse exited
	 */
    public void mouseExited(MouseEvent e) {
       
    }

	/*
	 *	Checks if mouse is clicked
	 *
	 *	@param e mouse clicked
	 */
    public void mouseClicked(MouseEvent e) {
       

    	
    	
    }

  
 }

class MyMotionListener implements MouseMotionListener {


	/*
	 *	Check if mouse was moved
	 *
	 *	@param e mmouse is moved
	 */
    public void mouseMoved(MouseEvent e) {
 		
       mouse = new Point(((int)(e.getX() / wmx)), ((int)(e.getY()/wmy)));
       
        //setMouseDirection(e.getY(), e.getX());
    }
	
	/*
	 *	Checks if mouse is dragged
	 *
	 *	@param e mouse dragged
	 */
    public void mouseDragged(MouseEvent e) {
    	if(drawing)
    	{
    		dragged = e.getPoint();
    		meshDrawn = new Rectangle((int)(clicked.getX()), (int)(clicked.getY()), (int)(dragged.getX() - clicked.getX()), (int)(dragged.getY() - clicked.getY()));
    	}
    	else if(dragging)
    	{
    		dragged = e.getPoint();
    		int draggedx = (int)(e.getPoint().getX()/wmx)+(camera.getX());
    		int draggedy = (int)(e.getPoint().getY()/wmy)+(camera.getY());
    		int diffx = draggedx - selectedEnt.getX();
    		int diffy = draggedy - selectedEnt.getY();
    		Entity v = selectedEnt;
    		
    		v.setLocation(v.getX() + diffx, v.getY() + diffy);
    		
    	}
    }


}
	
class ConsoleKeyListener extends KeyAdapter {
		/*
		 *	Check if key is pressed
		 *
		 *	@param e key that is pressed
		 */
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) {  
			
				
					
											    		
			}	
	
			}
		/*
		 *	Check if key is released
		 *
		 *	@param e key that is released
		 */	
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) {  
				
				case KeyEvent.VK_ENTER:
					
					if(!(input.getText().equals("")))
					{
						command = input.getText();
						if(consoleText.equals(""))
						{
							consoleText = input.getText();
						}
						else
						{
							consoleText = consoleText + "\n" + input.getText();	
						}						
					}

					
					input.setText("");
					field.setText(consoleText);
				try {
					handleCommand();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					//System.out.println(command);
					break;
					
				case KeyEvent.VK_BACK_QUOTE:
					e.consume();
					input.setEditable(false);
					input.setText("");					
					if(consoleOn)
						dialog.dispose();
	
					consoleOn = false;	
					break;			
																					
					    		
			}	
	
			}			
			
		}






		
 	}  	



		
 	  	










  	

 