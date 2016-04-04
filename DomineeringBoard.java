import java.util.HashSet;
import java.util.Set;

public class DomineeringBoard extends Board<DomineeringMove> {

	/**
	 * H is the player who starts, places the dominos horizontally V is the
	 * second player, places the dominos vertically
	 */
	private static final Player H = Player.MAXIMIZER;
	private static final Player V = Player.MINIMIZER;
	private boolean[][] board;
	private HashSet<DomineeringMove> hMoves;
	private HashSet<DomineeringMove> vMoves;
	private String[][] currentBoard;
	private int lines;
	private int columns;

	/**
	 * Constructor for the 4 x 4 board
	 */
	public DomineeringBoard() {
		board = new boolean[4][4];
		hMoves = new HashSet<>();
		vMoves = new HashSet<>();
		currentBoard = new String[4][4];

	}

	/**
	 * Constructor for the 4 x 4 board
	 * 
	 * @param bool
	 * @param hMoves
	 * @param vMoves
	 * @param string
	 */
	private DomineeringBoard(boolean[][] bool, HashSet<DomineeringMove> hMoves, HashSet<DomineeringMove> vMoves,
			String[][] string) {

		this.hMoves = hMoves;
		this.vMoves = vMoves;

		this.board = bool;
		this.currentBoard = string;

	}

	/**
	 * Constructor for the m x n board
	 * 
	 * @param m
	 *                the number of rows
	 * @param n
	 *                the number of columns
	 */
	public DomineeringBoard(int m, int n) {

		hMoves = new HashSet<>();
		vMoves = new HashSet<>();

		board = new boolean[n][m];
		currentBoard = new String[n][m];

		lines = n;
		columns = m;
	}

	/**
	 * Construct a m X n board
	 * 
	 * @param m
	 *                the number of lines
	 * @param n
	 *                the number of columns
	 * @param bool
	 *                the board
	 * @param hMoves
	 *                the hash set for the horizontal moves
	 * @param vMoves
	 *                the hash set for the vertical moves
	 * @param string
	 *                the current state of the board
	 */

	private DomineeringBoard(int m, int n, boolean[][] bool, HashSet<DomineeringMove> hMoves,
			HashSet<DomineeringMove> vMoves, String[][] string) {

		this.hMoves = hMoves;
		this.vMoves = vMoves;

		this.board = bool;
		this.currentBoard = string;

		lines = m;
		columns = n;
	}

	/**
	 * Method to determine whose turn is it
	 */
	@Override
	public Player nextPlayer() {
		if (hMoves.size() == vMoves.size())
			return H;
		return V;

	}

	/**
	 * Method to determine the available moves on the board
	 */
	@Override
	public Set<DomineeringMove> availableMoves() {
		return (nextPlayer() == H ? availableHMoves() : availableVMoves());
	}

