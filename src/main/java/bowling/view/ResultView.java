package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResult;
import bowling.domain.frame.Frames;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String VERTICAL_LINE = "|";

    public static void printResultBoard(final Player player, final Frames frames) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        final List<Frame> frameList = frames.getFrames();
        System.out.println(frameList.stream()
            .map(FrameResult::get)
            .map(s -> String.format("%6s", s))
            .collect(Collectors.joining(
                VERTICAL_LINE, String.format("%s%6s%s", VERTICAL_LINE, player.getName(), VERTICAL_LINE), VERTICAL_LINE)));
    }
}
