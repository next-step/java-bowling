package bowling.View;

import bowling.domain.Frame;
import bowling.domain.Name;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String FRAME_TEMPLATE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_FRAME = "      |";

    public void printFrame(List<Frame> frames, Player player) {
        System.out.println(FRAME_TEMPLATE);
        System.out.println(frameFormat(player.name()) + frameFormat(frames));
    }

    private String frameFormat(List<Frame> frames) {
        String collect = frames.stream()
                .map(frame -> frameFormat(frame))
                .collect(Collectors.joining(""));
        return collect;
    }

    private String frameFormat(Frame frame) {
        if (frame.empty()) {
            return EMPTY_FRAME;
        }
        if (frame.remain()) {
            return "  " + frame.first().score() + "|  |";
        }
        if (frame.isStrike()) {
            return "  X   |";
        }
        if (frame.isSpare()) {
            return "  " + frame.first().score() + "|- |";
        }
        return "  " + frame.first().score() + "|" + frame.second().score() + " |";
    }

    private String frameFormat(Name name) {
        return String.format("| %4s |", name.value());
    }

}