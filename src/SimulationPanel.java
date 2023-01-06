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
import javax.swing.JPanel;
import javax.swing.Timer;

//This class is responsible for displaying the simulation. It is the panel that goes inside the window/JFrame
@SuppressWarnings("serial")
public class SimulationPanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600; //Screen width is 600 pixels
	static final int SCREEN_HEIGHT = 600; //Screen height is 600 pixels
	static final int UNIT_SIZE = 10; //Each "cell" in the simulation is 10 pixels. Screen is divided into 10x10 squares. Particles occupy one of these cells
	static final int DELAY = 75; //Used for timer. A frame update/tick occurs every 75ms
	boolean running = false; //Tracks if the simulation is running
	Timer timer; //Will hold an instance of the Timer class. Timer will repaint the panel every tick by firing of an ActionEvent at each interval
	int particleNumber = 100; //Number of particles simulated by the application
	LinkedList<Particle> particleList = new LinkedList<Particle>(); //Holds a list of particles that must be simulated
	
	//Constructor 
	SimulationPanel()
	{
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //Set the panel dimensions
		this.setBackground(Color.black); //Make the panel black
		this.setFocusable(true); //Panel is focusable so that it can accept keyboard inputs (unnecessary, focusable be default)
		this.addKeyListener(new MyKeyAdapter()); //Panel has key listeners allowing it to accept keyboard inputs
		
		startSimulation(); //Start the simulation
	}
	
	//Create particle instances and start the simulation
	public void startSimulation() 
	{
		//Create instances of particles and add them to particleList
		for (int i = 0; i < particleNumber; i++)
		{
			Particle particle = new Particle(UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT); //Instantiate the particle
			particleList.add(particle); //Add particle to the particle list
		}
		
		running = true; //Set variable running to true as the simulation is now starting
		timer = new Timer(DELAY, this); //Create a new instance of timer with a delay of 75 milliseconds between each tick
		timer.start(); //Start the timer
	}
	
	//Method fires every tick of the Timer. If simulation is running move each particle and repaint the screen.
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(running) 
		{
			for (int i = 0; i < particleNumber; i++)
			{
				particleList.get(i).randomMove(); //Move the particle/change particle coordinates
			}
		}
		repaint(); //Update the JPanel. Triggers paintComponent(Graphics g)
	}
	
	//Method overriding so that it calls the draw(g) method. Method is automatically called by Java Swing GUI each tick. 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); //Perform the normal painting operation
		draw(g); //Call the method for drawing the graphics (particles) of the simulation
	}

	//Used for drawing the graphics of the simulation each tick. Calls the draw(Graphics g) method inside each particle in order to paint the particle
	public void draw(Graphics g) 
	{
		//If running is true, draw each particle
		if (running) 
		{
			for (int i = 0; i < particleNumber; i++)
			{
				particleList.get(i).draw(g); //Call the draw(Graphics g) method inside a particle in order to paint it
			}
		}
		else //Otherwise run the simulationOver(g) method
		{
			simulationOver(g);
		}
		
	}
	
	//Method draws the screen for when the simulation is not running (running = false)
	public void simulationOver(Graphics g) 
	{
		//Simulation over text
		g.setColor(Color.blue); //Text colour is blue
		g.setFont( new Font("Times New Roman", Font.BOLD, 75)); //Bold text of size 75 and font = "Times New Roman"
		FontMetrics metrics2 = getFontMetrics(g.getFont()); //Data about the font that has been set above. Used below for correct positioning
		g.drawString("Simulation Over", (SCREEN_WIDTH - metrics2.stringWidth("Simulation Over"))/2, SCREEN_HEIGHT/2); //Place the "Simulation Over" text in the centre of the screen
	}
	
	//Inner class that has access to SimulationPanel variables and methods. It is responsible for reacting to key presses
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) 
		{
			
			switch(e.getKeyCode()) 
			{
			case KeyEvent.VK_ESCAPE: //Stop/start simulation if ESC key is pressed.
				if (running == true)
				{
					running = false;
				}
				else
				{
					running = true;
				}
				
				break;
			}
		}
		
	}

}
