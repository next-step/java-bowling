package bowling.view;

import bowling.domain.NormalFrame;
import bowling.domain.Player;
import bowling.util.StringUtils;

import java.text.MessageFormat;
import java.util.stream.IntStream;

import static bowling.domain.NormalFrame.LAST_FRAME_NUMBER;

public class OutputView {

    public static void printBoard(Player player, NormalFrame normalFrame) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.print(MessageFormat.format("| {0} | {1} |", StringUtils.leftPad(player.getName(), 4), StringUtils.leftPad(normalFrame.toString(), 4)));
        while (normalFrame.hasNext()) {
            normalFrame = normalFrame.next();
            System.out.print(StringUtils.leftPad(normalFrame.toString(), 5) + " |");
        }
        IntStream.range(normalFrame.getFrameNumber(), LAST_FRAME_NUMBER)
                .forEach(i -> System.out.print(StringUtils.leftPad("", 5) + " |"));
    }

}
