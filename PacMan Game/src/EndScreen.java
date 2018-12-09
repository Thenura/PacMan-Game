import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents the end screen which appears when
 * the game has finished (death)
 * 
 * @author Thenura Jayasinghe
 */
public class EndScreen extends JFrame implements ActionListener {

	//End Panel
	JPanel endPanel = new JPanel();

	//End Panel Buttons
	JButton playAgainButton = new JButton("Play Again");
	JButton exitButton = new JButton("Exit");
	
	public EndScreen() {
		
		//1. Setup the GUI
		setLayout(null);
		
		//2. Set Bounds
		this.setBounds(0, 0, 500, 500);
		
		//3. Set The Title and Exit Option
		setTitle("PacMan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//4. Make GUI Visible
		setVisible(true);		
		
		//5. Call End Panel Method
		endPanel();
	}

	public void endPanel() {

		//1. Set up Panel
		endPanel.setLayout(null);
		endPanel.setVisible(true);
		Font buttonFont = new Font ("Arial",Font.BOLD,45);
		
		//2. Set Bounds 
		endPanel.setBounds(0,0,500,500);
		playAgainButton.setBounds(58,74,371,142);
		exitButton.setBounds(58,244,371,142);
		
		//3. Change Color
		endPanel.setBackground(Color.BLACK);
		playAgainButton.setBackground(Color.YELLOW);
		exitButton.setBackground(Color.YELLOW);
		
		//4. Set Font
		playAgainButton.setFont(buttonFont);
		exitButton.setFont(buttonFont);

		//5. Add Action Listener
		playAgainButton.addActionListener(this);
		exitButton.addActionListener(this);

		//6. Add objects to Panel
		endPanel.add(playAgainButton);
		endPanel.add(exitButton);
		
		//7. Add Panel
		this.add(endPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//6.Action If Play Button Clicked
		if(e.getSource() == playAgainButton){
			
			//6.1 Reset Score to 0
			Board.score = 0;
			
			//6.2 Stop PacMan Music
			Audio.pacManFever.stop();
			
			//6.3 Make End Screen Non Visible
			setVisible(false);
			
			//6.4 Closes JFrame
			dispose();
			
			//6.5 Resets PacMan Lives to 3 
			PacManGame.pacLives = 3;

			//6.6 Opens PacMan GUI
			new PacManGUI();

		}	  
		
		//7. If Exit Button is Pressed
		else if(e.getSource() == exitButton){
			
			//Exits Program
			System.exit(0); 	
			
		}
	}
}
