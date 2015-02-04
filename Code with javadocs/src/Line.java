import java.awt.Point;


public class Line {

	public static final int xsteps = 10;
	public static final int ysteps = 10;
	public static final int add = 3;
	Point dest;
	Point original;
	int xcount;
	int ycount;
	int xspeed;
	int yspeed;
	int count;
	Entity player;
	boolean passed;
	
	int finalCount;
	
	/*
	 *	Contructs a line
	 *	@param dest			destination of line
	 *	@param original		starting point of line
	 *	@param player		player that moves
	 */
	public Line(Point dest, Point original, Entity player)
	{
		this.dest = dest;
		this.original = original;
		this.player = player;
		double diff = dest.getX() - original.getX();
		double div = diff/xsteps;
		xspeed = (int)div;
		diff = dest.getY() - original.getY();
		div = diff/ysteps;
		yspeed = (int)div;
		count = 1;
		finalCount = 5;
		if(player!=null)
			player.tethered = true;
	}
	
	/*
	 *	Releases the line
	 */
	public void release()
	{
		player.tethered = false;
	}
	
	/*
	 *	Checks to see if player can release
	 */
	public void check()
	{
		if((Math.abs(player.getCenter().getX() - dest.getX()) < 30) && (Math.abs(player.getCenter().getY() - dest.getY()) < 30))
		{
			passed = true;
			release();
			
		}
	}
	
	/*
	 *	Gets the X distance that player traveled
	 *
	 *	@return the X distance player traveled 
	 */
	public int getXDist()
	{
		if(passed)
			return 0;
		count++;
		check();
		//if(passed)
		//{
			//return 0;
			//finalCount--;
			//if(finalCount <0)
				//return 0;
		//}
		player.v_x = (int)(((double)xspeed)*((double)count/20));
		//return (int)(((double)xspeed)/((double)count/3));
		//return xspeed/3;
		return (int)(((double)xspeed)*((double)count/20));
	}
	
	/*
	 *	Gets the Y distance that player traveled
	 *
	 *	@return the Y distance player traveled 
	 */
	public int getYDist()
	{
		//if(passed)
		//{
			//if(finalCount <0)
				//return 0;
		//}
		if(passed)
			return 0;
		player.v_y = (int)(((double)yspeed)*((double)count/20));
		return (int)(((double)yspeed)*((double)count/20));
	}	
	
	/*
	 *	Sets the destination of point
	 *
	 *	@param p destination of point
	 */
	public void setDest(Point p)
	{
		dest = p;
	}
}

