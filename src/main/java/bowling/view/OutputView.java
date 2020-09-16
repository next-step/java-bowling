package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.util.StringUtils;

import java.text.MessageFormat;
import java.util.stream.IntStream;

public class OutputView {

    public static void printBoard(Player player, Frame frame) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.print(MessageFormat.format("| {0} | {1} |", StringUtils.leftPad(player.getName(), 4), StringUtils.leftPad(frame.toString(), 4)));
        while (frame.hasNext()) {
            frame = frame.next();
            System.out.print(StringUtils.leftPad(frame.toString(), 5) + " |");
        }
        IntStream.range(frame.getFrameNumber(), 10)
                .forEach(i -> System.out.print(StringUtils.leftPad("", 5) + " |"));
    }

}
