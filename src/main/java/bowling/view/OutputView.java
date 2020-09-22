package bowling.view;

import bowling.domain.Frame;

import java.util.Collections;
import java.util.List;

import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

public class OutputView {

    public static final String VERTICAL_SIGN = " | ";
    public static final int MAX_FRAME_COUNT = 10;
    public static final String SCORE_BOARD_HEADER = " | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private OutputView() {
    }

    public static void printScore(String playerName, List<Frame> frames) {
        printScoreHeader();
        printScoreBoard(playerName, frames);
    }

    public static void printEmptyScore(String playerName) {
        printScore(playerName, Collections.emptyList());
    }

    public static void printScoreHeader() {
        System.out.println(SCORE_BOARD_HEADER);
    }

    private static void printScoreBoard(String playerName, List<Frame> frames) {
        System.out.print(String.format("%s%3s ", VERTICAL_SIGN, playerName));

        for (Frame frame : frames) {
            //frame.getScore();
            System.out.print(String.format("%s%s", VERTICAL_SIGN,
                    frame.getScore()
                            .stream()
                            .collect(joining("|"))));
        }

        for (int i = frames.size() ; i < MAX_FRAME_COUNT; i++) {
            System.out.print(String.format("%s    ", VERTICAL_SIGN));
        }

        System.out.println();
    }
}
