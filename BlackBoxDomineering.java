import java.util.Scanner;

public class BlackBoxDomineering {

	private static Integer height;
	private static Integer width;

	@SuppressWarnings("unused")
	private static class DomineeringMoveChannel implements MoveChannel<DomineeringMove> {

		private Scanner scanner;
		private Integer turns;

		protected DomineeringMoveChannel() {

			scanner = new Scanner(System.in);
			turns = 0;
		}

		@Override
		public DomineeringMove getMove() {

			// String x = System.console().readLine("Enter your move
			// :");

		// 	System.out.println("Enter your move : ");
			String string = scanner.nextLine();

			turns++;

			String[] aux = string.split(",");

			int fstX = Integer.parseInt(aux[0]);
			int fstY = Integer.parseInt(aux[1]);

			int temp;
			temp = fstX;
			fstX = fstY;
			fstY = temp;

			int sndY;
			int sndX;

			if (turns % 2 == 1) {
				sndX = fstX;
				sndY = fstY + 1;
			} else {
				sndX = fstX + 1;
				sndY = fstY;
			}

			return (new DomineeringMove(fstX, fstY, sndX, sndY));

		}

		@Override
		public void giveMove(DomineeringMove domineeringMove) {
			System.out.println(domineeringMove.getFstY() + "," + domineeringMove.getFstX());
			System.out.flush();
			turns++;
		}

		@Override
		public void end(int Value) {
			System.exit(0);
		}

		@Override
		public void comment(String msg) {
			// System.out.println(msg);
		}
	}

	public static void main(String[] args) {
		assert (args.length == 4);
		assert (args[0].equals("first") || args[0].equals("second"));
		assert (args[1].equals("horizontal") || args[1].equals("vertical"));

		width = Integer.parseInt(args[2]);
		height = Integer.parseInt(args[3]);

		Board<DomineeringMove> board = new DomineeringBoard(width, height);

		if (args[0].equals("first")) {
			board.tree().firstPlayer(new DomineeringMoveChannel());
		} else {
			board.tree().secondPlayer(new DomineeringMoveChannel());
		}
	}

}
