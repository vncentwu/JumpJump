import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class BackPanel extends JPanel
{
	BufferedImage img;
	BufferedImage wood;
	int tick;
	int woodTick;
	boolean black;
	
	/*
	 *	Default constructor for a backpanel
	 */
	public BackPanel()
	{
		super();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("background1.png");
		try {
			img = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		input = classLoader.getResourceAsStream("woodScroll.gif");
		try {
			wood = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		
		repaint();
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
		@Override
		public void run() {
			tick++;
			woodTick++;
			repaint();
		}
		}, 30, 30);
		
		
	}
	
	/*
	 *	Constructs a backpanel
	 *
	 *	@param name 	name of the panel
	 */
	public BackPanel(String name)
	{
		super();
		/*
		img = null;
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
		}
		System.out.println(img);*/
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = null;
		
		if(!(name == "black"))
		{
			
			input = classLoader.getResourceAsStream(name);	
			try {
				img = ImageIO.read(input);
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
			
			}
		else
			black = true;
	
		input = classLoader.getResourceAsStream("woodScroll.gif");
		try {
			wood = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}					
		
		
		repaint();
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
		@Override
		public void run() {
			tick++;
			woodTick++;
			repaint();
		}
		}, 30, 30);
		
		
	}	
	
	/*
	 *	Paint component
	 *
	 *	@param g Graphics of the paint component
	 */
	public void paintComponent(Graphics g)
	{

		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());
		//if(img == null)
			//System.out.println("wtf");
		//System.out.println(tick);
		//if(ImageLoader.menuBackground!=null)
		if(!black)
		{
			if(tick > img.getWidth() - getWidth())
				tick = 0;
			g.drawImage(img, tick * -1, 0, img.getWidth(), getHeight(), null);			
		}
		else
		{
			g.setColor(Color.black);
			g.fillRect(0,0, getWidth(), getHeight());
		}
		
		if(woodTick > wood.getWidth() - getWidth())
			woodTick = 0;			
		
		g.drawImage(wood, -1 * wood.getWidth() + woodTick * 1 + getWidth(), 0, wood.getWidth(), getHeight()/30, null);
		g.drawImage(wood, -1 * wood.getWidth() + woodTick * 1 + getWidth(), getHeight() - getHeight()/30, wood.getWidth(), getHeight()/30, null);
	}
	
	
	
	
}