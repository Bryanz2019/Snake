import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Write a description of class SnakePanel here.
 *
 * @author Bryan Zheng
 * @version 1.0
 */
public class SnakePanel extends JPanel
{
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;

    // initializing body this way makes things a lot less cluttered
    private ArrayList<SnakeComponent> body = new ArrayList<SnakeComponent>();
    private ArrayList<FoodComponent> foods = new ArrayList<FoodComponent>();
    private int xCoor = 4;
    private int yCoor = 4;
    private int size = 10;
    
    private Random r = new Random();
    
    public static JLabel score = new JLabel();
    public static int scores = 0;
    
    public static JLabel timer = new JLabel();
    public static String time;
    private int milisec = 0;
    private int sec = 0;
    private int min = 0;
    
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = true;

    private FoodComponent food;
    private SnakeComponent snake;
    private SnakeComponent tail;
    
    class TimerListener implements ActionListener
    {
    public void actionPerformed(ActionEvent event)
    {
        time = min + " : " + sec + " : " + milisec / 10;
        milisec += 100;
        if(milisec == 1000)
        {
            milisec = 0;
            sec++;
        }
        if(sec == 60)
        {
            sec = 0;
            min++;
        }
        timer.setText("Time: " + SnakePanel.time);
        
        if(body.size() == 0) {
            snake = new SnakeComponent(xCoor, yCoor);
            body.add(snake);
        }
        
        tail = new SnakeComponent(body.get(0).getXCoord(), body.get(0).getYCoord());
        
        if(foods.size() == 0) {
            int foodXCoor = r.nextInt(FRAME_WIDTH / size);
            int foodYCoor = r.nextInt(FRAME_HEIGHT / size);
            for(int i = 0; i < body.size(); i++)
            {
                if(foodXCoor == body.get(i).getXCoord() && foodYCoor == body.get(i).getYCoord())
                {
                    foodXCoor = r.nextInt(601);
                    foodYCoor = r.nextInt(601);
                }
            }
            food = new FoodComponent(foodXCoor, foodYCoor);
            foods.add(food);
        }
        
        for(int i = 0; i < body.size() - 1; i++)
        {
            body.set(i, body.get(i + 1));
        }
        body.set(body.size() - 1, getNextHead());
        
        if(body.get(body.size()-1).getXCoord() == foods.get(0).getXCoord() && body.get
            (body.size()-1).getYCoord() == foods.get(0).getYCoord()) {
            scores += 10;
            score.setText("Score:\n" + scores);
            foods.remove(0);
            body.add(0, tail);
        }
        
        if(body.get(body.size()-1).getXCoord() == FRAME_WIDTH / size || body.get(body.size()
            -1).getYCoord() == FRAME_WIDTH / size || body.get(body.size()-1).getXCoord()
            == -1 || body.get(body.size()-1).getYCoord() == -1)
        {
            ((Timer)event.getSource()).stop();
            JOptionPane.showMessageDialog(null ,"Game over");
            SnakeFrame.restart.setVisible(true);
        }
        
        for(int i = 0; i < body.size() - 1; i++)
        {
            if(body.get(body.size()-1).getXCoord() == body.get(i).getXCoord() && body.get
                (body.size()-1).getYCoord() == body.get(i).getYCoord())
            {
                if(body.size() >= 5)
                {
                    ((Timer)event.getSource()).stop();
                    JOptionPane.showMessageDialog(null ,"Game over");
                    SnakeFrame.restart.setVisible(true);
                }
            }
        }
        
        repaint();
    }
    }

    public SnakeComponent getNextHead() {
        // this gets the last item in the array list, aka the head
        SnakeComponent head = body.get(body.size() - 1);
        int newXCoor;
        int newYCoor;
        if(up == true) {
            newXCoor = head.getXCoord();
            newYCoor = head.getYCoord() - 1;
            head = new SnakeComponent(newXCoor, newYCoor);
        }
        if(down == true) {
            newXCoor = head.getXCoord();
            newYCoor = head.getYCoord() + 1;
            head = new SnakeComponent(newXCoor, newYCoor);
        }
        if(right == true) {
            // when you go right, the x coordinate changes. Remember that the top left is (0, 0), so going right increases the x coordinate by 1
            newXCoor = head.getXCoord() + 1;
            // when you go right, the y coordinate is not changed 
            newYCoor = head.getYCoord();
            head = new SnakeComponent(newXCoor, newYCoor);
        }
        if(left == true) {
            newXCoor = head.getXCoord() - 1;
            newYCoor = head.getYCoord();
            head = new SnakeComponent(newXCoor, newYCoor);
        }
        return head;
    }
    // This should be alone
    class MyKeyListener extends KeyAdapter
    {
    public void keyPressed(KeyEvent event)
    {
        if(event.getKeyCode() == KeyEvent.VK_UP && !down)
        {
            up = true;
            right = false;
            left = false;
        }
        if(event.getKeyCode() == KeyEvent.VK_DOWN && !up)
        {
            down = true;
            right = false;
            left = false;
        }
        if(event.getKeyCode() == KeyEvent.VK_LEFT && !right)
        {
            left = true;
            up = false;
            down = false;
        }
        if(event.getKeyCode() == KeyEvent.VK_RIGHT && !left)
        {
            right = true;
            up = false;
            down = false;
        }
    }
    }

    @Override
    public void paint(Graphics g)
    {
        //delete these when submitting
        super.paint(g);
        for(int i = 0; i < body.size(); i++)
        {
            body.get(i).paint(g);
        }
        for(int i = 0; i < foods.size(); i++)
        {
            foods.get(i).paint(g);
        }
    }

    public SnakePanel()
    {
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color. black);
        this.addKeyListener(new MyKeyListener());

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        setFocusable(true);
        requestFocus();

        ActionListener timer = new TimerListener();
        final int DELAY = 100;
        Timer t = new Timer(DELAY, timer);
        t.start();
    }
}
