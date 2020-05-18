package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.List;

public class ResultView {
    private static final String FRAME_INFO_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRAME_NAME_FORMAT = "|  %s |";
    private static final String FRAME_NAME_FORMAT_FULL_SIZE = "%6s|";
    private static final String FRAME_NAME_FORMAT_FILL_SIZE = "  %-4s|";
    private static final String FRAME_NAME_FORMAT_EMPTY = "      |";
    private static final String ROLLING_SPLITTER_FORMAT = "|";
    private static final int FRAME_STRING_SIZE = 5;

    public static void printFrame(Player player, Frames frames) {
        System.out.println(FRAME_INFO_FORMAT);

        StringBuffer stringBuffer = new StringBuffer(String.format(FRAME_NAME_FORMAT, player.getName()));
        List<Frame> frameList = frames.getFrames();

        for (Frame frame : frameList) {
            addFrameState(stringBuffer, frame);
        }

        stringBuffer.append("\n");
        System.out.println(stringBuffer.toString());
    }

    private static void addFrameState(StringBuffer stringBuffer, Frame frame) {
        String statesFormat = String.join(ROLLING_SPLITTER_FORMAT, frame.getStates());

        if (statesFormat.length() == FRAME_STRING_SIZE) {
            stringBuffer.append(String.format(FRAME_NAME_FORMAT_FULL_SIZE, statesFormat));
            return;
        }

        if (statesFormat.isEmpty()) {
            stringBuffer.append(FRAME_NAME_FORMAT_EMPTY);
            return;
        }

        stringBuffer.append(String.format(FRAME_NAME_FORMAT_FILL_SIZE, statesFormat));
    }
}
