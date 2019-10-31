import java.awt.Graphics;
import java.awt.Color;

/**
 * Write a description of class PixelComponent here.
 *
 * @author Bryan Zheng
 * @version 1.0
 */
public class PixelComponent
{
    private int xCoor, yCoor;
    public static int SIZE = 10;
    
    public PixelComponent(int x, int y)
    {
        xCoor = x;
        yCoor = y;
    }
    
    public int getXCoord() {
        return xCoor;
    }
    
    public int getYCoord()  {
        return yCoor;
    }
    
    public void paint(Graphics g)
    {      
        g.setColor(Color.RED);
        g.fillRect(xCoor * PixelComponent.SIZE, yCoor * PixelComponent.SIZE, 
            PixelComponent.SIZE, PixelComponent.SIZE);
    }
}
