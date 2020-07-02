package bowling.ui;

import bowling.domain.BowlingGame;

import java.util.stream.Stream;

public class ResultView {
    private static final int MAX_FRAME_SIZE = 10;
    private static final String BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private ResultView() {
    }

    public static void printResult(BowlingGame bowlingGame) {
        System.out.println(BOARD_TITLE);
        printScoreLine(bowlingGame);
        printEmptyLine(bowlingGame);
        System.out.println("\n");
    }

    private static void printScoreLine(BowlingGame bowlingGame) {
        System.out.print("|" + formatting(bowlingGame.whoseTurn()));
        bowlingGame.getFrames().stream()
                .map(frame -> formatting(frame.printFrameResult()))
                .forEach(System.out::print);
    }

    private static void printEmptyLine(BowlingGame bowlingGame) {
        Stream.generate(() -> formatting(""))
                .limit(MAX_FRAME_SIZE - bowlingGame.getCurrentIndex() - 1)
                .forEach(System.out::print);
    }

    private static String formatting(String input) {
        return String.format("  %-4s|", input);
    }
}