	/**
	 * Method to determine the available moves for the H PLAYER
	 * 
	 * @return a set of available moves for the H PLAYER
	 */
	@SuppressWarnings("unchecked")
	private Set<DomineeringMove> availableHMoves() {
		@SuppressWarnings("rawtypes")
		Set hMoves = new HashSet<>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length - 1; j++) {
				if (!board[i][j] && !board[i][j + 1])
					hMoves.add(new DomineeringMove(i, j, i, j + 1));
			}
		}
		return hMoves;
	}

	/**
	 * Method to determine the available moves for the V PLAYER
	 * 
	 * @return a set of available moves for the V PLAYER
	 */
	@SuppressWarnings("unchecked")
	private Set<DomineeringMove> availableVMoves() {
		@SuppressWarnings("rawtypes")
		Set vMoves = new HashSet<>();

		for (int j = 0; j < board[0].length; j++) {
			for (int i = 0; i < board.length - 1; i++) {
				if (!board[i][j] && !board[i + 1][j])
					vMoves.add(new DomineeringMove(i, j, i + 1, j));
			}
		}
		return vMoves;
	}

	/**
	 * Method to get the current state of the board; 1 -> H has won ;0 -> no
	 * player has won yet; -1 -> V has won
	 */
	@Override
	public int value() {
		if (availableMoves().isEmpty()) {
			if (nextPlayer() == H) {
				return -1;
			} else {
				return 1;
			}

		}
		return 0;

	}

	/**
	 * Helper method for the play() method clones the board
	 * 
	 * @param bool
	 *                the board in which we want to clone
	 * @return the fresh copy of the board
	 */
	public boolean[][] cloneBoolean(boolean[][] board) {
		boolean[][] bool = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)
				bool[i][j] = board[i][j];
		}
		return bool;
	}

	/**
	 * Method that clones a string
	 * 
	 * @param string
	 * @return the cloned string
	 */
	public String[][] cloneString(String[][] currentBoard) {
		String[][] string = new String[currentBoard.length][currentBoard[0].length];
		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[0].length; j++)
				string[i][j] = currentBoard[i][j];
		}
		return string;
	}

	/**
	 * Method that clones a set
	 * 
	 * @param set
	 * @return the cloned set
	 */
	@SuppressWarnings("unchecked")
	public HashSet<DomineeringMove> cloneHashSetH(@SuppressWarnings("rawtypes") HashSet hMoves) {
		HashSet<DomineeringMove> set = new HashSet<DomineeringMove>();
		set.addAll(hMoves);
		return set;

	}

	/**
	 * Method that clones a set
	 * 
	 * @param set
	 * @return the cloned set
	 */
	@SuppressWarnings("unchecked")
	public HashSet<DomineeringMove> cloneHashSetV(@SuppressWarnings("rawtypes") HashSet vMoves) {
		HashSet<DomineeringMove> set = new HashSet<DomineeringMove>();
		set.addAll(vMoves);
		return set;
	}

	/**
	 * Method that creates a fresh copy of the board and plays the move
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Board<DomineeringMove> play(DomineeringMove move) {

		try {
			assert (!hMoves.contains(move) && !vMoves.contains(move));

			assert ((currentBoard[move.getFstX()][move.getFstY()] == null
					|| currentBoard[move.getFstX()][move.getFstY()].equals(""))
					&& (currentBoard[move.getSndX()][move.getSndY()] == null
							|| currentBoard[move.getSndX()][move.getSndY()].equals("")));

			assert ((nextPlayer() == H && move.getFstX().equals(move.getSndX()))
					|| (nextPlayer() == V && move.getFstY().equals(move.getSndY())));

			assert ((nextPlayer() == H && ((move.getFstX() - move.getSndX() == 0)
					&& (move.getSndY() - move.getFstY() == 1))))
					|| (nextPlayer() == V && ((move.getFstY() - move.getSndY() == 0)
							&& (move.getSndX() - move.getFstX() == 1)

			));

		} catch (AssertionError error) {
			System.out.println("This move cannot be played(illegal)");
			return null;
		}

		boolean[][] copyOfBoolean = cloneBoolean(board);
		String[][] copyOfString = cloneString(currentBoard);
		HashSet copyOfHashSetH = cloneHashSetH(hMoves);
		HashSet copyOfHashSetV = cloneHashSetV(vMoves);
		int x1 = move.getFstX();
		int y1 = move.getFstY();
		int x2 = move.getSndX();
		int y2 = move.getSndY();

		if (nextPlayer().equals(H)) {
			copyOfHashSetH.add(move);
			copyOfBoolean[x1][y1] = true;
			copyOfBoolean[x2][y2] = true;

			copyOfString[x1][y1] = " H ";
			copyOfString[x2][y2] = " H ";
			return new DomineeringBoard(lines, columns, copyOfBoolean, copyOfHashSetH, copyOfHashSetV,
					copyOfString);
		} else {
			copyOfHashSetV.add(move);
			copyOfBoolean[x1][y1] = true;
			copyOfBoolean[x2][y2] = true;

			copyOfString[x1][y1] = " V ";
			copyOfString[x2][y2] = " V ";
			return new DomineeringBoard(lines, columns, copyOfBoolean, copyOfHashSetH, copyOfHashSetV,
					copyOfString);
		}

	}

	/**
	 * Method to show the current state of the board
	 */
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < currentBoard.length; i++) {
			string = string + " ";
			for (int j = 0; j < currentBoard[0].length; j++) {
				if (currentBoard[i][j] == null || currentBoard[i][j].equals(""))
					string = string + "" + i + j + " ";
				if (currentBoard[i][j] == " H ")
					string = string + " H ";
				if (currentBoard[i][j] == " V ")
					string = string + " V ";
			}
			string = string + " \n";
		}
		return " \n" + string + "\n";

	}

}
