package ie.gmit.sw.ai;

import ie.gmit.sw.ai.Node.NodePassage;

import java.awt.*;

import javax.swing.*;
public class MazeView extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private Node[][] maze;
	
	public MazeView(Node[][] maze, Node goal) {
		this.maze = maze;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}
		
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        final int size = DEFAULT_VIEW_SIZE/maze.length;
        g2.drawRect(0, 0, MazeView.DEFAULT_VIEW_SIZE, MazeView.DEFAULT_VIEW_SIZE);
        
        for(int row = 0; row < maze.length; row++) {
        	for (int col = 0; col < maze[row].length; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		int x2 = (col + 1) * size;
        		int y2 = (row + 1) * size;
        		
        		
        		if (maze[row][col].isVisited() && !maze[row][col].isGoalNode()){
        			g2.setColor(maze[row][col].getColor());
        			g2.fillRect(x1, y1, size, size);
        		}
        		
       			if (maze[row][col].isGoalNode()){
       				g2.setColor(Color.GREEN);
       				g2.fillRect(x1, y1, size, size);
       			}

       			
        		g2.setColor(Color.RED);
        		g2.drawLine(x1, y1, x2, y1); //N
        		g2.drawLine(x1, y2, x2, y2); //S
        		g2.drawLine(x2, y1, x2, y2); //E
        		g2.drawLine(x1, y1, x1, y2); //W
        		
        		g2.setColor(maze[row][col].getColor());
        		if (maze[row][col].getPassage() == NodePassage.North){
        			g2.drawLine(x1 + 1, y1, x2 - 1, y1); //N
        		}else{
        			g2.drawLine(x1, y1 + 1, x1, y2 -1); //W
        		}
        	}
        }
	}
}