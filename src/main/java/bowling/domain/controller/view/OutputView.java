package bowling.domain.controller.view;

import bowling.domain.bowling.Bowling;
import bowling.domain.frame.Frame;

import java.util.List;

public class OutputView {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final String DELIMITER = "|";

    private OutputView() {
    }

    public static void showBowling(Bowling bowling) {
        printBoard();
        append(DELIMITER);
        printName(bowling.nameOfParticipant());
        printScore(bowling.frames());
    }

    private static void printBoard() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printName(String name) {
        append(" " + name + "  ");
        append(DELIMITER);
    }

    private static void append(String asd) {
        STRING_BUILDER.append(asd);
    }

    private static void printScore(List<Frame> frames) {
//        frames.
    }


}
