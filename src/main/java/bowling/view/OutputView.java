package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.List;

import bowling.domain.Frame;

public class OutputView {
	public static final String VERTICAL_LINE = "|";
	public static final int MAX_FRAME_COUNT = 10;
	public static final String SCORE_BOARD_NUMBER = "  |  NAME  |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
	public static final String EMPTY_SPACE = "    ";

	private OutputView() {}

	public static void printScore(String playerName, List<Frame> frames) {
		System.out.println(SCORE_BOARD_NUMBER);
		printNameSpace(playerName);
		printScores(frames);
		printEmptySpace(frames.size());
		System.out.println(VERTICAL_LINE);
	}

	private static void printNameSpace(String playerName) {
		System.out.printf("%s%4s", VERTICAL_LINE, playerName);
	}

	private static void printScores(List<Frame> frames) {
		for (Frame frame : frames) {
			String score = String.join(VERTICAL_LINE, frame.getScore());
			System.out.printf("%s%-4s", VERTICAL_LINE, score);
		}
		System.out.println();
	}

	private static void printEmptySpace(int size) {
		for (int i = size; i < MAX_FRAME_COUNT; i++) {
			System.out.printf("%s%s", VERTICAL_LINE, EMPTY_SPACE);
		}
	}
}
