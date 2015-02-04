
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
        
    
    public Meow()
    {
       	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)dim.getWidth() * 4 /5;
        height = (int)dim.getHeight() * 4 /5;
        
		frame = new JFrame("Meow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);  	
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		pan = new JPanel();
		pan.setLayout(new GridLayout(3, 4));
		pan.setBackground(Color.red);
		pan.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() * 1/2));
		
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
		
		pane = new JPanel();
		pane.setLayout(new GridLayout(3, 3));
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() * 1/2));
		
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
    
    
    public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			frame.dispose();
		}
	}			
			
			
			
    public static void main(String[] args) 
    {
;
		Meow meow = new Meow();

		

       
       
       
    }
}
