
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Meow{
        
        
        
    static JFrame frame;
    static JPanel pan;
    static JPanel pane;  
    static JButton play;
    	
    static int width;
    static int height;
        
    /*
     *	Constructor for endgame screen
     */
     
    public Meow()
    {
       	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)dim.getWidth() * 4 /5;
        height = (int)dim.getHeight() * 4 /5;
        
		frame = new JFrame("Meow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);  	
		frame.setUndecorated(true);
		
		BackPanel panel = new BackPanel("dots.png");
		//panel.setBackground(Color.red);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		pan = new BackPanel("background_night.gif");
		pan.setLayout(new GridLayout(3, 4));
		//pan.setBackground(Color.red);
		pan.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() * 2/3));
		
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel(""));
		pan.add(new JLabel("Score: "));
		pan.add(new JLabel("Level:"));
		pan.add(new JLabel(""));
		
		pane = new BackPanel("black");
		pane.setLayout(new GridLayout(3, 3));
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() * 1/3));
		
		play = new JButton("Play Again");
		play.setBackground(Color.black);
		play.setForeground(Color.white);
		play.addActionListener(new ButtonListener());
		
	/*	play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
	*/	
		
		pane.add(new JLabel(""));
		pane.add(play);
		pane.add(new JLabel(""));
		pane.add(new JLabel(""));
		pane.add(new JLabel(""));
		pane.add(new JLabel(""));
		pane.add(new JLabel(""));
		pane.add(new JLabel(""));
		pane.add(new JLabel(""));
		panel.add(pan);
		panel.add(pane);
		
		frame.add(panel);
		frame.setVisible(true);		    	
    }
    
    /*
     *	Disposes the frame
     */
    public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			frame.dispose();
		}
	}			
			
			
			
    public static void main(String[] args) 
    {

		Meow meow = new Meow();
   
    }
}
