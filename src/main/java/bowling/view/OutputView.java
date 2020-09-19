package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.util.StringUtils;

import java.text.MessageFormat;
import java.util.stream.IntStream;

import static bowling.domain.frame.AbstractFrame.LAST_FRAME_NUMBER;

public class OutputView {

    public static final String EMPTY = "";

    public static void printBoard(Player player, Frame firstFrame) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        printPins(player, firstFrame);
        System.out.println();
        printScore(firstFrame);
        System.out.println();
    }

    private static void printPins(Player player, Frame firstFrame) {
        System.out.print(MessageFormat.format("| {0} |", StringUtils.leftPad(player.getName(), 4)));
        Frame lastFrame = firstFrame;
        for (Frame nextFrame: firstFrame) {
            System.out.print(StringUtils.leftPad(nextFrame.toString(), 5) + " |");
            lastFrame = nextFrame;
        }
        printEmptyBoard(lastFrame);
    }

    private static void printScore(Frame firstFrame) {
        System.out.print("|      |");
        Frame lastFrame = firstFrame;
        for (Frame nextFrame: firstFrame) {
            System.out.print(StringUtils.leftPad(convertScore(nextFrame.getScore()), 5) + " |");
            lastFrame = nextFrame;
        }
        printEmptyBoard(lastFrame);
    }

    private static String convertScore(int score) {
        return score == -1 ? EMPTY : String.valueOf(score);
    }

    private static void printEmptyBoard(Frame lastFrame) {
        IntStream.range(lastFrame.getFrameNumber(), LAST_FRAME_NUMBER)
                .forEach(i -> System.out.print(StringUtils.leftPad("", 5) + " |"));
    }

}
