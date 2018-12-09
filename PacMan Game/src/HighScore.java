import java.io.*;
import java.util.*;

/**
 * This class represents the high score of the game and 
 * writing and saving to a text file
 * 
 * @author Thenura Jayasinghe
 */

public class HighScore {
	
	static int highscore;
	static String name;

	public static void writeFile() {

		try {
			
			//1.Opens Highscore TextFile
			FileWriter fw = new FileWriter("highscore.txt");
			
			//2. Writes values given to the Text File
			PrintWriter pw = new PrintWriter(fw);
			
			//3. Highscore and Name
			pw.println(Board.score);
			pw.println(Board.name);
			
			pw.close();

		} catch (IOException e) {
			
			System.out.println("Error!");
			
		}
	}
	
	public static int getHighscore() {

		return highscore;
	}
	
	public static String getname() {

		return name;
	}
	
	//Used to show highscore on the Label 
	public static void highscoreInput() {

		try {
			
			//Opens High Score File
			Scanner input = new Scanner(new File("highscore.txt"));
			
			//Assigns highscore variable with value from text file
			highscore = input.nextInt();
			name = input.next();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}

}
