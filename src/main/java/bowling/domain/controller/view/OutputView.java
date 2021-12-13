package bowling.domain.controller.view;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowling.Bowling;
import bowling.domain.frame.Frame;

import java.util.List;

import static java.lang.String.format;

public class OutputView {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final String DELIMITER = "|";
    private static final String EMPTY_BODY = "      |";
    private static final int NUMBER_OF_FRAME = 10;

    private OutputView() {
    }

    public static void showBowling(Bowling bowling) {
        printBoard();
        append(format("%s %s  ", DELIMITER, bowling.nameOfParticipant()));
        printPlayedFrames(bowling.frames());
    }

    private static void printBoard() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printPlayedFrames(List<Frame> frames) {
        append(DELIMITER);
        for (Frame frame : frames) {
            printBowls(frame.bowls());
            append(DELIMITER);
        }
        for (int i = 0; i < NUMBER_OF_FRAME - frames.size(); i++) {
            append(EMPTY_BODY);
        }

        flushBuffer();
    }

    private static void printBowls(List<Bowl> bowls) {
        for (Bowl bowl : bowls) {
            append(bowl.getView());
        }
    }

    private static void append(String asd) {
        STRING_BUILDER.append(asd);
    }

    private static void flushBuffer() {
        System.out.println(STRING_BUILDER);
        STRING_BUILDER.setLength(0);
    }

}
