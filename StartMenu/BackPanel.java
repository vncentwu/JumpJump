import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class BackPanel extends JPanel
{
	BufferedImage img;
	int tick;
	
	public BackPanel()
	{
		super();
		img = null;
		try {
			img = ImageIO.read(new File("background1.png"));
		} catch (IOException e) {
		}
		System.out.println(img);
		repaint();
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
		@Override
		public void run() {
			tick++;
			repaint();
		}
		}, 30, 30);
		
		
	}
	public void paintComponent(Graphics g)
	{

		g.setColor(Color.cyan);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(img == null)
			System.out.println("wtf");
		System.out.println(tick);
		g.drawImage(img, tick * -1, 0, img.getWidth(), getHeight(), null);
	}
	
	
	
	
}