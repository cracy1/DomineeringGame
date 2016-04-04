
public class DomineeringMove {

	Integer fstX, fstY; // the first cell of the move
	Integer sndX, sndY; // the second cell of the move

	/**
	 * Constructor that reads the move of a player
	 * 
	 * @param args
	 */
	public DomineeringMove(String args) {

		String[] in = args.split(",");

		fstX = Integer.parseInt(in[0]);
		fstY = Integer.parseInt(in[1]);
		sndX = Integer.parseInt(in[2]);
		sndY = Integer.parseInt(in[3]);

		// we check to see whether a move is legal or not, not caring if
		// it is
		// horizontal or vertical

		assert ((sndX - fstX) + (sndY - fstY) == 1);

	}

	/**
	 * The move a player can play
	 * 
	 * @param x1
	 *                x of the first cell
	 * @param y1
	 *                y of the first cell
	 * @param x2
	 *                x of the second cell
	 * @param y2
	 *                y of the second cell
	 * @return 
	 */
	public DomineeringMove(int x1, int y1, int x2, int y2) {

//		System.out.println("x1 = " + x1 + " y1 = " + y1 + " x2 = " + x2 + " y2 = " + y2);

		try {
			assert ((x2 - x1) + (y2 - y1) == 1);
		} 
		catch (AssertionError error) {
			System.out.println("This move cannot be played(illegal)");
		}

		fstX = x1;
		fstY = y1;
		sndX = x2;
		sndY = y2;

	}

	/**
	 * Method to get the x coordinate of the first cell
	 * 
	 * @return the x coordinate of the first cell
	 */
	public Integer getFstX() {
		return fstX;
	}

	/**
	 * Method to get the y coordinate of the first cell
	 * 
	 * @return the y coordinate of the first cell
	 */
	public Integer getFstY() {
		return fstY;
	}

	/**
	 * Method to get the x coordinate of the second cell
	 * 
	 * @return the x coordinate of the second cell
	 */
	public Integer getSndX() {
		return sndX;
	}

	/**
	 * Method to get the y coordinate of the second cell
	 * 
	 * @return the y coordinate of the second cell
	 */
	public Integer getSndY() {
		return sndY;
	}

	/**
	 * equals method we have to define for Move objects
	 */
	@Override
	public boolean equals(Object o) {

		if (o == null || !o.getClass().equals(this.getClass())) {
			return false;
		}

		return fstX.equals(((DomineeringMove) o).getFstX()) && fstY.equals(((DomineeringMove) o).getFstY())
				&& sndX.equals(((DomineeringMove) o).getSndX())
				&& sndY.equals(((DomineeringMove) o).getSndY());

	}

	/**
	 * hashCode method we have to define, so 2 equal things should have the
	 * same hash code
	 */
	@Override
	public int hashCode() {

		return Integer.hashCode(((fstX + sndX) + (fstY + sndY)) * ((fstX + sndX) + (fstY + sndY) + 1) / 2
				+ (fstY + sndY));
	}

	/**
	 * method to print out the move a player is playing
	 */
	@Override
	public String toString() {
		return "(" + fstX + " " + fstY + " " + sndX + " " + sndY + ")";
	}
}
