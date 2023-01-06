import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Particle {
	
	final int UNIT_SIZE; //Size of the cells that the screen is divided into. Effectively the size of the particle.
	final int SCREEN_WIDTH; //Width of the screen
	final int SCREEN_HEIGHT; //Height of the screen
	int x; //Holds x coordinates of a particle.
	int y; //Holds y coordinates of a particle.
	String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"}; //Array of possible directions.
	String direction; //Direction that the particle is currently heading in.
	static Random random = new Random(); //Hold an instance of the Random class.
	private Color colour = new Color(random.nextInt(0xFFFFFF)); //Colour of the particle (random)

	//Constructor
	Particle(int UNIT_SIZE, int SCREEN_WIDTH, int SCREEN_HEIGHT)
	{
		this.SCREEN_WIDTH = SCREEN_WIDTH;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
		this.UNIT_SIZE = UNIT_SIZE;
		this.x = SCREEN_WIDTH/2; //Place the particle at the centre of the screen 
		this.y= SCREEN_HEIGHT/2; //Place the particle at the centre of the screen
		direction = directions[random.nextInt(8)]; //Select a random direction for the particle.
	}
	
	//Select a direction for the particle to move and then call the move method to move the particle in that direction.
	//Move particle randomly within a 90-degree arc of current direction. This means that particles will make progress on the screen rather than generally staying in the same area
	public void randomMove()
	{
		switch(direction) 
		{
		case "N":
			direction = chooseDirection("NE", "N", "NW"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "S":
			direction = chooseDirection("SE", "S", "SW"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "W":
			direction = chooseDirection("NW", "W", "SW"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "E":
			direction = chooseDirection("NE", "E", "SE"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "SE":
			direction = chooseDirection("S", "SE", "E"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "SW":
			direction = chooseDirection("S", "SW", "W"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "NE":
			direction = chooseDirection("N", "NE", "E"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		case "NW":
			direction = chooseDirection("N", "NW", "W"); //Select direction
			checkCollisions(); //If at border then select direction that avoids exiting screen bounds
			move(); //Move
			break;
		}
	}
	
	//Move the particle based on it's currently selected movement direction
	public void move() 
	{
		switch(direction) 
		{
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
	
	//Randomly pick a direction from 1 of 3 directions provided
	public String chooseDirection(String direction1, String direction2, String direction3) 
	{
		String[] directions = {direction1, direction2, direction3};
		String newDirection;
		
		newDirection = directions[random.nextInt(3)];
		
		return newDirection;
	}
	
	//Change the current direction of the particle if it is in contact with a border
	public void checkCollisions() 
	{
		//Check if a particle is touching an individual border
		if(x == 0) //Check if particle touches left border
		{
			direction = chooseDirection("NE", "E", "SE"); //Give it a right-heading direction
		}
		else if(x == (SCREEN_WIDTH - UNIT_SIZE)) //Check if particle touches right border
		{
			direction = chooseDirection("NW", "W", "SW"); //Give it a left-heading direction
		}
		else if(y == 0) //Check if particle touches top border
		{
			direction = chooseDirection("SW", "S", "SE"); //Give it a bottom-heading direction
		}
		else if(y == (SCREEN_HEIGHT - UNIT_SIZE)) //Check if particle touches bottom border
		{
			direction = chooseDirection("NW", "N", "NE"); //Give it an upward-heading direction
		}
		
		//Check if particle is in a corner
		if(x == 0 && y == 0) //Check if particle touches top left corner
		{
			direction = "SE"; //Give it a South-East direction
		}
		else if(x == (SCREEN_WIDTH - UNIT_SIZE) && y == 0) //Check if particle touches top right corner
		{
			direction = "SW"; //Give it a South-West direction
		}
		else if(x == 0 && y == (SCREEN_HEIGHT - UNIT_SIZE)) //Check if particle touches bottom left corner
		{
			direction = "NE"; //Give it a North-East direction
		}
		else if(x == (SCREEN_WIDTH - UNIT_SIZE) && y == (SCREEN_HEIGHT - UNIT_SIZE)) //Check if particle touches bottom right corner
		{
			direction = "NW"; //Give it a North-West direction
		}

	}
	
	//Used for drawing the graphics/particles of the simulation each tick.
	public void draw(Graphics g) 
	{
			g.setColor(colour); //Set the colour of the Graphics class that will be used to draw the particle
			g.fillOval(x, y, UNIT_SIZE, UNIT_SIZE); //Draw the particle (an oval at the provided x and y coordinates (upper left corner) with provided width and height)
	}

}
