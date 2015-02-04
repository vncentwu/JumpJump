/**
 * @(#)StartMenu.java
 *
 *
 * @author 
 * @version 1.00 2014/5/27
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartMenu 
{
	/*
	 *Full screen x and y
	 */
	static int x =	(int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	static int y = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
	private JLabel Display1 = new JLabel(new ImageIcon("Vinnie.gif"));
	public static void main(String[] args)
	{
		createAndShowGUI();
	}
	
    public static void createAndShowGUI()
    {
    	final JFrame frame = new JFrame("LoLol XD RofL LMAO");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,3));
		
		JButton start = new JButton("Start");
		start.setIcon(new ImageIcon("skyPaneBlank.gif"));
		start.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 70));
		start.setHorizontalTextPosition(JButton.CENTER);
		start.setVerticalTextPosition(JButton.CENTER);
	//	start.setBorderPainted(false);
		
		JButton option = new JButton("Option");
		option.setIcon(new ImageIcon("skyPaneBlank.gif"));
		option.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 70));
		option.setHorizontalTextPosition(JButton.CENTER);
		option.setVerticalTextPosition(JButton.CENTER);
	//	option.setBorderPainted(false);
	
		JButton exit = new JButton("Exit");
		exit.setIcon(new ImageIcon("skyPaneBlank.gif"));
		exit.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 70));
		exit.setHorizontalTextPosition(JButton.CENTER);
		exit.setVerticalTextPosition(JButton.CENTER);
		
		JLabel extra = new JLabel("", new ImageIcon("Vinnie.gif"), JLabel.CENTER); // Eyepatch man
		JLabel extra1 = new JLabel("", new ImageIcon("YakuzaVin.gif"), JLabel.LEFT); //Lel. Lawnmower and grass
		JLabel extra2 = new JLabel("", new ImageIcon("JelloJanet.gif"), JLabel.CENTER);; //Jellohead
		JLabel extra3 = new JLabel("", new ImageIcon("HenrytheSage.gif"), JLabel.CENTER); // Beared wizard
		JLabel extra4 = new JLabel("", new ImageIcon("YellowVin.gif"), JLabel.CENTER); // SS Vin. Sunshine
		JLabel extra5 = new JLabel("", new ImageIcon("Toma-Chen.gif"), JLabel.CENTER); //Uh huh
		JLabel skyPanel = new JLabel("",new ImageIcon("skyPaneTitle.gif"),JLabel.CENTER); //  blank sky title
		JLabel skyPanel1 = new JLabel("",new ImageIcon("skyPaneBlank.gif"),JLabel.CENTER); // blank sky
		JLabel skyPanel2 = new JLabel("",new ImageIcon("skyPaneBlank.gif"),JLabel.CENTER); // blank sky
		JLabel skyPanel3 = new JLabel("",new ImageIcon("skyPaneTitle.gif"),JLabel.CENTER); // blank sky title
		JLabel grassPanel = new JLabel("",new ImageIcon("colorGrassLeft.gif"),JLabel.CENTER); // grass fox
		JLabel grassPanel1 = new JLabel("",new ImageIcon("colorGrassRight.gif"),JLabel.RIGHT); // grass name
		
		panel.add(extra);
		panel.add(extra5);
		panel.add(extra4);
		panel.add(skyPanel);
		panel.add(start);
		panel.add(skyPanel1);
		panel.add(extra3);
		panel.add(option);
		panel.add(extra2);
		panel.add(skyPanel2);
		panel.add(exit);
		panel.add(skyPanel3);
		panel.add(grassPanel);
		panel.add(extra1);
		panel.add(grassPanel1);
	
		//I don't know what else you wanted with this
		/*
		 *Start button
		 */
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			//	frame.dispose();
			//	startButton = true;
			}
		});
		
		/*
		 *Option button
		 */
		option.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				createOptionFrame();
				
			}
		});
		
		/*
		 *Exit button
		 */
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Confirmation on quitting
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
				
				else if(reply == JOptionPane.NO_OPTION)
				{
				}
			}
		});
		
		frame.add(panel);
		frame.pack();
		setSize(frame,x,y);
		frame.setVisible(true);
    }
    
    /*
     *Sets the new sizes of frame
     */
    public static void setSize(JFrame o, int x, int y)
    {
    	o.setSize(x,y);
    }
    
    /*
     *New x value of frame
     */
    public static void setX(int newX)
    {
    	x = newX;
    }
    
    /*
     *New y value of frame
     */
    public static void setY(int newY)
    {
    	y = newY;
    }
    
    /*
     *Option frame
     */
    public static void createOptionFrame()
    {
    	final JFrame frame1 = new JFrame("Options");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,5));
		JButton screenSize1 = new JButton("800x600"); 
		JButton screenSize2 = new JButton("1024x768");
		JButton screenSize3 = new JButton("1280x800");
		JButton screenSize4 = new JButton("Fullscreen");
		JButton back = new JButton("Back");
		panel.add(screenSize1);
		panel.add(screenSize2);
		panel.add(screenSize3);
		panel.add(screenSize4);
		panel.add(back);
		
		// 800 x 600 frame
		screenSize1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setX(800);
				setY(600);
				createAndShowGUI();
				frame1.dispose();
				
			}
		});
		
		// 1024 x 768 frame
		screenSize2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setX(1024);
				setY(768);
				createAndShowGUI();
				frame1.dispose();
				
			}
		});
		
		// 1280 x 800 frame
		screenSize3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setX(1280);
				setY(800);
				createAndShowGUI();
				frame1.dispose();
				
			}
		});
		
		// Fullscreen
		screenSize4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setX((int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth());
				setY((int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight());
				createAndShowGUI();
				frame1.dispose();
				
			}
		});
		
		//Back to first frame
		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame1.dispose();
				createAndShowGUI();
				
			}
		});
		frame1.add(panel);
		frame1.pack();
		setSize(frame1, x, y);
		frame1.setVisible(true);
    }
}