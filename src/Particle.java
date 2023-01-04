import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Particle {
	
	final int UNIT_SIZE; //Size of the cells that the screen is divided into. Effectively the size of the particle.
	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;
	int x; //Holds x coordinates of a particle.
	int y; //Holds y coordinates of a particle.
	String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"}; //Array of possible directions.
	String direction; //Direction that the particle is currently heading in.
	static Random random = new Random(); //Hold an instance of the Random class.
	private Color colour = new Color(random.nextInt(0xFFFFFF)); //A random colour for the particle.

	//Constructor
	Particle(int UNIT_SIZE, int SCREEN_WIDTH, int SCREEN_HEIGHT){
		
		this.SCREEN_WIDTH = SCREEN_WIDTH;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
		this.UNIT_SIZE = UNIT_SIZE;
		this.x = SCREEN_WIDTH/2;
		this.y= SCREEN_HEIGHT/2;
		direction = directions[random.nextInt(8)]; //Select a random direction for the particle.
		
	}
	
	//Move particle randomly
	public void randomMove()
	{
		switch(direction) {
		case "N":
			direction = chooseDirection("NE", "N", "NW");
			move();
			break;
		case "S":
			direction = chooseDirection("SE", "S", "SW");
			move();
			break;
		case "W":
			direction = chooseDirection("NW", "W", "SW");
			move();
			break;
		case "E":
			direction = chooseDirection("NE", "E", "SE");
			move();
			break;
		case "SE":
			direction = chooseDirection("S", "SE", "E");
			move();
			break;
		case "SW":
			direction = chooseDirection("S", "SW", "W");
			move();
			break;
		case "NE":
			direction = chooseDirection("N", "NE", "E");
			move();
			break;
		case "NW":
			direction = chooseDirection("N", "NW", "W");
			move();
			break;
		}
	}
	
	//Used for moving the particle.
	public void move() {
		
		
		switch(direction) {
		case "N":
			y = y - UNIT_SIZE;
			break;
		case "S":
			y = y + UNIT_SIZE;
			break;
		case "W":
			x = x - UNIT_SIZE;
			break;
		case "E":
			x = x + UNIT_SIZE;
			break;
		case "SE":
			x = x + UNIT_SIZE;
			y = y + UNIT_SIZE;
			break;
		case "SW":
			x = x - UNIT_SIZE;
			y = y + UNIT_SIZE;
			break;
		case "NE":
			x = x + UNIT_SIZE;
			y = y - UNIT_SIZE;
			break;
		case "NW":
			x = x - UNIT_SIZE;
			y = y - UNIT_SIZE;
			break;
		}
	}
	
public String chooseDirection(String direction1, String direction2, String direction3) {
		
		String[] directions = {direction1, direction2, direction3};
		String newDirection;
		
		newDirection = directions[random.nextInt(3)];
		
		return newDirection;
	}
	
	public void checkCollisions() {
		
		
		//Check if head touches left border
		if(x <= 0)
		{
			//running = false;
			direction = chooseDirection("NE", "E", "SE");
		}
		
		//Check if head touches right border
		if(x >= SCREEN_WIDTH - UNIT_SIZE)
		{
			//running = false;
			direction = chooseDirection("NW", "W", "SW");
		}
		
		//Check if head touches top border
		if(y <= 0)
		{
			//running = false;
			direction = chooseDirection("SW", "S", "SE");
		}
		
		//Check if head touches bottom border
		if(y >= SCREEN_HEIGHT - UNIT_SIZE)
		{
			//running = false;
			direction = chooseDirection("NW", "N", "NE");
		}
		
		//if(!running)
		//{
		//	timer.stop();
		//}
	}
	
	//Used for drawing the graphics of the game each tick.
	public void draw(Graphics g) {
		
			g.setColor(colour);
			g.fillOval(x, y, UNIT_SIZE, UNIT_SIZE);
		
	}
			
	//Setters and getters
	

}
