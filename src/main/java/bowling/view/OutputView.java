package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.util.StringUtils;

import java.text.MessageFormat;
import java.util.stream.IntStream;

import static bowling.domain.AbstractFrame.LAST_FRAME_NUMBER;

public class OutputView {

    public static void printBoard(Player player, Frame firstFrame) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.print(MessageFormat.format("| {0} |", StringUtils.leftPad(player.getName(), 4)));
        Frame lastFrame = printFrame(firstFrame);
        printEmptyBoard(lastFrame);
        System.out.println();
    }

    private static Frame printFrame(Frame firstFrame) {
        Frame lastFrame = firstFrame;
        for (Frame nextFrame: firstFrame) {
            System.out.print(StringUtils.leftPad(nextFrame.toString(), 5) + " |");
            lastFrame = nextFrame;
        }
        return lastFrame;
    }

    private static void printEmptyBoard(Frame lastFrame) {
        IntStream.range(lastFrame.getFrameNumber(), LAST_FRAME_NUMBER)
                .forEach(i -> System.out.print(StringUtils.leftPad("", 5) + " |"));
    }

}
