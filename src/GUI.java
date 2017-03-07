/**
 * @author Stanley Plagata, Nathan Quiocson
 * 
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
	
	private int boardSize = ConnectFour.getBoardSize();
	private int currentPlayer = 1;
	private int winValue = 0;
	private int drawValue = 0;
	private int restartValue = 0;
	private int quitValue = 0;
	
	Board gameBoard = new Board(boardSize, boardSize);
	Controller gameController = new Controller(gameBoard);
	
	private JFrame boardFrame;
	private JLabel[][] spaces;
	
	public GUI() {
		boardFrame = new JFrame("Connect Four - Player " + currentPlayer + "'s Turn");
		JPanel boardPanel = (JPanel) boardFrame.getContentPane();
		boardPanel.setLayout(new GridLayout(boardSize + 1, boardSize));
		spaces = new JLabel[boardSize][boardSize];
		
		JButton[] buttons = new JButton[boardSize];
		
		for (int i = 0; i < boardSize; i++) {
			buttons[i] = new JButton();
			buttons[i].setActionCommand(Integer.toString(i));
			buttons[i].addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int columnSpaceLeft = gameBoard.findRemainingColumnSpace(Integer.parseInt(e.getActionCommand()));
							
							if (columnSpaceLeft == -1) {
								JOptionPane.showMessageDialog(null, "Please choose another column", "Column Full", JOptionPane.INFORMATION_MESSAGE);
							} else {
								if (gameController.isWon(Integer.parseInt(e.getActionCommand()), columnSpaceLeft, currentPlayer)) {
									winValue = 1;
								} else if (gameController.isDraw()) {
									drawValue = 1;
								} else {
									currentPlayer = gameController.changePlayer(currentPlayer);
									boardFrame.setTitle("Connect Four - Player " + currentPlayer + "'s Turn");
								}
							}
						}
					});
			boardPanel.add(buttons[i]);
		}
		
		for (int column = 0; column < boardSize; column++) {
			for (int row = 0; row < boardSize; row++) {
				spaces[row][column] = new JLabel();
				spaces[row][column].setHorizontalAlignment(SwingConstants.CENTER);
				spaces[row][column].setBorder(new LineBorder(Color.black));
				boardPanel.add(spaces[row][column]);
			}
		}
		
		boardFrame.setContentPane(boardPanel);
		boardFrame.setSize(boardSize * 85, boardSize * 85);
		boardFrame.setVisible(true);
		boardFrame.setLocationRelativeTo(null);
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Updates the color of the spaces chosen by the player
	 */
	public void updateBoard() {
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				if (gameBoard.getPlayerOwnership(row, column, 1)) {
					spaces[row][column].setOpaque(true);
					spaces[row][column].setBackground(Color.red);
				} else if (gameBoard.getPlayerOwnership(row, column, 2)) {
					spaces[row][column].setOpaque(true);
					spaces[row][column].setBackground(Color.yellow);
				}
			}
		}
	}
	
	/**
	 * Shows gameover dialog
	 */
	public void showVictory() {
		int n = JOptionPane.showConfirmDialog(boardFrame, "Play again?", "Player " + currentPlayer + " Wins!", JOptionPane.YES_NO_OPTION);
		
		if (n == 0) {
			boardFrame.dispose();
			restartValue = 1;
		} else {
			boardFrame.dispose();
			quitValue = 1;
		}
	}
	
	/**
	 * Shows draw dialog
	 */
	public void showDraw() {
		int n = JOptionPane.showConfirmDialog(boardFrame, "Play again?", "Draw", JOptionPane.YES_NO_OPTION);
		
		if (n == 0) {
			boardFrame.dispose();
			restartValue = 1;
		} else {
			boardFrame.dispose();
			quitValue = 1;
		}
	}
	
	public int endWin() { return winValue; }
	public int endDraw() { return drawValue; }
	public int choseRestart() { return restartValue; }
	public int choseQuit() { return quitValue; }
	
}
