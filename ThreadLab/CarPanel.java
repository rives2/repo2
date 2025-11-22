package ThreadLab;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws one car.
*/
public class CarPanel extends JComponent
{  
    private Car car1;
    private int x, y, delay;
    private CarQueue carQueue;
    
    CarPanel(int x1, int y1, int d, CarQueue queue)
    {
        delay = d;
        x = x1;
        y = y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
    }

    public void startAnimation()
    {
        class AnimationRunnable implements Runnable
        {
        	boolean run = true;
            public void run()
            {
                try
                {
                    while (run)
                    {
                        int direction;

                        try {
                            direction = carQueue.deleteQueue(); 
                        } 
                        catch (Exception e) {
                            continue; 
                        }

                        if(direction == 0)
                        {
                        	y-= 5;
                        }
                        else if (direction == 1)
                        {
                        	y+= 5;
                        }
                        else if (direction == 2)
                        {
                        	x += 5;
                        }
                        else
                        {
                        	x -= 5;
                        }

                 
                        if (x > getWidth() - 50) {
                            x = getWidth() - 50;
                            carQueue.changeDirection(3);
                        }

                        if (x < 0) {
                            x = 0;
                            carQueue.changeDirection(2);
                        }

                        if (y > getHeight() - 35) {
                            y = getHeight() - 35;
                            carQueue.changeDirection(0);
                        }

                        if (y < 0) {
                            y = 0;
                            carQueue.changeDirection(1);
                        }

                        repaint();
                        Thread.sleep(delay * 1000);
                    }
                }
                catch (InterruptedException exception)
                {
                }
            }
        }

        Runnable r = new AnimationRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    public void paintComponent(Graphics g)
    {  
        Graphics2D g2 = (Graphics2D) g;
        car1.draw(g2, x, y);
    }
}
