package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Pin;
import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.Frame.NO_SCORE;
import static bowling.domain.Pin.MAX_COUNT;

public class FrameResult {
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    public String frameSign(Frame frame) {
        if (frame.isLastFrame()) {
            return String.format(frame.getStates()
                    .stream()
                    .map(this::sign)
                    .collect(Collectors.joining("|")));
        }

        State state = frame.getState();
        return sign(state);
    }

    private String sign(State state) {
        return createSign(state)
                .replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER);
    }

    private static String createSign(State state) {
        List<Pin> pins = state.pins();

        if (pins.size() == 0) {
            return "";
        }

        if (pins.size() == 1) {
            return String.valueOf(pins.get(0).getCount());
        }

        if (pins.get(0).getCount() + pins.get(1).getCount() == MAX_COUNT) {
            return String.format("%d|/", pins.get(0).getCount());
        }

        return String.format("%d|%d", pins.get(0).getCount(), pins.get(1).getCount());
    }

    public int frameScore(Frame frame) {
        try {
            return frame.getIntScore();
        } catch (UnsupportedOperationException e) {
            return NO_SCORE;
        }
    }
}
