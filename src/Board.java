/**
 * @author Stanley Plagata, Nathan Quiocson
 * 
 */
public class Board {

	private int[][] gameBoard;
	private int boardSize;
	private int remainingSpaces;
	
	public Board(int row, int column) {
		gameBoard = new int[row][column];
		boardSize = row;
		remainingSpaces = row * column;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				gameBoard[i][j] = 0;
			}
		}
	}
	
	/**
	 * @param row The row index of the column being checked
	 * @return Number of remaining spaces in the column
	 */
	public int findRemainingColumnSpace(int row) {
		int remainingColumnSpace = -1;
		for (int i = 0; i < boardSize; i++) {
			if (gameBoard[row][i] == 0) {
				remainingColumnSpace = i;
			}
		}
		return remainingColumnSpace;
	}
	
	/**
	 * @param row The row index of the row space being set
	 * @param column The column index of the column space being set
	 * @param currentPlayer The current player
	 */
	public void setSpace(int row, int column, int currentPlayer) {
		gameBoard[row][column] = currentPlayer;
	}
	
	/**
	 * @param row The row index of the column being checked
	 * @param column The column index of the column being checked
	 * @param currentPlayer The current player
	 * @return If current player is owner of the space
	 */
	public boolean getPlayerOwnership(int row, int column, int currentPlayer) {
		return gameBoard[row][column] == currentPlayer;
	}
	
	/**
	 * @return Number of remaining spaces
	 */
	public int getRemainingSpaces() {
		return remainingSpaces;
	}
	
}
