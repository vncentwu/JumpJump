import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class SplashPanel extends JPanel {
    private BufferedImage image;
    public static String status;
    private int x;
    private int y;
    
    /*
     *	Constructs a splash panel
     */
    public SplashPanel()
    {
    	status = "started";
    	x = 0;
    	y = 0;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("5key.png");
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	   	
    }
    
    /*
     *	paint method
     *	
     *	@param g	graphics of paint 
     */
    public void paint(Graphics g) {
    	g.setColor(Color.black);
    	g.fillRect(0, 0, 400, 200);
        g.drawImage(image, x + 5, y + 5, 390, 170, this);
    	g.setColor(Color.white);
    	g.drawString(status, 0, 20);
        //g.setColor(Color.black);
        //g.fillRect(0, 0, 400, 200);
        //g.setColor(Color.white);
        //g.drawString("Progress" + Runner.progress, 0, 20);
        g.setColor(Color.green);
        double mod = (double)(Runner.progress)/10;
        g.fillRect(5, 175, (int)(390 * mod), 20);
    }
}
