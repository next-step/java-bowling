package bowling.view;

import bowling.service.BowlingGame;

import java.io.PrintStream;
import java.util.stream.Stream;

public class ResultView {

    public static final String GUIDE_BOWLING_UI_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String GUIDE_BOWLING_BOARD_BODY_FORMATTER = "  %-4s|";
    public static final String WHITE_SPACE = "";
    private final PrintStream out;

    public ResultView() {
        this.out = new PrintStream(System.out);
    }

    public void printBoard(String name) {
        printBoardHeader();
        System.out.printf("|  %s |      |      |      |      |      |      |      |      |      |      |", name);
        System.out.println();

    }

    public void printResult(BowlingGame bowling) {
        printBoardHeader();
        System.out.print("|  " + bowling.user() + " |");
        bowling.frames().stream()
                .map(frame -> printBoardBody(frame.toString()))
                .forEach(System.out::print);
        printEmptyBody(bowling);
        System.out.println();
    }

    private void printEmptyBody(BowlingGame bowling) {
        Stream.generate(() -> printBoardBody(WHITE_SPACE))
                .limit(10 - bowling.frameNumber())
                .forEach(System.out::print);
    }

    private void printBoardHeader() {
        System.out.println(GUIDE_BOWLING_UI_BOARD);
    }

    private String printBoardBody(String item) {
        return String.format(GUIDE_BOWLING_BOARD_BODY_FORMATTER, item);
    }
}
