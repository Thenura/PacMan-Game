import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.sound.sampled.Clip;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * This class represents the game board and includes methods to
 * handle keyboard events and game actions
 * 
 * @author Thenura Jayasinghe
 */
public class Board extends JPanel implements KeyListener, ActionListener {

	//Pellets Eaten
	int pelletsEaten = 0;

	/**
	 * Timer for Pac Man movement
	 */
	private Timer pacManTimer = new Timer(250, this); 

	/**
	 * Timer for Ghost movement
	 */
	private Timer ghostTimer = new Timer(310, this);

	/**
	 * Timer for PacMan animation
	 */
	private Timer animateTimer = new Timer(25, this);

	/**
	 * Timer for "Blue Mode"
	 */
	private Timer blueModeTimer = new Timer(10000, this);

	/**
	 * ImageIcon constant for wall
	 */
	private static final ImageIcon WALL = new ImageIcon("images/Grass.png");

	/**
	 * ImageIcon constant for food
	 */
	private static final ImageIcon FOOD = new ImageIcon("images/pacFood.png");

	/**
	 * ImageIcon constant for blank
	 */
	private static final ImageIcon BLANK = new ImageIcon("images/Black.bmp");

	/**
	 * ImageIcon constant for door
	 */
	private static final ImageIcon DOOR = new ImageIcon("images/Black.bmp");

	/**
	 * ImageIcon constant for skull
	 */
	private static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");

	/**
	 * ImageIcon constant for powerpellet
	 */
	private static final ImageIcon POWERPELLET = new ImageIcon("images/Powerpellet.png");

	/**
	 * ImageIcon constant for cherry 
	 */
	private static final ImageIcon CHERRY = new ImageIcon("images/Cherry.bmp");

	/**
	 * Array to hold the game board characters from the text file
	 */
	private char[][] maze = new char[25][27];

	/**
	 * Array to hold the game board images
	 */
	private JLabel[][] cell = new JLabel[25][27];

	/**
	 * PacMan object
	 */
	private PacMan pacMan;

	/**
	 * Array of Ghost objects
	 */
	private Ghost[] ghost = new Ghost[3];

	/**
	 * track amount of food on board
	 */
	private int pellets=0;

	/**
	 * track game score (1pt per food item eaten)
	 */
	public static int score=0;

	/**
	 * track players name
	 */
	public static String name;

	/**
	 * track bluemode on or off
	 */
	public int bluemode = 1;
	/**
	 * steps for animating Pacman's chomp
	 */
	private int pStep;

	/**
	 * Construct the game board including the layout, background, PacMan and ghosts
	 * and calls the loadBoard method
	 */
	public Board() {

		//1. Set the layout (grid), background (black)
		setLayout (new GridLayout(25,27));
		setBackground (new Color(40,26,11)); 

		//2. Create PacMan and the ghosts
		pacMan = new PacMan();

		ghost[0] = new Ghost(0);
		ghost[1] = new Ghost(1);
		ghost[2] = new Ghost(2);

		//3. Load the maze
		loadBoard();

	}

