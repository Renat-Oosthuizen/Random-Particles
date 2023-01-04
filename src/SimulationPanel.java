import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationPanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 10; //Size of each cell in the game.
	static final int DELAY = 75; //Used for timer.
	boolean running = false;
	Timer timer; //Will hold an instance of the Timer class. Timer will repaint the panel every tick.
	int particleNumber = 100;
	LinkedList<Particle> particleList = new LinkedList<Particle>();
	Scanner in = new Scanner(System.in);
	
	SimulationPanel(){
		//random = new Random(); //Create an instance of the Random class.
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		startGame();
	}
	
	public void startGame() {
		
		//System.out.println("How many particles do you wish to simulate?");
		//particleNumber = in.nextInt();
		
		//Create instances of particle and add them to particleList.
		for (int i = 0; i < particleNumber; i++)
		{
			Particle particle = new Particle(UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
			particleList.add(particle);
		}
		
		running = true; //Set variable running to true as the game is now starting;
		timer = new Timer(DELAY, this); //Create a new instance of timer with a delay of 75 milliseconds between each tick. 
		timer.start(); //Start the timer.
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		draw(g);
	}

	//Used for drawing the graphics of the game each tick.
	public void draw(Graphics g) {
		
		if (running) 
		{
			for (int i = 0; i < particleNumber; i++)
			{
				particleList.get(i).draw(g);
			}
		}
		else
		{
			simulationOver(g);
		}
		
	}
	

	
	
	
	public void simulationOver(Graphics g) {
		
		//Simulation over text
		g.setColor(Color.blue);
		g.setFont( new Font("Times New Roman", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Simulation Over", (SCREEN_WIDTH - metrics2.stringWidth("Simulation Over"))/2, SCREEN_HEIGHT/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) 
		{
			for (int i = 0; i < particleNumber; i++)
			{
				particleList.get(i).randomMove();
				particleList.get(i).checkCollisions();
			}
		}
		repaint();

		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) 
			{
			/*case KeyEvent.VK_LEFT: //Go left if left key is pressed.
				direction = "W";
				break;
			case KeyEvent.VK_RIGHT: //Go right if right key is pressed.
				direction = "E";
				break;
			case KeyEvent.VK_UP: //Go up if up key is pressed.
				direction = "N";
				break;
			case KeyEvent.VK_DOWN: //Go down if down key is pressed.
				direction = "S";
				break;*/
			case KeyEvent.VK_ESCAPE: //Stop simulation if ESC key is pressed.
				running = false;
				break;
			}
		}
		
	}

}
