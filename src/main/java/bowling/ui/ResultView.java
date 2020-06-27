package bowling.ui;

import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.stream.Stream;

public class ResultView {
    public static final int MAX_FRAME_SIZE = 10;
    public static final String BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printResult(Player player, Frames frames) {
        System.out.println(BOARD_TITLE);
        printScoreLine(player, frames);
        printEmptyLine(frames);
        System.out.println("\n");
    }

    private static void printEmptyLine(Frames frames) {
        Stream.generate(() -> formatting(""))
                .limit(MAX_FRAME_SIZE - frames.size())
                .forEach(System.out::print);
    }

    private static void printScoreLine(Player player, Frames frames) {
        System.out.print("|" + formatting(player.getName()));
        frames.getFrames().stream()
                .map(frame -> formatting(frame.getResult()))
                .forEach(System.out::print);
    }

    private static String formatting(String input) {
        return String.format("  %-4s|", input);
    }
}
