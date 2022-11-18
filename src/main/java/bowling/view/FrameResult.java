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
    private static final String EMPTY = "";
    private static final int MAX_COUNT = 10;

    public String frameSign(final Frame frame) {

        if (frame.isLastFrame()) {
            return String.format(frame.getStates()
                    .stream()
                    .map(this::sign)
                    .collect(Collectors.joining("|")));
        }
        return sign(frame.getState());
    }

    private String sign(final State state) {

        return createSign(state)
                .replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER);
    }

    private static String createSign(final State state) {

        final List<Pin> pins = state.pins();
        if (state.hasPins(0)) {
            return EMPTY;
        }

        final Pin firstPin = pins.get(0);
        if (state.hasPins(1)) {
            return String.valueOf(firstPin.count());
        }

        final Pin secondPin = pins.get(1);
        if (firstPin.count() + secondPin.count() == MAX_COUNT) {
            return String.format("%d|/", firstPin.count());
        }

        return String.format("%d|%d", firstPin.count(), secondPin.count());
    }

    public int frameScore(final Frame frame) {

        try {
            return frame.getIntScore();
        } catch (IllegalArgumentException e) {
            return NO_SCORE;
        }
    }
}
