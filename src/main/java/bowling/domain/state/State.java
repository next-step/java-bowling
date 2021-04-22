package bowling.domain.state;

public interface State {
    static State newState(BowlingPin bowlingPin) {
        if (bowlingPin.isMax()) {
            return Strike.of(bowlingPin);
        }
        return Miss.of(bowlingPin);
    }

    static State newState(BowlingPin firstPin, BowlingPin secondPin) {
        if (firstPin.sum(secondPin).isMax()) {
            return Spare.of(firstPin, secondPin);
        }
        return Miss.of(firstPin, secondPin);
    }

    BowlingPin firstHit();
    String score();
    String totalScore();
}
