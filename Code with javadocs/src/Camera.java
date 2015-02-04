
import java.awt.Point;
import java.awt.Rectangle;



public class Camera {

	Rectangle frame;
	Player player;
	/*
	 *	Constructs a view of the player
	 *
	 *	@param player player the frame is going to be on
	 */
	public Camera(Player player)
	{
		this.player = player;
		frame = new Rectangle(0, 0, 1280, 720);
	}
	
	/*
	 *	Refreshes the frame at half size
	 */
	public void refresh()
	{
		Point p = player.getCenter();
		frame.setLocation((int)(p.getX()-640), (int)(p.getY()-360));
	}
	
	/*
	 *	Gets the X component of frame
	 *
	 *	@return X component of frame
	 */
	public int getX()
	{
		return (int)(frame.getX());
	}
	
	/*
	 *	Gets the Y component of frame
	 *
	 *	@return Y component of frame
	 */
	public int getY()
	{
		return (int)(frame.getY());
	}	
}
