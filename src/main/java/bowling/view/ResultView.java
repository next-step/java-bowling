package bowling.view;

import bowling.domain.engine.Frame;
import bowling.service.BowlingGame;
import org.apache.logging.log4j.util.Strings;

import java.io.PrintStream;
import java.util.stream.Stream;

import static bowling.domain.Constants.BOWLING_LAST_ROUND;

public class ResultView {

    private static final String GUIDE_BOWLING_UI_HEADER_BOARD = "| NAME |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String GUIDE_BOWLING_UI_BODY_BOARD = "|  %s |        |        |        |        |        |        |        |        |        |        |";
    private static final String GUIDE_BOWLING_BOARD_BODY_FORMATTER = "  %-5s |";

    private final PrintStream out;

    public ResultView() {
        this.out = new PrintStream(System.out);
    }

    public void printBoard(final String name) {
        printBoardHeader();
        out.printf(GUIDE_BOWLING_UI_BODY_BOARD, name);
        out.println();

    }

    public void printCustomResult(final BowlingGame bowling, final String name) {
        printBoardHeader();
        out.print("|  " + name + " |");
        bowling.frames()
                .stream()
                .map(this::printCustomCondition)
                .forEach(out::print);
        printCustomEmptyBody(bowling);
        out.println();
    }

    private void printBoardHeader() {
        out.println(GUIDE_BOWLING_UI_HEADER_BOARD);
    }

    private String printBoardBody(final String item) {
        return String.format(GUIDE_BOWLING_BOARD_BODY_FORMATTER, item);
    }

    private String printCustomCondition(final Frame frame) {
        return printBoardBody(frame.printFrame());
    }

    private void printCustomEmptyBody(final BowlingGame bowling) {
        Stream.generate(() -> printBoardBody(Strings.EMPTY))
                .limit(BOWLING_LAST_ROUND - bowling.frames().size())
                .forEach(out::print);
    }
}
