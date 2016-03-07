package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import ie.gmit.sw.ai.traversers.*;
public class MazeRunner implements KeyListener{
	
	
	private static final int MAZE_WIDTH = 50;
	private Node[][] maze;
	private Node goal;
	private MazeView mv;
	
	public static void main(String[] args) {
		new MazeRunner();
	}
	
	public MazeRunner(){
		MazeGenerator generator = new MazeGenerator(MAZE_WIDTH, MAZE_WIDTH);
		//System.out.println(generator);
		maze = generator.getMaze();
		//Node goal = maze[maze.length / 2][maze.length / 2];
		//goal.setGoalNode(true);
		
		goal = generator.getGoalNode();
    	mv = new MazeView(maze, goal);
    	
    	Dimension d = new Dimension(MazeView.DEFAULT_VIEW_SIZE, MazeView.DEFAULT_VIEW_SIZE);
    	mv.setPreferredSize(d);
    	mv.setMinimumSize(d);
    	mv.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(mv);
        f.addKeyListener(this);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
        
        
        //Uninformed Searches
        //-------------------------------------------------------
        //Traversator t = new RandomWalk();
        //Traversator t = new BruteForceTraversator(true); //1.13
        //Traversator t = new RecursiveDFSTraversator();//1.13
        //Traversator t = new DepthLimitedDFSTraversator(maze.length / 2);
        //Traversator t = new IDDFSTraversator();
        
        //Heuristic Searches
        //-------------------------------------------------------       
        //Traversator t = new BasicHillClimbingTraversator(goal);     
        //Traversator t = new SteepestAscentHillClimbingTraversator(goal);      
        //Traversator t = new BestFirstTraversator(goal);
        Traversator t = new BeamTraversator(goal, 2);
 
        t.traverse(maze, maze[0][0], mv);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			System.out.println("click down key");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
