package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Score;
import org.apache.logging.log4j.util.Strings;

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

    public static void printScore(String playerName, List<Frame> frames, List<Integer> scores) {
        printScoreHeader();

        printNameSpace(playerName);
        printFallenPin(frames);
        printEmptySpace(frames.size());

        printNameSpace(Strings.EMPTY);
        printScores(scores);
        printEmptySpace(scores.size());

    }

    public static void printEmptyScore(String playerName) {
        printScore(playerName, Collections.emptyList(), Collections.emptyList());
    }

    public static void printScoreHeader() {
        System.out.println(SCORE_BOARD_HEADER);
    }

    private static void printNameSpace(String playerName) {
        System.out.print(String.format("%s%4s%s", VERTICAL_SIGN, playerName, VERTICAL_SIGN));
    }

    private static void printFallenPin(List<Frame> frames) {
        for (Frame frame : frames) {
            System.out.print(String.format("%-4s%s", frame.getFallenPins(), VERTICAL_SIGN));
        }
    }

    private static void printEmptySpace(int size) {
        for (int i = size; i < MAX_FRAME_COUNT; i++) {
            System.out.print(String.format("%s%s", EMPTY_SPACE, VERTICAL_SIGN));
        }
        System.out.println();
    }

    private static void printScores(List<Integer> scores) {
        for (Integer score : scores) {
            System.out.print(String.format("%-4s%s", score, VERTICAL_SIGN));
        }
    }
}
