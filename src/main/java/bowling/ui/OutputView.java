package bowling.ui;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.game.Player;

import java.util.List;

import static bowling.domain.frame.Frames.MAX_FRAMES_SIZE;

public class OutputView {

    private static final String NAME_FORMAT = "| %s  ";
    private static final String FRAME_FORMAT = "|  %-3s ";
    private static final String SCORE_FORMAT = "|  %-3d ";
    private static final String EMPTY_PADDING = "|      ";

    private OutputView() {
    }

    public static void printBowling(Player player, Frames frames) {
        printHeader();
        printName(player.getName());
        printFrames(frames.getFrames());
        printScores(frames.getScores());
        System.out.println();
    }

    private static void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10");
    }

    private static void printName(String name) {
        System.out.printf(NAME_FORMAT, name);
    }

    private static void printFrames(List<Frame> frames) {
        frames.forEach(frame -> System.out.printf(FRAME_FORMAT, frame.toExpression()));

        int emptyFrameCount = MAX_FRAMES_SIZE - frames.size();
        for (int i = 0; i < emptyFrameCount; i++) {
            printEmptyPadding();
        }

        System.out.println();
    }

    private static void printScores(List<Integer> scores) {
        System.out.print(EMPTY_PADDING);

        int total = 0;
        for (int score : scores) {
            total = total + score;
            System.out.printf(SCORE_FORMAT, total);
        }

        int emptyScoreCount = MAX_FRAMES_SIZE - scores.size();
        for (int i = 0; i < emptyScoreCount; i++) {
            printEmptyPadding();
        }

        System.out.println();
    }

    private static void printEmptyPadding() {
        System.out.print(EMPTY_PADDING);
    }
}
