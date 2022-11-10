package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Pin;
import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.Frame.NO_SCORE;

public class FrameResult {
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    public String frameSign(Frame frame) {
        if (frame.isLastFrame()) {
            String states = String.format(frame.getStates()
                    .stream()
                    .map(this::sign)
                    .collect(Collectors.joining("|")), "%5s");
            return String.format("%5s ", states);
        }

        State state = frame.getState();
        return String.format(" %3s  ", sign(state));
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

        if (pins.get(0).getCount() + pins.get(1).getCount() == 10) {
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
