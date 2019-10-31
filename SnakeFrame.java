import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class SnakeFrame here.
 *
 * @author Bryan Zheng
 * @version 1.0
 */
public class SnakeFrame extends JFrame
{
    public static JButton restart = new JButton("Retry?");
    
    public SnakeFrame()
    {
      setTitle("Snake Game");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      
      JPanel main = new JPanel();
      SnakePanel s = new SnakePanel();
      MenuPanel m = new MenuPanel();
      
      restart.setVisible(false);
      s.add(restart);
      restart.addActionListener( new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              dispose();
              new SnakeFrame();
            }
        });
      
      main.add(s, BorderLayout.WEST);
      main.add(m, BorderLayout.EAST);
      add(main);
      
      pack();
      //setVisible should always be last
      setVisible(true);
    }
    
    public static void main(String[] args)
    {
      new SnakeFrame();
    }
}
