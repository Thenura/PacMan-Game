import javax.swing.ImageIcon;

@SuppressWarnings("serial")

/**
 * This class is used to create Ghost objects.
 * It includes a constant ImageIcon array to hold the various ghost pictures
 * and a constructor method that sets the Ghosts' images
 *
 * @author Thenura Jayasinghe
 */
public class Ghost extends Mover{
	
	/**
	 * creates an array of ImageIcons representing various Ghosts
	 */
	public static final ImageIcon[] IMAGE = {
			
			new ImageIcon("images/Ghost1.png"),
			new ImageIcon("images/Ghost2.png"),
			new ImageIcon("images/Ghost3.png"),
			
			new ImageIcon("images/BlueGhost1.png"),
			new ImageIcon("images/BlueGhost2.png"),
			new ImageIcon("images/BlueGhost3.png")

	};

	/**
	 * Ghost constructor
	 *
	 * @param gNum ghost number - 0, 1 or 2
	 */
	public Ghost(int gNum) {

		this.setIcon(IMAGE[gNum]);
		
	}

}