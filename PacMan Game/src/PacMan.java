import javax.swing.ImageIcon;

/**
 * This class is used to create a PacMan object. 
 * It extends the Mover class and includes an array of ImageIcons constants for the various states of PacMan.
 *  Also, there is a constructor that sets the initial image of PacMan
 * 
 * @author Thenura Jayasinghe
 *
 */
public class PacMan extends Mover {
	
	/**
	 * Creates an array of ImageIcons representing various states of PacMan
	 */
	public static final ImageIcon[][] IMAGE = {
		
		{new ImageIcon("images/PacLeftClosed.bmp"), new ImageIcon("images/PacLeftOpen.bmp") },
		{new ImageIcon("images/PacUpClosed.bmp"), new ImageIcon("images/PacUpOpen.bmp") },
		{new ImageIcon("images/PacRightClosed.bmp"), new ImageIcon("images/PacRightOpen.bmp") },
		{new ImageIcon("images/PacDownClosed.bmp"), new ImageIcon("images/PacDownOpen.bmp") },
	};
	
	/**
	 * PacMan constructor
	 */
	public PacMan(){
		
		this.setIcon(IMAGE[0][0]); //Set PacMan to left losed when game starts 
	}
	
}
