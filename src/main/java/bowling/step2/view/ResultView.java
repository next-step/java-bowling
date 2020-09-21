package bowling.step2.view;

import bowling.step2.domain.Game;
import bowling.step2.domain.Player;
import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.FrameBoard;

public class ResultView {
	private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
	private static final String BOARD_FORMAT = "|  %-4s";
	private static final int BOARD_SIZE = 11;

	public static void printInitBoard(Player player) {
		System.out.println(BOARD);
		System.out.printf(BOARD_FORMAT, player.toString());
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.printf(BOARD_FORMAT, "");
		}
		System.out.println();
	}

	public static void printEachFrame(Player player, Frame frame) {
		System.out.println(BOARD);
		System.out.printf(BOARD_FORMAT, player.toString());

		FrameBoard board = frame.makeBoard();
		board.getResults().forEach(result -> System.out.printf(BOARD_FORMAT, result));
		for (int i = 0; i < BOARD_SIZE - board.getResults().size(); i++) {
			System.out.printf(BOARD_FORMAT, "");
		}
		System.out.println();
	}

}