	/**
	 * Loads the maze onto the screen from a text file
	 */
	private void loadBoard() {

		//keeps track of the row number
		int r = 0; 

		//1. Open the maze text file for input
		Scanner input = null;

		try {
			//Check the level and corresponding file to input

			input = new Scanner (new File ("maze.txt"));

			//1.1. Cycle through all the rows in the maze file reading one row at a time
			while (input.hasNext()) {

				//1.1.1. Read the next line from the maze file
				maze[r] = input.nextLine().toCharArray();

				//1.1.2. For each row cycle through all the columns
				for (int c = 0; c < maze[r].length; c++) {

					// create a new picture for each cell
					cell[r][c] = new  JLabel(); 

					// Depending on the symbol in the maze file assign the
					// appropriate picture *Note: This only has to be done
					// for squares that are not black since the background
					// is already black

					//1.1.2.1. If the symbol is a wall then assign a wall picture to the current square on the screen
					if (maze[r][c] == 'W')
						cell[r][c].setIcon(WALL);

					//1.1.2.2. Otherwise if the symbol is a food item then assign a food picture to the current square on the screen and keep track of the number of items
					// keep track of the amount of food
					else if (maze[r][c] == 'F') {
						cell[r][c].setIcon(FOOD);
						pellets++; 

					}

					//1.1.2.3. Otherwise if the symbol is PacMan then assign the closed left image and set PacMan's row, column, and direction (left)
					else if (maze[r][c] == 'P') {
						cell[r][c].setIcon(pacMan.getIcon());
						pacMan.setRow(r);
						pacMan.setColumn(c);
						pacMan.setDirection(0);

					}

					//1.1.2.4. Otherwise if the symbol is a Ghost then assign the
					// appropriate ghost image and set the ghost's row, column
					// use ASCII conversion
					else if (maze[r][c]=='0'||maze[r][c]=='1'||maze[r][c]=='2') {
						int gNum = Character.getNumericValue(maze[r][c]);
						//int gNum = (int)(maze[r][c])-48; //or use ASCII conversion

						cell[r][c].setIcon(ghost[gNum].getIcon());						
						ghost[gNum].setRow(r);
						ghost[gNum].setColumn(c);

					}

					//1.1.2.5. Otherwise if the symbol is a door then assign a door
					// picture to the current square on the screen
					else if (maze[r][c]=='D') {
						cell[r][c].setIcon(DOOR);

					}
					//1.1.2.6 Otherwise if the symbol is a U, then assign a blank space
					else if (maze[r][c]=='U'){
						cell [r][c].setIcon(BLANK);
					}

					//1.1.2.7 Otherwise if the symbol is a L, then assign a blank space
					else if (maze[r][c]=='L'){
						cell [r][c].setIcon(BLANK);
					}

					//1.1.2.8 Otherwise if the symbol is a R, then assign a blank space
					else if (maze[r][c]=='R'){
						cell [r][c].setIcon(BLANK);
					}

					//1.1.2.9 Otherwise if the symbol is a R, then assign a power pellet
					else if (maze[r][c]=='T'){
						cell [r][c].setIcon(POWERPELLET);
					}

					//1.1.2.10 Otherwise if the symbol is a C, then assign a Cherry
					else if (maze[r][c]=='C'){
						cell [r][c].setIcon(CHERRY);
					}

					//1.1.2.11 Add the current cell to the board panel
					add(cell[r][c]);

				}

				//1.1.3. Increment the row
				r++;

			}

			//3. Close the maze text file
			input.close();

		} catch (FileNotFoundException e) {

			System.out.println("File not found");

		} 
	}

	/**
	 * Handles keyboard entries - to start the game and control PacMan's movements
	 */
	public void keyPressed(KeyEvent key) {

		//1. If the game isn't running and pacMan is alive then
		// start the game timer
		if (pacManTimer.isRunning()==false && pacManTimer.isRunning()==false && pacMan.isDead()==false){
			pacManTimer.start();
			ghostTimer.start();
		}

		//2. If PacMan is still alive and the game is not over then
		if (pacMan.isDead()==false && score!=pellets) {

			//2.1 Track direction based on the key pressed
			// - 37 since ASCII codes for cursor keys start
			// at 37:
			int direction = key.getKeyCode()-37;

			//2.2. Change direction of PacMan;
			// 37-left,38-up,39-right,40-down
			if (direction==0 && maze[pacMan.getRow()][pacMan.getColumn()-1] != 'W')
				pacMan.setDirection(0);

			else if (direction==1 && maze[pacMan.getRow()-1][pacMan.getColumn()] != 'W')
				pacMan.setDirection(1);

			else if (direction==2 && maze[pacMan.getRow()][pacMan.getColumn()+1] != 'W')
				pacMan.setDirection(2);

			else if (direction==3 && maze[pacMan.getRow()+1][pacMan.getColumn()] != 'W')
				pacMan.setDirection(3);
		}
	}

