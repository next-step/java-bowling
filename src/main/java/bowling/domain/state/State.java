package bowling.domain.state;

public interface State {
    static State newState(BowlingPin bowlingPin) {
        if (bowlingPin.isMax()) {
            return new Strike();
        }
        return Miss.of(bowlingPin);
    }

    static State newState(BowlingPin firstPin, BowlingPin secondPin) {
        if (firstPin.sum(secondPin).isMax()) {
            return Spare.of(firstPin, secondPin);
        }
        return Miss.of(firstPin, secondPin);
    }
}
