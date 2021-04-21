package bowling.domain.state;

public class Spare extends BaseState {

    private final int secondPin;

    public Spare(final int firstPin, final int secondPin) {
        super(firstPin);
        this.secondPin = secondPin;
    }

    @Override
    public String printResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER + BOWLING_STATE_SPARE;
    }

    @Override
    public int totalScore() {
        return firstPin + secondPin;
    }
}
