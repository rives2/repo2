package ThreadLab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {

    private Queue<Integer> queue;
    private Random rand;

    public CarQueue() {
        queue = new LinkedList<>();
        rand = new Random();
        
        queue.add(0);
        queue.add(1);
        queue.add(3);
        queue.add(2);
        queue.add(3);
        queue.add(0);
    }

    public void addToQueue() {
        class Direction implements Runnable {
        	boolean run = true;
        	
            public void run() {
                try {
                    while (run) {
                    	synchronized(queue) {
                        queue.add(rand.nextInt(4));
                    	}
                        Thread.sleep(500); 
                    }
                } catch (InterruptedException e) {
                   
                }
            }
        }

        Runnable r = new Direction();
        Thread t = new Thread(r);
        t.start();
    }
    
    public int deleteQueue() {
    	synchronized(queue) {
    		if (queue.isEmpty()) {
                return rand.nextInt(4);
    	}
            return queue.remove();
    	}
        }
    
    public void changeDirection(int d) {
    	synchronized(queue){
        queue.add(d);
    	}
    }
}
