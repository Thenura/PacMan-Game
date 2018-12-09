import java.io.*;
import javax.sound.sampled.*;

/**
 *This class is used to create a various sound effects in the PacMan Game
 *@author Thenura Jayasinghe
 */

public class Audio {

	//music clips
	static Clip pacManChomp;
	static Clip pacKilled;
	static Clip fruitEat;
	static Clip pacManFever;

	/**
	 * Loads the fruit eaten sound
	 */
	public static void fruitEatSound() {
		
		//1. Fruit Audio Clip 
		try {

			//1.1. Define location of Fruit Audio Clip
			File eatCherryURL = new File("sounds/fruiteat.wav");

			//1.2 . Audio Interface
			fruitEat = AudioSystem.getClip();
			
			AudioInputStream fruitEatClip = AudioSystem.getAudioInputStream( eatCherryURL );

			//1.3.  Open and Start Fruit Audio Clip
			fruitEat.open(fruitEatClip);
			fruitEat.start();


		} catch(Exception ex) {
			
			//1.4. File Not Found Error
			System.out.println("Error! fruiteat.wav not found.");
			
		}
	}
	
	/**
	 * Loads the death sound
	 */
	public static void deadSound() {
		
		//2. Death Audio Clip
		try {

			//2.1. Define location of Death Audio Clip
			File pacDeadURL = new File("sounds/killed.wav");

			//2.2 . Audio Interface
			pacKilled = AudioSystem.getClip();

			AudioInputStream pacKilledClip = AudioSystem.getAudioInputStream( pacDeadURL );

			//2.3.  Open and Start Death Audio Clip
			pacKilled.open(pacKilledClip);
			pacKilled.start();


		} catch(Exception ex) {
			
			//2.4. File Not Found Error
			System.out.println("Error! killed.wav not found.");

		}

	}
	
	/**
	 * Loads the chomping sound
	 */
	public static void pacManChomp() {
		
		//3. Chomp Audio Clip
		try {

			//3.1. Define location of Chomp Audio Clip
			File pacChompUrl = new File("sounds/pacchomp.wav");

			//3.2 . Audio Interface
			pacManChomp = AudioSystem.getClip();

			AudioInputStream pacChompClip = AudioSystem.getAudioInputStream( pacChompUrl );
			
			//3.3.  Open and Start Chomp Audio Clip
			pacManChomp.open(pacChompClip);
			pacManChomp.start();


		} catch(Exception ex) {
			
			//3.4. File Not Found Error
			System.out.println("pacchomp.wav not found.");

		}
	}
	
	/**
	 * Loads the PacMan Fever song
	 */
	public static void pacManFever(){
		
		try {

			//4.1. Define location of Chomp Audio Clip
			File pacManFeverURL = new File("sounds/Pac-Man_Fever_New_.wav");

			//4.2 . Audio Interface
			pacManFever = AudioSystem.getClip();

			AudioInputStream musicClip = AudioSystem.getAudioInputStream( pacManFeverURL );

			//4.3.  Open, Start and Loop PacMan Fever Audio Clip
			pacManFever.open(musicClip);
			pacManFever.start();
			pacManFever.loop(25); //Allows song to repeat 

		} catch(Exception ex) {

			System.out.println("Pac-Man_Fever_New_. wav not found");

		}
	}
}
