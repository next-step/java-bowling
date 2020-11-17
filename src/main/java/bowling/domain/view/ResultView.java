package bowling.domain.view;

import bowling.domain.frame.Frames2;
import bowling.domain.player.Player;

import java.util.stream.Stream;

public class ResultView {
    private static final String BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DEFAULT_BOARD = "| %-4s |      |      |      |      |      |      |      |      |      |      |";
    private static final int MAX_FRAME_SIZE = 10;
    private static final String BLANK = "";
    private static final String BLOCK = "|";
    private static final String MARGIN_BLOCK = "    ";


    public static void getDefaultBoard(Player player) {
        System.out.println(BOARD_TITLE);
        System.out.println(String.format(DEFAULT_BOARD, player));
        System.out.println(String.format(DEFAULT_BOARD, MARGIN_BLOCK));
        System.lineSeparator();
    }

    public static void printResult(Player player, Frames2 frames) {
        System.out.println(BOARD_TITLE);
        getScore(player, frames);
        printEmptyBlocks(frames);
        System.out.println();
    }

    private static void printEmptyBlocks(Frames2 frames) {
        for (int count = MAX_FRAME_SIZE - frames.getSize(); count > 0; count--) {
            System.out.print(margin(BLANK));
        }
    }

    private static void getScore(Player player, Frames2 frames) {
        System.out.print(BLOCK + margin(player.toString()));
        frames.getFrames().stream()
                .map(frame -> margin(frame.getScores()))
                .forEach(System.out::print);
    }

    private static void printPoint(Frames2 frames) {

    }

    private static String margin(String input) {
        return String.format("  %-4s|", input);
    }

}
