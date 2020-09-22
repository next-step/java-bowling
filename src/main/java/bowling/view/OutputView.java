package bowling.view;

import bowling.domain.Frame;

import java.util.Collections;
import java.util.List;

import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

public class OutputView {

    public static final int MAX_FRAME_COUNT = 10;
    public static final String VERTICAL_SIGN = " | ";
    public static final String SCORE_BOARD_HEADER = " | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final Object EMPTY_SPACE = "    ";

    private OutputView() {
    }

    public static void printScore(String playerName, List<Frame> frames) {
        printScoreHeader();
        printNameSpace(playerName);
        printScores(frames);
        printEmptySpace(frames.size());
        System.out.println(VERTICAL_SIGN);
    }

    public static void printEmptyScore(String playerName) {
        printScore(playerName, Collections.emptyList());
    }

    public static void printScoreHeader() {
        System.out.println(SCORE_BOARD_HEADER);
    }

    private static void printNameSpace(String playerName) {
        System.out.print(String.format("%s%4s", VERTICAL_SIGN, playerName));
    }

    private static void printScores(List<Frame> frames) {
        for (Frame frame : frames) {
            String score = String.join("|", frame.getScore());

            System.out.print(String.format("%s%-4s", VERTICAL_SIGN, score));
        }
    }

    private static void printEmptySpace(int size) {
        for (int i = size; i < MAX_FRAME_COUNT; i++) {
            System.out.print(String.format("%s%s", VERTICAL_SIGN, EMPTY_SPACE));
        }
    }
}
