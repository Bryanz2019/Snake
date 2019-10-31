import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * Write a description of class MenuPanel here.
 *
 * @author Bryan Zheng
 * @version 1.0
 */
public class MenuPanel extends JPanel
{
    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 600;
    
    ImageIcon theGame = new ImageIcon("game.jpg");
    ImageIcon image = new ImageIcon("snake.jpg");

    public MenuPanel()
    {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);
        
        JLabel img = new JLabel("", image, JLabel.CENTER);
        add(img, BorderLayout.NORTH);
        
        SnakePanel.timer.setText("Time: " + SnakePanel.time);
        add(SnakePanel.timer, BorderLayout.WEST);
        
        SnakePanel.scores = 0;
        SnakePanel.score.setText("Score: " + SnakePanel.scores);
        add(SnakePanel.score, BorderLayout.EAST);
        
        JLabel img2 = new JLabel("", theGame, JLabel.CENTER);
        add(img2);
    }
}