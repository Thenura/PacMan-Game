
/**
 *This class creates a PacMan GUI that extends the JFrame class. It has a Board (JPanel) and 
 *Includes a constructor method that sets up the frame and adds a key listener to the board. 
 *@author Thenura Jayasinghe
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class PacManGUI extends JFrame {

	//Board 
	 static Board board = new Board();
	 
	//Score Board
	JPanel scoreBoard = new JPanel();

	//Score Board Label
	public static JLabel scoreLabel = new JLabel();
	
	//High Score Label
	public static JLabel highScoreLabel = new JLabel ();
	
	//Remaining Lives Label 
	public static JLabel livesLabel = new JLabel();
	
	//PacMan Fever Song
	static Clip pacManFever;
	
	/**
	 * PacMan GUI constructor
	 */
	public PacManGUI() {

		//1. Setup the GUI
		setLayout(null);
		setSize(615,720);
		setTitle("PacMan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Stops programs from piling up, stops program after exit

		//2. Set Bounds
		board.setBounds (0,80,600,600);
		scoreBoard.setBounds (0,0,600,80);		

		//3. Listen for events on the board and add the board and scoreBoard to the GUI
		addKeyListener(board);
		add(board);
		add(scoreBoard);

		//4. Make GUI visible
		setVisible(true);

		//5. Call ScoreBoard method
		scoreBoardPanelSetup();
		
		//6. Start PacMan Fever Song
		Audio.pacManFever();
		
	}
	
	/**
	 * This method represents the Score Board Panel
	 */
	private void scoreBoardPanelSetup() {

		//1. Setup ScoreBoard Panel
		scoreBoard.setLayout(null);
		scoreBoard.setBackground (new Color(40,26,11)); 
		
		//2. Setup Font
		Font subTitleFont = new Font ("Arial",Font.BOLD,28);
		scoreLabel.setFont(subTitleFont);
		highScoreLabel.setFont(subTitleFont);
		livesLabel.setFont(subTitleFont);
		
		//3. Change color 
		scoreLabel.setForeground(Color.WHITE);
		highScoreLabel.setForeground(Color.WHITE);
		livesLabel.setForeground(Color.WHITE);
		
		//3. Setup ScoreBoard, Lives, and Highscore Label
		scoreLabel.setBounds(0,10,250,40);
		highScoreLabel.setBounds(0, 45, 500, 40);
		livesLabel.setBounds(300,10,500,40);
						
		//4. Add ScoreBoard, Lives, and Highscore Label to Panel 
		scoreBoard.add(scoreLabel);
		scoreBoard.add(highScoreLabel);
		scoreBoard.add(livesLabel);

	}

}