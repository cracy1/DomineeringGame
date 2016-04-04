import java.util.Scanner;

public class CommandLineDomineering {

	private static class CommandLineDom implements MoveChannel<DomineeringMove> {

		private Scanner in;
		private Integer turns;

		protected CommandLineDom() {
			in = new Scanner(System.in);
		}

		@Override
		public DomineeringMove getMove() {

			System.out.println("Enter your move : ");
			String string = in.nextLine();
			System.out.flush();

			String[] aux = string.split(",");

			int fstX = Integer.parseInt(aux[0]);
			int fstY = Integer.parseInt(aux[1]);

			int sndX;
			int sndY;
			
			if (turns % 2 == 0) {
				sndX = fstX;
				sndY = fstY + 1;
			} else {
				sndX = fstX + 1;
				sndY = fstY;
			}

			return (new DomineeringMove(fstX, fstY, sndX, sndY));

		}

		@Override
		public void giveMove(DomineeringMove move) {
			System.out.println("My move is : " + move);
		}

		@Override
		public void end(int Value) {
			System.out.println("Game over! The result is : " + Value);

		}

		@Override
		public void comment(String msg) {
			System.out.println(msg);
		}

		public static void main(String[] args) {
			DomineeringBoard board = new DomineeringBoard();
			// DomineeringMove move = new DomineeringMove(0, 0, 0,
			// 1);
			// DomineeringMove move2 = new DomineeringMove(1, 2, 2,
			// 2);
			// DomineeringMove move3 = new DomineeringMove(0, 2, 0,
			// 3);
			// board.play(move);
			//
			// board.play(move2);
			// board.play(move3);
			//
			// System.out.println(board.toString());

			// DomineeringMove move = new DomineeringMove(0, 0, 0,
			// 1);
			// board = (DomineeringBoard) board.play(move);
			// System.out.println(board);
			// board.tree().secondPlayer(new
			// CommandLineDomineering());
			// board.tree().secondPlayer(new
			// CommandLineDomineering());

		}

	}
}
