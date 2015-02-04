
import java.io.IOException;


public class Platform extends Terrain{


	/*
	 *	Constructs a platform
	 *
	 *	@param x			starting x component
	 *	@param y			starting y component
	 *	@param width		width of platform
	 *	@param height		height of platform
	 *	@param dad			map of the platform
	 */
	public Platform(int x, int y, int width, int height, Map dad) throws IOException
	{
		super(x, y, width, height, dad);
	}
}
