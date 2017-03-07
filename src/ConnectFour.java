/**
 * @author Stanley Plagata, Nathan Quiocson
 * 
 */
public class ConnectFour {
	
	private static int boardSize;
	private static int winSize;
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Usage: <boardSize> <winSize>");
			return;
		}
		if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 20) {
			System.out.println("Error: Please use a board size of 1 - 20");
			return;
		}
		if (Integer.parseInt(args[1]) > Integer.parseInt(args[0])) {
			System.out.println("Error: Win length cannot be longer than board length");
			return;
		}
		
		boardSize = Integer.parseInt(args[0]);
		winSize = Integer.parseInt(args[1]);
		
		GUI gui = new GUI();
		int gameover = 0;
		
		/*
		 * gameover == 0: game in progress
		 * gameover == 1: game ends with winner
		 * gameover == 2: game ends in draw
		 * gameover == -1: game quit
		 */
		while (gameover != -1) {
			if (gameover == 0) {
				gui.updateBoard();
				if (gui.endWin() == 1) {
					gameover = 1;
                } else if (gui.endDraw() == 1) {
                	gameover = 2;
                } else if (gui.choseRestart() == 1) {
                    gui = new GUI();
                    gameover = 0;
                }
			} else if (gameover == 1) {
				gui.showVictory();
                if (gui.choseQuit() == 1) {
                	gameover = -1;
                } else  if (gui.choseRestart() == 1) {
                    gui = new GUI();
                    gameover = 0;
                }
			} else if (gameover == 2) {
				gui.showDraw();
                if (gui.choseQuit() == 1) {
                	gameover = -1;
                } else if (gui.choseRestart() == 1) {
                    gui = new GUI();
                    gameover = 0;
                }
			}
		}
	}
	
	public static int getBoardSize() { return boardSize; }
	public static int getNumToWin() { return winSize; }
	
}
