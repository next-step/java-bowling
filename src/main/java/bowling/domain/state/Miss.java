package bowling.domain.state;

public class Miss extends BaseState {

    private final int downPin;

    public Miss(int firstPin, int downPin) {
        super(firstPin);
        this.downPin = downPin;
    }

    @Override
    public String printResult() {
        return printScore(firstPin) + BOWLING_STATE_SPLIT_DELIMITER + printScore(downPin);
    }

    @Override
    public int totalScore() {
        return firstPin + downPin;
    }
}
