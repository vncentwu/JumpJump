
import java.io.IOException;


public class Terrain extends Entity{

	/**
	 *Contructs a terrain
	 *
	 *@param x			Starting x component
	 *@param y			Starting x component
	 *@param weidth		Width of terrain
	 *@param height		Height of terrain
	 */
	public Terrain(int x, int y, int width, int height) throws IOException
	{
		super(x, y, width, height);
		
	}
	
	/**
	 *Contructs a terrain
	 *
	 *@param x			Starting x component
	 *@param y			Starting x component
	 *@param weidth		Width of terrain
	 *@param height		Height of terrain
	 *@param parent		map of the terrain
	 */
	public Terrain(int x, int y, int width, int height, Map parent) throws IOException
	{
		super(x, y, width, height, parent);
		
	}	
	
}
