package bowling.domain;

import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static bowling.common.BowlingConstant.*;

public class NormalRollingResult {

    private final List<Pin> rollingPin;

    public NormalRollingResult() {
        this.rollingPin = new ArrayList<>();
    }

    public void bowl(int countOfPins) {
        rollingPin.add(new Pin(countOfPins));

        if (countOfAllPins() > PIN_COUNT_TOTAL) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isFinish() {
        return rollingPin.size() > 1 || countOfAllPins() == PIN_COUNT_TOTAL;
    }

    public Status status() {
        return Status.of(allFallenPins(), rollingPin.size());
    }

    public String desc() {
        if (rollingPin.isEmpty()) {
            return "";
        }
        Status status = status();
        if (status == Status.STRIKE) {
            return BOWLING_STATUS_STRIKE;
        }

        String firstBowl = transGutter(FRAME_BOWL_INDEX_FIRST);
        StringJoiner sj = new StringJoiner(BOWLING_SCORE_SEPERATOR);
        if (status == Status.SPARE) {

            sj.add(firstBowl);
            sj.add(BOWLING_STATUS_SPARE);
            return sj.toString();
        }

        if (status == Status.MISS) {
            sj.add(firstBowl);
            sj.add(transGutter(FRAME_BOWL_INDEX_SECOND));
            return sj.toString();
        }

        return firstBowl;
    }

    private int countOfAllPins() {

        return rollingPin.stream().map(pin -> pin.count()).reduce((a, b) -> a + b).orElse(-1);
    }

    private boolean allFallenPins() {
        return countOfAllPins() == PIN_COUNT_TOTAL;
    }

    private String transGutter(int tryIndex) {
        String pinCount = String.valueOf(rollingPin.get(tryIndex).count());
        if (pinCount.equals(BOWLING_FALLEN_PIN_ZERO)) {
            return BOWLING_STATUS_GUTTER;
        }
        return pinCount;
    }
}
