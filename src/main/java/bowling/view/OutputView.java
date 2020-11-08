package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.List;

import bowling.domain.Frame;

public class OutputView {
	public static final String VERTICAL_LINE = "|";
	public static final int MAX_FRAME_COUNT = 10;
	public static final String SCORE_BOARD_NUMBER = "  |  NAME  |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

	private OutputView() {}

	public static void printScore(String playerName, List<Frame> frames) {
		// print score header
		// print score board
	}

	private static void printScoreBoard(String playerName, List<Frame> frames) {
		System.out.printf("%s%3s ", VERTICAL_LINE, playerName);
		for (Frame frame : frames) {
			System.out.printf("%s%s", VERTICAL_LINE, String.join("|", frame.getScore()));
		}
		for (int i = frames.size(); i < MAX_FRAME_COUNT; i++) {
			System.out.printf("%s      ", VERTICAL_LINE);
		}
		System.out.println();
	}
}
