package bowling.domain.state;

public interface State {
    static State newState(BowlingPin bowlingPin) {
        return new Miss(bowlingPin);
    }

    static State newState(BowlingPin firstPin, BowlingPin secondPin) {
        return new Miss(firstPin, secondPin);
    }
}
