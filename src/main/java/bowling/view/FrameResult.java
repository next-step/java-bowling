package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Pin;
import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class FrameResult {

    private static final int NO_SCORE = -1;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    public String frameSign(final Frame frame) {

        if (frame.isLastFrame()) {
            final String states = String.format(frame.getStates()
                    .stream()
                    .map(this::sign)
                    .collect(Collectors.joining("|")), "%5s");
            return String.format("%5s ", states);
        }

        final State state = frame.getState();
        return String.format(" %3s  ", sign(state));
    }

    private String sign(final State state) {

        return createSign(state)
                .replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER);
    }

    private static String createSign(final State state) {

        final List<Pin> pins = state.pins();
        if (pins.size() == 0) {
            return "";
        }

        if (pins.size() == 1) {
            return String.valueOf(pins.get(0).count());
        }

        if (pins.get(0).count() + pins.get(1).count() == 10) {
            return String.format("%d|/", pins.get(0).count());
        }

        return String.format("%d|%d", pins.get(0).count(), pins.get(1).count());
    }

    public int frameScore(final Frame frame) {

        try {
            return frame.getIntScore();
        } catch (IllegalArgumentException e) {
            return NO_SCORE;
        }
    }
}
