import java.awt.Graphics;
import java.awt.Color;

/**
 * Write a description of class FoodComponent here.
 *
 * @author Bryan Zheng
 * @version 1.0
 */
public class FoodComponent extends PixelComponent
{
   public FoodComponent(int x, int y)
   {
        super(x, y);
   }
   
   public void paint(Graphics g)
   {      
        g.setColor(Color.GREEN);
        g.fillRect(getXCoord() * PixelComponent.SIZE, getYCoord() * PixelComponent.SIZE, 
            PixelComponent.SIZE, PixelComponent.SIZE);
   }
}
