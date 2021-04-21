package bowling.domain.state;

public class Miss implements FrameState {

    private final int firstPin;
    private final int downPin;

    public Miss(int firstPin, int downPin) {
        this.firstPin = firstPin;
        this.downPin = downPin;
    }

    @Override
    public FrameState bowl(int pin) {
        return null;
    }

    @Override
    public String printResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER + printScore(downPin);
    }
}
