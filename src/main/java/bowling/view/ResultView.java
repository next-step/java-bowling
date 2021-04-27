package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.Map;
import java.util.stream.IntStream;

import static bowling.controller.BowlingGameController.MAX_FRAME_NO;
import static bowling.controller.BowlingGameController.START_FRAME_NO;

public class ResultView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    protected static final String DELIMITER = "|";
    protected static final String EMPTY_FRAME = "      |";
    protected static final String SYMBOL_STRIKE = "X";
    protected static final String SYMBOL_SPARE = "/";
    protected static final String SYMBOL_GUTTER = "-";
    protected static final String ZERO = "0";

    public void initBoard(Player player) {
        printBoard(player, new Frames());
    }

    public void printBoard(Player player, Frames frames) {
        System.out.println(BOARD_HEADER);
        System.out.print(playerName(player.name()));
        System.out.println(frameResult(frames));
    }

    private String frameResult(Frames frames) {
        StringBuilder builder = new StringBuilder();

        IntStream.rangeClosed(START_FRAME_NO, MAX_FRAME_NO)
                .forEach(frameNo -> builder.append(framePitches(frameNo, frames)));

        return builder.toString();
    }

    private String framePitches(int frameNo, Frames frames) {
        Map<Integer, Frame> frameMap = frames.frames();
        if (frameMap.containsKey(frameNo)) {
            ResultViewType viewType = ResultViewFactory.factory(frameNo, MAX_FRAME_NO);
            String frameResult = viewType.frameResult(frameMap.get(frameNo));
            return String.format(" %-3s  " + DELIMITER, frameResult);
        }
        return EMPTY_FRAME;
    }

    private String playerName(String name) {
        return String.format(DELIMITER + " %4s " + DELIMITER, name);
    }

    protected String convertZeroToHyphen(int fallPins) {
        return String.valueOf(fallPins).replace(ZERO, SYMBOL_GUTTER);
    }
}
