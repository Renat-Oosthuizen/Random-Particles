import javax.swing.JFrame;

//This class is responsible for creating a window inside of which the simulation is displayed.
@SuppressWarnings("serial")
public class SimulationFrame extends JFrame{
	
	SimulationFrame() 
	{
		this.add(new SimulationPanel()); //Add the JPanel SimulationPanel which displays the simulation
		this.setTitle("Random Particles Simulation"); //Title of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit the application when the window is closed
		this.setResizable(false); //Window is not resizable. Simulation relies on JPanel dimensions being divisible by particle size
		this.pack(); //JFrame will encompass all the components that are added to the frame.
		this.setVisible(true); //Make the window visible
		this.setLocationRelativeTo(null); //Place JFrame in the centre of the screen
	}

}