	/**
	 * Allows an object to move and updates both on the maze and screen based on:
	 * the object, direction, and change in row and column
	 *
	 * @param mover either PacMan or a Ghost
	 */
	private void performMove(Mover mover) {

		//1. If a mover is at a door then teleport to other side
		if (mover.getColumn()==1) {
			mover.setColumn(24);
			cell[12][1].setIcon(DOOR);

		} else if (mover.getColumn()==25) {
			mover.setColumn(2);
			cell[12][25].setIcon(DOOR);

		}

		//2. If there is no wall in the direction that
		// the Mover object wants to go then
		if (maze[mover.getNextRow()][mover.getNextColumn()] != 'W') {
			//2.1. If the Mover object is PacMan then animate a 'chomp'
			if (mover==pacMan)
				animateTimer.start();

			//2.2 Otherwise the Mover is a ghost
			else {
				//2.2.1 If the cell where the Ghost is has food then reset the food
				if (maze[mover.getRow()][mover.getColumn()]=='F')
					cell[mover.getRow()][mover.getColumn()].setIcon(FOOD);

				//2.2.2 Otherwise reset the cell to blank
				else
					cell[mover.getRow()][mover.getColumn()].setIcon(BLANK);

				//2.2.3 Move the ghost's position
				mover.move();

				//2.2.4 If a collision has occurred then death occurs
				if (collided()) {

					//2.2.4.1 If Blue Mode is not on and death occurs
					if (bluemode == 1){
						//Change death to true
						pacMan.setDead(true);
						PacManGame.death = true;

						//Call death and checkdeath methods
						death();
						checkDeath();
					}

					//2.2.4.2 If Blue mode is on and collision occurs (PacMan Eats)
					else if (bluemode ==2){
						score += 150;
						PacManGUI.scoreLabel.setText("Score : " + String.valueOf(score)); 

						//If Ghost 0 collides with PacMan
						if (ghost[0].getColumn() == pacMan.getColumn() && ghost[0].getRow() == pacMan.getRow()){
							ghost[0].setRow(12);
							ghost[0].setColumn(12);
						}

						//If Ghost 1 collides with PacMan
						else if (ghost[1].getColumn() == pacMan.getColumn() && ghost[1].getRow() == pacMan.getRow()){
							ghost[1].setRow(14);
							ghost[1].setColumn(14);
						}

						//If Ghost 2 collides with PacMan
						else if (ghost[2].getColumn() == pacMan.getColumn() && ghost[2].getRow() == pacMan.getRow()){
							ghost[2].setRow(12);
							ghost[2].setColumn(16);
						}
					}
				}

				//2.2.5 Otherwise update the picture on the screen
				else
					cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());

			}
		}
	}

	/**
	 * Stop the game when PacMan and a ghost 'collide'
	 */
	private void death() {

		Audio.pacManFever.stop();

		//1. Set PacMan dead
		pacMan.setDead(true);

		//2. Stop the game and music
		stopGame(); 

		//3. Determine the current location of PacMan on the screen
		// and assign a picture of a skull
		cell[pacMan.getRow()][pacMan.getColumn()].setIcon(SKULL);

		//4. Play Death Audio Clip
		Audio.deadSound();

		System.out.println(HighScore.getHighscore());

		//5. Check and Save Highscore
		if (score > HighScore.getHighscore()){
			name = JOptionPane.showInputDialog("You set a new highscore! What is your name?");

			//5.1 Call WriteFile method
			HighScore.writeFile();
		}
		else{
			System.out.println("try again");

		}

		//6. PacMan Lives 
		if (PacManGame.pacLives > 0 && pacMan.isDead() == true){

			//6.1 Subtract Life Remaining
			PacManGame.pacLives --;

			//6.2 Change status of PacMan to alive
			pacMan.setDead(false);
			PacManGame.death = false;

			//6.3 Reset Score to 0
			score = 0;

			//6.4  Reload Game 
			new PacManGUI();
			
			//6.5 Changes where respawn to prevent losing lives unnecessarily  
			pacMan.setRow(16);
			pacMan.setColumn(14);
		}

		//7. Verifies if no lives left and death is true
		else if (PacManGame.pacLives == 0 && pacMan.isDead() == true){

			PacManGame.death = true;

		}
	}

	/**
	 * Checks Game to see if PacMan is dead to load play end screen
	 */
	private void checkDeath() {

		if (PacManGame.death = true && pacMan.isDead())

			//Closes and Opens End Screen GUI
			new EndScreen();
	}

	/**
	 * Determines if PacMan has collided with a Ghost
	 *
	 * @return collided
	 */
	private boolean collided(){

		//1. Cycle through all the ghosts to see if anyone has caught PacMan
		for (Ghost g: ghost) {

			//1.1. If the ghost is in the same location then return that a collision occurred
			if (g.getRow()==pacMan.getRow() && g.getColumn()==pacMan.getColumn())
				return true;
		}

		//2. If no ghosts were in the same location then return that no collision occurred
		return false;

	}

	/**
	 * Stops the game timer
	 */
	private void stopGame() {
		//1. If PacMan is dead or all the food is eaten then
		// stop the timers
		if (pacMan.isDead() || score==pelletsEaten) {
			pacManTimer.stop();
			ghostTimer.stop();
			animateTimer.stop();
		}

	}

	/**
	 * Moves the ghosts in a random pattern and remove from the house
	 */
	private void moveGhosts(){

		//1. Cycle through all the ghosts
		for (Ghost g: ghost) {

			int dir=0;

			//1.1 Move Ghosts out of house (up)
			if (maze[g.getRow()][g.getColumn()] == 'U')
				dir = 1;

			//1.2 Move Ghosts out of house (right)
			else if (maze[g.getRow()][g.getColumn()] == 'R')
				dir = 2;

			//1.3 //1.1 Move Ghosts out of house (left)
			else if (maze[g.getRow()][g.getColumn()] == 'L')
				dir = 0;


			//1.4 A.I for Ghost (Same Row)
			else if (g.getRow()==pacMan.getRow()){

				//Ghost same row as PacMan and move to the left
				if (g.getColumn() > pacMan.getColumn())
					dir = 0;

				//Ghost same row as PacMan and move to right
				else
					dir = 2;
			} 

			//1.5 A.I. for Ghost (Same Column)	
			else if (g.getColumn()==pacMan.getColumn()){

				//Ghost same column as PacMan and move up 
				if (g.getRow() > pacMan.getRow())
					dir = 1;

				//Ghost same column as PacMan and move down
				else
					dir = 3;
			}	


			//1.6 Blue Mode Active for Ghosts

			else if (bluemode == 2){

				//Ghost same row as PacMan and move to the right
				if (g.getColumn() > pacMan.getColumn())
					dir = 2;

				//Ghost same row as PacMan and move to left
				else if (g.getColumn() < pacMan.getColumn())
					dir = 0; 

				//Ghost same column as PacMan and move down 
				else if (g.getRow() > pacMan.getRow())
					dir = 3;

				//Ghost same column as PacMan and move up
				else if (g.getRow() < pacMan.getRow())
					dir = 1;
			} 


			//1.6 Increase Ghost speed with 60 pellets left 
			else if (pelletsEaten == 165){
				ghostTimer.setDelay(250);
			}

			//1.7 Increase Ghost speed with 40 pellets left 
			else if (pelletsEaten == 185){
				ghostTimer.setDelay(175);
			}

			//1.6 Increase Ghost speed with 20 pellets left 
			else if (pelletsEaten == 205){
				ghostTimer.setDelay(150);
			}

			//1.7. Keep selecting random directions to avoid
			// 'back-tracking'
			else if (bluemode == 1){
				do {
					dir = (int)(Math.random()*4);	

				} while (Math.abs(g.getDirection() - dir) == 2);
			}

			//1.8. Set the ghosts direction
			g.setDirection(dir);

			//1.9 Move the ghost
			performMove(g);
		}

	}

	/**
	 * Mandatory method to implement KeyListener interface
	 */
	public void keyReleased(KeyEvent key) {
		//Not Used
	}

	/**
	 * Mandatory method to implement KeyListener interface
	 */
	public void keyTyped(KeyEvent key) {
		//Not Used
	}

	/**
	 * Determines the source of the action as either the game timer or the animation timer and then performs the corresponding actions	 
	 */
	public void actionPerformed(ActionEvent e) {

		//1. If the action is the PacMan timer 
		if (e.getSource()==pacManTimer) {
			//1.1. Then move the PacMan and the ghosts
			performMove(pacMan);

			//1.2 Show Highscore on Label
			PacManGUI.highScoreLabel.setText("Highscore: " + HighScore.getHighscore() + " by " + HighScore.getname());

			//1.3 Show Lives Label 
			PacManGUI.livesLabel.setText("Lives Remaining : " + PacManGame.pacLives);

			//1.4 Show Score Label 
			PacManGUI.scoreLabel.setText("Score : " + String.valueOf(score)); 

		}	

		//2. If the action is the Ghost timer
		else if (e.getSource()==ghostTimer ) {
			moveGhosts();
		}

		//3. Otherwise, if the action is the animation timer
		else if (e.getSource()==animateTimer) {
			//3.1. Animate PacMan through the current step
			animatePacMan();

			//3.2. Increment the step number
			pStep++;

			//3.3. If the step is the last step then reset the step to 0
			if (pStep==3)
				pStep=0;
		}
		//4. If the action is the Blue Mode Timer
		else if (e.getSource() == blueModeTimer){

			//4.1 If Bluemode is active, reset to not active 
			bluemode = 1;

			//4.2 Reset images back to normal for ghosts
			ghost[0].setIcon(Ghost.IMAGE[0]);
			ghost[1].setIcon(Ghost.IMAGE[1]);
			ghost[2].setIcon(Ghost.IMAGE[2]);

		}
	}

	/**
	 * Animates PacMan in 3 steps: open mouth, draw black square, move and close mouth	 
	 */
	private void animatePacMan() {

		//1. If it is step 0 of animation
		if (pStep == 0) {

			//1.1 Open mouth in current cell
			cell[pacMan.getRow()][pacMan.getColumn()].setIcon (PacMan.IMAGE[pacMan.getDirection()][1]);

			//1.2 Delay the animation timer
			animateTimer.setDelay(100);

		}

		//2. Otherwise if it is step 1 of animation
		else if (pStep==1){
			//2.1. Blank the current cell
			cell[pacMan.getRow()][pacMan.getColumn()].setIcon(BLANK);
		}

		//3. Otherwise if it is step 2 of animation
		else if (pStep ==2) {
			//3.1. Move pacMan
			pacMan.move();

			//3.2. If there is any food in the new square on the maze and the Mover is PacMan then
			if (maze[pacMan.getRow()][pacMan.getColumn()]=='F'){
				//3.2.1. Increment the score
				score++;
				pelletsEaten++;

				if (pelletsEaten == 223){

					//3.2.1.1 Call stop Game and nextLevel method
					stopGame();

					//3.2.1.2 High score
					if (score >=  HighScore.getHighscore()){
						name = JOptionPane.showInputDialog("You set the new highscore! What is your name?");

						//Call WriteFile method
						HighScore.writeFile();
					}
					else{
						System.out.println("try again");

					}

					//3.2.1.2 Open End Screen
					SwingUtilities.getWindowAncestor(this).dispose();
					new EndScreen ();

				}

				//3.2.2. Mark the maze at the new position to 'eaten'
				maze[pacMan.getRow()][pacMan.getColumn()]='E';

				//3.2.3 Play Chomp Music 
				Audio.pacManChomp();

			}

			//3.3. If there is any cherry in the new square on the maze and the Mover is PacMan then
			else if (maze[pacMan.getRow()][pacMan.getColumn()] == 'C') {

				//3.3.1 Increment Score by 100
				score += 100;

				//3.3.2 Assign square to empty
				maze[pacMan.getRow()][pacMan.getColumn()] = 'E';
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(BLANK);

				//3.3.3 Implement Chery Eat Sound 
				Audio.fruitEatSound();

			}

			//3.4. If there is any cherry in the new square on the maze and the Mover is PacMan then
			else if (maze[pacMan.getRow()][pacMan.getColumn()] == 'T') {

				//3.3.1 Start Blue Mode Timer
				blueModeTimer.start();
				bluemode = 2;

				score += 10;
				PacManGUI.scoreLabel.setText("Score : " + String.valueOf(score));

				//3.3.2 Assign square to empty
				maze[pacMan.getRow()][pacMan.getColumn()] = 'E';
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(BLANK);

				//3.3.3 Assign Ghosts to blue when active 
				ghost[0].setIcon(Ghost.IMAGE[3]);
				ghost[1].setIcon(Ghost.IMAGE[4]);
				ghost[2].setIcon(Ghost.IMAGE[5]);

			}

			//3.5. Stop the animation timer
			animateTimer.stop();

			//3.6. If pacMan is dead then show a skull
			if (pacMan.isDead())
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(SKULL);

			//3.7. Otherwise show the appropriate closed pacMan
			// based on its direction
			else
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(PacMan.IMAGE[pacMan.getDirection()][0]);
		}

	}
}
