package bowling.view;

import bowling.domain.frame.Frame;
import bowling.dto.BowlingResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    public static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String FRAME = "  %-3s ";
    public static final String PLAYER = "|  %s |";
    public static final String FRAME_END = "|";
    public static final String EMPTY_FRAME = "      ";


    public static void board(final BowlingResponse response) {
        System.out.println(BOARD);
        String frameBoard = IntStream.range(0, 10)
                .mapToObj(index -> getFrameString(response, index))
                .collect(Collectors.joining("|"));
        System.out.println(String.format(PLAYER, response.player()) + frameBoard + FRAME_END);
    }

    private static String getFrameString(final BowlingResponse response, final int index) {
        List<Frame> frames = response.getFrames();
        if (index < frames.size()) {
            return String.format(FRAME, frames.get(index).print());
        }
        return EMPTY_FRAME;
    }

    public static void board() {
    }
}
