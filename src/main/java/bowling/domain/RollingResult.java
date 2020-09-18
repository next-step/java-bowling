package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RollingResult {
    protected static final String BOWLING_SCORE_SEPERATOR = "|";
    protected static final String BOWLING_STATUS_STRIKE = "X";
    protected static final String BOWLING_STATUS_SPARE = "/";
    protected static final String BOWLING_STATUS_GUTTER = "-";
    protected static final String BOWLING_FALLEN_PIN_ZERO = "0";

    protected static final int PIN_COUNT_TOTAL = 10;

    protected final List<Pin> rollingPin = new ArrayList<>();
    protected final List<String> recordBowl = new ArrayList<>();

    abstract protected boolean isFinish();

    abstract protected void bowl(Pin pin);

    protected String currentFrameStatus() {
        if (rollingPin.isEmpty()) {
            return "";
        }
        return this.recordBowl.stream()
                .collect(Collectors.joining(BOWLING_SCORE_SEPERATOR));
    }

    protected String ifCountOfPinsZeroTransGutter(Pin pin) {
        String pinCount = String.valueOf(pin.count());
        if (pinCount.equals(BOWLING_FALLEN_PIN_ZERO)) {
            return BOWLING_STATUS_GUTTER;
        }
        return pinCount;
    }

    protected int countOfFirstAndSecondPins() {
        return rollingPin.stream()
                .limit(2)
                .map(pin -> pin.count())
                .reduce((a, b) -> a + b)
                .orElse(-1);
    }

    protected void record(Pin pin) {
        recordBowl.add(judgeBowlResult(pin));
    }

    private String judgeBowlResult(Pin pin) {
        if (pin.count() == PIN_COUNT_TOTAL) {
            return BOWLING_STATUS_STRIKE;
        }

        if (rollingPin.size() > 1 && isSpare()) {
            return BOWLING_STATUS_SPARE;
        }

        return ifCountOfPinsZeroTransGutter(pin);
    }

    private boolean isSpare() {
        if (recordBowl.contains(BOWLING_STATUS_SPARE)) {
            return false;
        }
        return countOfIndexPins(rollingPin.size() - 1, rollingPin.size() - 2) == PIN_COUNT_TOTAL;
    }

    private int countOfIndexPins(int index1, int index2) {
        return rollingPin.get(index1).count() + rollingPin.get(index2).count();
    }


}
