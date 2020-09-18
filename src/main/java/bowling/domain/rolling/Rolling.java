package bowling.domain.rolling;

import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Rolling {

    private static final String BOWLING_SCORE_SEPERATOR = "|";

    protected final List<Pin> rollingPin = new ArrayList<>();
    protected final List<String> recordBowl = new ArrayList<>();

    abstract public boolean isFinish();

    abstract public void bowl(Pin pin);

    public String currentFrameStatus() {
        if (recordBowl.isEmpty()) {
            return "";
        }
        return this.recordBowl.stream()
                .collect(Collectors.joining(BOWLING_SCORE_SEPERATOR));
    }

    protected int countOfFirstAndSecondPins() {
        return rollingPin.stream()
                .limit(2)
                .map(pin -> pin.count())
                .reduce((a, b) -> a + b)
                .orElse(-1);
    }

    protected void record(String pinStatus) {
        recordBowl.add(pinStatus);
    }

    protected Pin firstPin() {
        return rollingPin.get(0);
    }

    protected Pin beforePin() {
        return rollingPin.get(rollingPin.size() - 2);
    }
}
