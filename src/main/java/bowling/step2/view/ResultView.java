package bowling.step2.view;

import bowling.step2.domain.Game;

public class ResultView {
	private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
	private static final String BOARD_FORMAT = "|  %-4s";
	private static final int BOARD_SIZE = 11;

	public static void printInitBowlingScore(Game game) {
		System.out.println(BOARD);
		System.out.printf(BOARD_FORMAT, game.getPlayerName());
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.printf(BOARD_FORMAT, "");
		}
		System.out.println();
	}
}
