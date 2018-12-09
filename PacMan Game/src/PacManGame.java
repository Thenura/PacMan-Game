import javax.swing.*;

/**
 *This class is used to create a new PacManGUI that will start a PacMan game
 *@author Thenura Jayasinghe
 *
 *Score Board 
 *High Score (Pop-Up Box && Displays Highscore with Name)
 *A.I for Ghosts 
 *Speed Increase When Little Pellets Left (60,40,20)
 * PacMan Fever Song
 *Get out of House (All Directions)
 *Splash Screen (Intro Screen)
 *Cherries (Bonus Points +100)
 * Music for Food, Death, Cherries, Powerpellet
 * Blue Mode (Bonus Points +150 eating Ghost)
 * Powerpellets (Bonus Points +10)
 * Theme (Changed Pic --> Blocks, Ghost)
 * Lives (Respawn Different Area)
 *
 */

public class PacManGame{
	/**
	 * Main method to run program and create a new GUI
	 * @param args not used
	 */

	//Lives for PacMan  (Changes)
	public static int pacLives = 3;
	
	//Status of PacMan
	public static boolean death = false;
	
	public static void main(String[] args){

		//1.Spash Screen 
		//1.1 New Window
		JWindow window = new JWindow();

		//2. Add JLabel (GIF) to JWindow
		window.getContentPane().add(new JLabel("", new ImageIcon("images/pacSplash.gif"),SwingConstants.CENTER));
		window.setBounds(0,0,1267,710);

		//3. Make JWindow Visible 
		window.setVisible(true);

		//4 Assign how long image will stay on for (6 seconds)
		try {
			Thread.sleep(6000);
		}catch(InterruptedException e){}

		//5. Close Window
		window.dispose();

		//6. Create a new PacMan GUI 
		new PacManGUI();	

		//7.Calls highscoreinput method to show the existing highscore
		HighScore.highscoreInput();

	}
}