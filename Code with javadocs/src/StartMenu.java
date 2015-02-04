/**
 * @(#)StartMenu.java
 *
 *
 * @author 
 * @version 1.00 2014/5/27
 */
import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

public class StartMenu 
{
	/*
	 *	Full screen x and y
	 */
	static int x =	(int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	static int y = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
	private JLabel Display1 = new JLabel(new ImageIcon("Vinnie.gif"));
	static OutlineLabel start;
	static OutlineLabel option;
	static OutlineLabel exit;
	static boolean startButton;
	
	public static final int SIZE_0 = 0;
	public static final int SIZE_1 = 1;
	public static final int SIZE_2 = 2;
	public static final int SIZE_3 = 3;
	public static final int SIZE_4 = 4;
	

	public static int size = SIZE_4;
	
	public static void main(String[] args)
	{
		createAndShowGUI();
	}
	
	/*
	 *	Creates the start menu
	 */
    public static void createAndShowGUI()
    {
    	final JFrame frame = new JFrame("JumpJump");
    	frame.setUndecorated(true);
    	frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new BackPanel();
		panel.setLayout(new GridLayout(5,3));
		
		start = new OutlineLabel("START");
		start.setOutlineColor(Color.white);
		start.setForeground(Color.black);
		
		start.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    	start.setBorderPainted(false);
    	start.setContentAreaFilled(false);
    	start.setFocusPainted(false);
	
		start.setFont(new Font("Calibri", Font.BOLD, 70));
		start.setHorizontalTextPosition(JButton.CENTER);
		start.setVerticalTextPosition(JButton.CENTER);
		
	    start.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    start.setFont(new Font("Calibri", Font.PLAIN, 70));
		    start.setOutlineColor(Color.white);
	    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    start.setFont(new Font("Calibri", Font.BOLD, 70));
		    start.setOutlineColor(Color.white);
	    }
	    });
		
		
		
		option = new OutlineLabel("OPTION");
		option.setOutlineColor(Color.white);
		option.setForeground(Color.black);

		option.setFont(new Font("Calibri", Font.BOLD, 70));
		option.setHorizontalTextPosition(JButton.CENTER);
		option.setVerticalTextPosition(JButton.CENTER);
		option.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    	option.setBorderPainted(false);
    	option.setContentAreaFilled(false);
    	option.setFocusPainted(false);		
	    option.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    option.setFont(new Font("Calibri", Font.PLAIN, 70));
		    option.setOutlineColor(Color.white);
	    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    option.setFont(new Font("Calibri", Font.BOLD, 70));
		    option.setOutlineColor(Color.white);
	    }
	    });		

		exit = new OutlineLabel("EXIT");
		exit.setOutlineColor(Color.white);
		exit.setForeground(Color.black);
		
		exit.setFont(new Font("Calibri", Font.BOLD, 70));
		exit.setHorizontalTextPosition(JButton.CENTER);
		exit.setVerticalTextPosition(JButton.CENTER);
		exit.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    	exit.setBorderPainted(false);
    	exit.setContentAreaFilled(false);
    	exit.setFocusPainted(false);	
 	    exit.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    exit.setFont(new Font("Calibri", Font.PLAIN, 70));
		    exit.setOutlineColor(Color.white);
	    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    exit.setFont(new Font("Calibri", Font.BOLD, 70));
		    exit.setOutlineColor(Color.white);
	    }
	    });	   				
		/*JLabel extra = new JLabel("", new ImageIcon("Vinnie.gif"), JLabel.CENTER); // Eyepatch man
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
		JLabel grassPanel1 = new JLabel("",new ImageIcon("colorGrassRight.gif"),JLabel.RIGHT); // grass name*/

		JLabel extra = new JLabel(""); // Eyepatch man
		JLabel extra1 = new JLabel(""); //Lel. Lawnmower and grass
		JLabel extra2 = new JLabel(""); //Jellohead
		JLabel extra3 = new JLabel(""); // Beared wizard
		JLabel extra4 = new JLabel(""); // SS Vin. Sunshine
		JLabel extra5 = new JLabel(""); //Uh huh
		JLabel skyPanel = new JLabel(""); //  blank sky title
		JLabel skyPanel1 = new JLabel(""); // blank sky
		JLabel skyPanel2 = new JLabel(""); // blank sky
		JLabel skyPanel3 = new JLabel(""); // blank sky title
		JLabel grassPanel = new JLabel(""); // grass fox
		JLabel grassPanel1 = new JLabel(""); // grass name
		
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
	
	
		/*
		 *	Start button
		 */
		start.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				startButton = true;
				try
				{
					//Runner.start();	
				}
				catch(Exception j)
				{
				}
			}
		});
		
		/*
		 *	Option button
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
		 *	Exit button
		 */
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Confirmation on quitting
				//int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
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
		panel.repaint();
		frame.repaint();
		frame.setVisible(true);
    }
    
    /*
     *	Sets the new sizes of frame
     */
    public static void setSize(JFrame o, int x, int y)
    {
    	o.setSize(x,y);
    }
    
    /*
     *	New x value of frame
     */
    public static void setX(int newX)
    {
    	x = newX;
    }
    
    /*
     *	New y value of frame
     */
    public static void setY(int newY)
    {
    	y = newY;
    }
    
    /*
     *	Option frame
     */
    public static void createOptionFrame()
    {
    	final JFrame frame1 = new JFrame("Options");
		//JFrame.setDefaultLookAndFeelDecorated(true);
		frame1.setUndecorated(true);
		//frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new BackPanel();
		panel.setLayout(new GridLayout(0,7,5, 0));
		
		JButton screenSize0 = new JButton("640x480"); 
		screenSize0.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	//screenSize1.setBorderPainted(false);
    	//screenSize0.setContentAreaFilled(false);
    	//screenSize0.setBackground(Color.white);
    	screenSize0.setFocusPainted(false);			
		
		JButton screenSize1 = new JButton("800x600"); 
		screenSize1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	//screenSize1.setBorderPainted(false);
    	//screenSize1.setContentAreaFilled(false);
    	screenSize1.setFocusPainted(false);			
			
			
			
		JButton screenSize2 = new JButton("1024x768");
		screenSize2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	//screenSize2.setBorderPainted(false);
    	//screenSize2.setContentAreaFilled(false);
    	screenSize2.setFocusPainted(false);			
		
		
		JButton screenSize3 = new JButton("1280x800");
		screenSize3.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	//screenSize3.setBorderPainted(false);
    	//screenSize3.setContentAreaFilled(false);
    	screenSize3.setFocusPainted(false);			
		
		JButton screenSize4 = new JButton("Fullscreen");
		screenSize4.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	//screenSize4.setBorderPainted(false);
    	//screenSize4.setContentAreaFilled(false);
    	screenSize4.setFocusPainted(false);			
		
		JButton back = new JButton("Back");
		back.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	//back.setBorderPainted(false);
    	//back.setContentAreaFilled(false);
    	back.setFocusPainted(false);			
		
		for(int i = 0; i< 22; i++)
			panel.add(new JLabel(""));
			
		panel.add(screenSize0);
		panel.add(screenSize1);
		panel.add(screenSize2);
		panel.add(screenSize3);
		panel.add(screenSize4);
		for(int i = 0; i< 11; i++)
			panel.add(new JLabel(""));
		
		panel.add(back);
		for(int i = 0; i< 18; i++)
			panel.add(new JLabel(""));		
	
		
		
		// 640 x 480 frame
		screenSize0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setX(640);
				setY(480);
				createAndShowGUI();
				frame1.dispose();
				size = SIZE_0;
			}
		});		
		
		// 800 x 600 frame
		screenSize1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setX(800);
				setY(600);
				createAndShowGUI();
				frame1.dispose();
				size = SIZE_1;
				
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
				size = SIZE_2;
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
				size = SIZE_3;
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
				size = SIZE_4;
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