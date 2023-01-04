import javax.swing.JFrame;

public class SimulationFrame extends JFrame{
	
	SimulationFrame() {
		
		this.add(new SimulationPanel());
		this.setTitle("Random Particles Simulation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack(); //JFrame will encompass all the components that are added to the frame.
		this.setVisible(true);
		this.setLocationRelativeTo(null); //Place JFrame in the centre of the screen.
		
	}

}
