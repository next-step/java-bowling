package bowling.view;

import bowling.domain.Frame;
import bowling.service.BowlingGame;

import java.io.PrintStream;
import java.util.stream.Stream;

public class ResultView {

    private static final String GUIDE_BOWLING_UI_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String GUIDE_BOWLING_BOARD_BODY_FORMATTER = "  %-4s|";
    private static final String WHITE_SPACE = "";
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";
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
        bowling.frames()
                .stream()
                .map(this::printCondition)
                .forEach(System.out::print);
        printEmptyBody(bowling);
        System.out.println();
    }

    private String printCondition(Frame frame) {

        if (frame.state().equals(Frame.State.STRIKE)) {
            return printBoardBody("X");
        }
        if (frame.state().equals(Frame.State.SPARE)) {
            return printBoardBody("/");
        }
        if (frame.state().equals(Frame.State.GUTTER)) {
            return printBoardBody("-");
        }
        return printBoardBody(frame.toString());
    }

    private void printEmptyBody(BowlingGame bowling) {
        Stream.generate(() -> printBoardBody(WHITE_SPACE))
                .limit(10 - bowling.frames.frameNumber())
                .forEach(System.out::print);
    }

    private void printBoardHeader() {
        System.out.println(GUIDE_BOWLING_UI_BOARD);
    }

    private String printBoardBody(String item) {
        return String.format(GUIDE_BOWLING_BOARD_BODY_FORMATTER, item);
    }
}
