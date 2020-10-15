package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Frame;
import bowling.domain.Frames;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class OutputView {

    public static final int MAX_FRAME_COUNT = 10;
    public static final String VERTICAL_SIGN = " | ";
    public static final String SCORE_BOARD_HEADER = " | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final Object EMPTY_SPACE = "    ";

    private OutputView() {
    }

    public static void printScore(BowlingGames bowlingGames) {
        printScoreHeader();

        for (BowlingGame game : bowlingGames.getBowlingGames()) {
            printNameSpace(game.getPlayer().toString());
            printFallenPin(game.getFrames());

            printNameSpace(Strings.EMPTY);
            printScores(game.getScore());
        }
    }

    public static void printScoreHeader() {
        System.out.println(SCORE_BOARD_HEADER);
    }

    private static void printNameSpace(String playerName) {
        System.out.print(String.format("%s%4s%s", VERTICAL_SIGN, playerName, VERTICAL_SIGN));
    }

    private static void printFallenPin(Frames frames) {
        for (Frame frame : frames.getFrames()) {
            System.out.print(String.format("%-4s%s", frame.getFallenPins(), VERTICAL_SIGN));
        }

        printEmptySpace(frames.size());
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
        printEmptySpace(scores.size());
    }
}
